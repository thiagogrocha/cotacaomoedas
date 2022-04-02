package br.com.trochadev.cotacaomoedas.entity;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

import javax.json.bind.annotation.JsonbProperty;
import java.util.List;

@Schema(name="Cotação", description="Classe que representa a Cotação")
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
