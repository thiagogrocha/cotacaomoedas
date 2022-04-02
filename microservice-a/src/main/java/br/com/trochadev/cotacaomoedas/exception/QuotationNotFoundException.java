package br.com.trochadev.cotacaomoedas.exception;

public class QuotationNotFoundException extends Exception{
    public QuotationNotFoundException(String message) {
        super(message);
    }
}
