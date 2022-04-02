package br.com.trochadev.cotacaomoedas.model;

import javax.json.bind.annotation.JsonbProperty;
import java.util.List;

public class Cotacao {
    @JsonbProperty(value = "@odata.context")
    String context;
    List<CotacaoDolar> value;

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public List<CotacaoDolar> getValue() {
        return value;
    }

    public void setValue(List<CotacaoDolar> value) {
        this.value = value;
    }
}
