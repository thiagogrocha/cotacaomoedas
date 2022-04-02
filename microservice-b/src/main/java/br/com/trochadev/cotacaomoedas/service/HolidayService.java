package br.com.trochadev.cotacaomoedas.service;

import br.com.trochadev.cotacaomoedas.client.HolidayClient;
import br.com.trochadev.cotacaomoedas.entity.Holiday;
import io.quarkus.cache.CacheResult;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@ApplicationScoped
public class HolidayService {

    final Logger log = LoggerFactory.getLogger(HolidayService.class);
    final String token = "645|qFp7CEzu0MEQhmYtf0xXQ2wDjxPFyBjc";

    @RestClient
    @Inject
    HolidayClient client;

    @CacheResult(cacheName = "holiday")
    public Optional<Holiday> getHoliday(String data) {
//        log.info("Fez a chamada na API do BCB com a data: " + data);



        return Optional.ofNullable(new Holiday());
    }
}
