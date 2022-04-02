package br.com.trochadev.cotacaomoedas.config;

import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.info.Contact;
import org.eclipse.microprofile.openapi.annotations.info.Info;
import org.eclipse.microprofile.openapi.annotations.info.License;

import javax.ws.rs.core.Application;

@OpenAPIDefinition(
        info = @Info(
                title = "Cotação Dólar por dia API",
                description = "Fornece a cotação do Dólar na data informada",
                version = "1.0.0",
                contact = @Contact(
                        name = "Thiago Guimaraes Rocha",
                        url = "https://github.com/thiagorocha-dev/cotacaomoedas",
                        email = "thiagoguimaraesdeveloper@hotmail.com"),
                license = @License(
                        name = "MIT",
                        url = "https://opensource.org/licenses/MIT")))
public class SwaggerConfig extends Application {
}
