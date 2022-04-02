package br.com.trochadev.cotacaomoedas;

import br.com.trochadev.cotacaomoedas.model.Cotacao;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("CotacaoDolar")
public class CotacaoResource {

    @Inject
    CotacaoService service;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Cotacao getCotacao(@QueryParam("data") String data) {
        return service.getCotacao(data);
    }

}
