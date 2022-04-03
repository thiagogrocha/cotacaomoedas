package br.com.trochadev.cotacaomoedas.config;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class QuotationConfig {
    private final boolean simularEspera;
    private final boolean simularExcecao;

    @Inject
    public QuotationConfig(
            @ConfigProperty(name = "quotation.simular-espera", defaultValue = "false") boolean simularEspera,
            @ConfigProperty(name = "quotation.simular-excecao", defaultValue = "false") boolean simularExcecao) {
        this.simularEspera = simularEspera;
        this.simularExcecao = simularExcecao;
    }

    public boolean isSimularEspera() {
        return simularEspera;
    }

    public boolean isSimularExcecao() {
        return simularExcecao;
    }
}
