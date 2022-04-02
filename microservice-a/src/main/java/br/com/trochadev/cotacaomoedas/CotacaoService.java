package br.com.trochadev.cotacaomoedas;

import br.com.trochadev.cotacaomoedas.model.Cotacao;
import io.quarkus.cache.CacheResult;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class CotacaoService {

    final Logger log = LoggerFactory.getLogger(CotacaoService.class);

    @RestClient
    @Inject
    CotacaoClient client;

    @CacheResult(cacheName = "cotacao")
    public Cotacao getCotacao(String data) {
        log.info("Fez a chamada na API do BCB com a data: " + data);
        return client.getCotacao(data);
    }
}
