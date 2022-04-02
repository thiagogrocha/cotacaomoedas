package br.com.trochadev.cotacaomoedas.client;

import br.com.trochadev.cotacaomoedas.entity.Quotation;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@RegisterRestClient(configKey = "quotation")
@Path("/")
public interface QuotationClient {

    @GET
    @Path("CotacaoDolarDia(dataCotacao=@dataCotacao)")
    @Produces(MediaType.APPLICATION_JSON)
    public Quotation getQuotation(@QueryParam("@dataCotacao") String data);

}
