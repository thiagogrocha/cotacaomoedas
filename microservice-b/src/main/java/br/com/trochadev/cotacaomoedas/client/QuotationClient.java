package br.com.trochadev.cotacaomoedas.client;

import br.com.trochadev.cotacaomoedas.entity.Quotation;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@RegisterRestClient(configKey = "quotation")
@Path("/")
public interface QuotationClient {

    @GET
    @Path("CotacaoDolar")
    @Produces(MediaType.APPLICATION_JSON)
    Quotation getQuotation(@QueryParam("Data") String data);
}
