package br.com.trochadev.cotacaomoedas.client;

import br.com.trochadev.cotacaomoedas.entity.Cotacao;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

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
