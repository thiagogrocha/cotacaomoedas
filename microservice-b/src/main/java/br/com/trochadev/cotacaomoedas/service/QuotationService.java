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

    @Inject
    BusinessDayService bdService;

    @CacheResult(cacheName = "quotation")
    public Quotation getQuotation(ParameterIn data) throws QuotationNotFoundException {
        String date = data.getData().orElse("'" + LocalDate.now().format(DateTimeFormatter.ofPattern("MM-dd-yyyy")) + "'");
        String date2 = date;

        bdService.setDate(date);
        if (bdService.isBusinessDay()) {
            Quotation quotation = client.getQuotation(date);

            do {
                date2 = LocalDate.parse(date2.replace("'", ""), DateTimeFormatter.ofPattern("MM-dd-yyyy")).minusDays(1).format(DateTimeFormatter.ofPattern("MM-dd-yyyy"));
                bdService.setDate(date2);

            } while (!bdService.isBusinessDay());
            quotation.getValue().add(client.getQuotation("'" + date2 + "'").getValue().get(0));

            return quotation;
        } else
            throw new QuotationNotFoundException("A data informada não corresponde a um dia útil! " + bdService.getDay());
    }
}
