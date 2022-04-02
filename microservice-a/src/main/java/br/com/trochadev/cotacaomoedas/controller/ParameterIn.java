package br.com.trochadev.cotacaomoedas.controller;

import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;

import javax.ws.rs.QueryParam;
import java.util.Optional;

@Schema(name = "ParâmetroIn", description = "Parâmetro de entrada")
public class ParameterIn {

    @Schema(title = "Data da cotação", pattern = "'\\d{2}-\\d{2}-\\d{4}'")
    @QueryParam("Data")
    @Parameter(description = "'MM-DD-AAAA' - Caso a data não seja informada, será utilizada a data atual.")
    String data;

    public Optional<String> getData() {
        return Optional.ofNullable(data);
    }

    public void setData(String data) {
        this.data = data;
    }
}
