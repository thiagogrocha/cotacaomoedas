package br.com.trochadev.cotacaomoedas.controller;

import br.com.trochadev.cotacaomoedas.config.QuotationConfig;
import br.com.trochadev.cotacaomoedas.entity.DolarQuotation;
import br.com.trochadev.cotacaomoedas.entity.Quotation;
import br.com.trochadev.cotacaomoedas.service.QuotationService;
import br.com.trochadev.cotacaomoedas.exception.QuotationNotFoundException;
import br.com.trochadev.cotacaomoedas.exceptionhandler.ExceptionHandler;
import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import org.eclipse.microprofile.faulttolerance.CircuitBreaker;
import org.eclipse.microprofile.faulttolerance.Fallback;
import org.eclipse.microprofile.faulttolerance.Retry;
import org.eclipse.microprofile.faulttolerance.Timeout;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Arrays;

@Path("CotacaoDolar")
@Tag(name = "Cotação", description = "Dólar compra/venda")
@ApplicationScoped
public class QuotationController {

    final Logger log = LoggerFactory.getLogger(QuotationController.class);

    private boolean simularEspera = false;

    private boolean simularExcecao = false;

    QuotationConfig quotationConfig;

    QuotationService service;

    public QuotationController() {
    }

    @Inject
    public QuotationController(QuotationConfig quotationConfig, QuotationService service) {
        this.quotationConfig = quotationConfig;
        this.service = service;
    }

    @GET
    @Timeout(value = 1000)
    @Retry(maxRetries = 1)
    @CircuitBreaker
    @Fallback(fallbackMethod = "fallbackQuotation")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Cotação Dólar por dia", description = "Retorna a cotação do Dólar no dia informado")
    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "Sucesso",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = Quotation.class))),
            @APIResponse(responseCode = "403", description = "Cotação não localizada",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = ExceptionHandler.ErrorResponseBody.class)))})
    public Quotation getQuotation(@BeanParam ParameterIn data) throws QuotationNotFoundException {
        talvezEspere1Seg();
        talvezLanceExcecao();
        return service.getQuotation(data);
    }


    public Quotation fallbackQuotation(@BeanParam ParameterIn data) throws QuotationNotFoundException {
        DolarQuotation dq = new DolarQuotation();
        dq.setCotacaoCompra(0);
        dq.setCotacaoVenda(0);
        dq.setDataHoraCotacao(data.data);
        Quotation q = new Quotation();
        q.setContext("Método fallbackQuotation foi chamado pois houve erro na chamada original!");
        q.setValue(Arrays.asList(dq));

        return q;
    }

    // --- Os métodos abaixo são somente para simulação de erros/timeout ---

    private void talvezLanceExcecao() {
        if (!quotationConfig.isSimularExcecao()) {
            return;
        }

        if (simularExcecao) {
            simularExcecao = false;
            log.error("Simulando Excecao!");
            throw new RuntimeException("Erro!");
        } else {
            simularExcecao = true;
        }
    }

    private void talvezEspere1Seg() {
        if (!quotationConfig.isSimularEspera()) {
            return;
        }

        if (simularEspera) {
            simularEspera = false;
            log.error("Simulando espera de 1 segundo!");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                log.warn("Interrupted!", e);
                Thread.currentThread().interrupt();
            }
        } else {
            simularEspera = true;
        }
    }
}
