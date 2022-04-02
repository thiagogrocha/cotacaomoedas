package br.com.trochadev.cotacaomoedas.service;

import br.com.trochadev.cotacaomoedas.client.QuotationClient;
import br.com.trochadev.cotacaomoedas.controller.ParameterIn;
import br.com.trochadev.cotacaomoedas.entity.Quotation;
import br.com.trochadev.cotacaomoedas.exception.QuotationNotFoundException;
import io.quarkus.cache.CacheResult;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@ApplicationScoped
public class QuotationService {

    final Logger log = LoggerFactory.getLogger(QuotationService.class);

    @RestClient
    @Inject
    QuotationClient client;

    @CacheResult(cacheName = "quotation")
    public Quotation getQuotation(ParameterIn data) throws QuotationNotFoundException {
        return client.getQuotation(data.getData().orElse("'" + LocalDate.now().format(DateTimeFormatter.ofPattern("MM-dd-yyyy")) + "'"));
    }
}
