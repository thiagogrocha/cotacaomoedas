package br.com.trochadev.cotacaomoedas;

import br.com.trochadev.cotacaomoedas.config.QuotationConfig;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class QuotationConfigTest {

    @Test
    public void criarConfigFrutas_simularEspera() {
        // given
        QuotationConfig quotationConfig = new QuotationConfig(true, false);

        // when
        boolean simularEspera = quotationConfig.isSimularEspera();

        // then
        assertTrue(simularEspera, "Simular espera deve ser true");
    }

    @Test
    public void criarConfigFrutas_simularExcecao() {
        // given
        QuotationConfig quotationConfig = new QuotationConfig(false, true);

        // when
        boolean simularExcecao = quotationConfig.isSimularExcecao();

        // then
        assertTrue(simularExcecao, "Simular excecao deve ser true");
    }

}
