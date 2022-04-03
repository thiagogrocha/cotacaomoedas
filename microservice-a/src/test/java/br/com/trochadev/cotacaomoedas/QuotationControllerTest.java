package br.com.trochadev.cotacaomoedas;

import br.com.trochadev.cotacaomoedas.config.QuotationConfig;
import br.com.trochadev.cotacaomoedas.controller.ParameterIn;
import br.com.trochadev.cotacaomoedas.controller.QuotationController;
import br.com.trochadev.cotacaomoedas.entity.Quotation;
import br.com.trochadev.cotacaomoedas.exception.QuotationNotFoundException;
import br.com.trochadev.cotacaomoedas.service.QuotationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.time.Instant;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

@ExtendWith(MockitoExtension.class)
public class QuotationControllerTest {

    private final QuotationConfig quotationConfigComEspera = new QuotationConfig(true, false);
    private final QuotationConfig quotationConfigComExcecao = new QuotationConfig(false, true);

    private final Logger log = LoggerFactory.getLogger(QuotationControllerTest.class);

    @Mock
    private QuotationService service;

    @BeforeEach
    public void assumptions() {
        assumeTrue(log != null, "logger não foi inicializado.");
    }

    @Test
    public void quotation_SimularEspera_PrimeiraChamada() throws QuotationNotFoundException {
        // setup
        QuotationController quotationController = newQuotationController(quotationConfigComEspera);

        // when
        quotationController.getQuotation(new ParameterIn());
        Instant inicio = Instant.now();
        quotationController.getQuotation(new ParameterIn());
        Instant fim = Instant.now();

        // then
        long duracaoAtual = Duration.between(inicio, fim).toMillis();
        long duracaoMaxima = 1000;
        assertTrue(duracaoAtual >= duracaoMaxima,
                "Segunda execução demorou menos de 1 segundo, quer dizer que não esperou 1 segundo: " + duracaoAtual);
    }

    @Test
    public void quotation_SimularExcecao_PrimeiraChamada() throws QuotationNotFoundException {
        // setup
        QuotationController quotationController = newQuotationController(quotationConfigComExcecao);

        // when
        Quotation quotation = quotationController.getQuotation(new ParameterIn());

        // then
        assertNull(quotation, "Retornou uma quotation quando não deveria ter retornado.");
    }

    @Test
    public void quotation_SimularExcecao_TerceiraChamada() throws QuotationNotFoundException {
        // setup
        QuotationController quotationController = newQuotationController(quotationConfigComExcecao);

        // when
        try {
            quotationController.getQuotation(new ParameterIn());
            quotationController.getQuotation(new ParameterIn());
        } catch (RuntimeException | QuotationNotFoundException e) {
        }
        Quotation quotation = quotationController.getQuotation(new ParameterIn());

        // then
        assertNull(quotation, "Retornou uma quotation quando não deveria ter retornado.");
    }

    private QuotationController newQuotationController(QuotationConfig quotationConfig) {
        QuotationController quotationController = new QuotationController(quotationConfig, service);
        return quotationController;
    }
}
