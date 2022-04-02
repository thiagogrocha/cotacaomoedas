package br.com.trochadev.cotacaomoedas.controller;

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

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("CotacaoDolar")
@Tag(name = "Cotação", description = "Dólar compra/venda")
public class QuotationController {

    @Inject
    QuotationService service;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Cotação Dólar por dia", description = "Retorna a cotação do Dólar no dia informado")
    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "Sucesso",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = Quotation.class))),
            @APIResponse(responseCode = "403", description = "Cotação não localizada",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = ExceptionHandler.ErrorResponseBody.class)))})
    public Quotation getQuotation(@BeanParam ParameterIn data) throws QuotationNotFoundException {
        return service.getQuotation(data);
    }

}
