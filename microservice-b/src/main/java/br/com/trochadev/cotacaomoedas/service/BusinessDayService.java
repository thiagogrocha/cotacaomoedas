package br.com.trochadev.cotacaomoedas.service;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class BusinessDayService {

    private boolean isBusinessDay;

    public BusinessDayService() {
    }

    public BusinessDayService(String date) {
    }

    public boolean isBusinessDay() {
        return isBusinessDay;
    }

    public String getDay() {
        return "";
    }
}
