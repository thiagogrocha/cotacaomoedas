package br.com.trochadev.cotacaomoedas;

import br.com.trochadev.cotacaomoedas.model.Cotacao;
import org.eclipse.microprofile.openapi.annotations.headers.Header;
import org.eclipse.microprofile.rest.client.annotation.RegisterClientHeaders;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@RegisterRestClient(configKey = "cotacao")
@Path("/")
public interface CotacaoClient {

    @GET
    @Path("CotacaoDolarDia(dataCotacao=@dataCotacao)")
    @Produces(MediaType.APPLICATION_JSON)
    public Cotacao getCotacao(@QueryParam("@dataCotacao") String data);


}
