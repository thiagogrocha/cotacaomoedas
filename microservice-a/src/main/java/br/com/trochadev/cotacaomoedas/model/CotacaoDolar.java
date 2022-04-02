package br.com.trochadev.cotacaomoedas.model;

public class CotacaoDolar {
    double cotacaoCompra;
    double cotacaoVenda;
    String dataHoraCotacao;

    public double getCotacaoCompra() {
        return cotacaoCompra;
    }

    public void setCotacaoCompra(double cotacaoCompra) {
        this.cotacaoCompra = cotacaoCompra;
    }

    public double getCotacaoVenda() {
        return cotacaoVenda;
    }

    public void setCotacaoVenda(double cotacaoVenda) {
        this.cotacaoVenda = cotacaoVenda;
    }

    public String getDataHoraCotacao() {
        return dataHoraCotacao;
    }

    public void setDataHoraCotacao(String dataHoraCotacao) {
        this.dataHoraCotacao = dataHoraCotacao;
    }
}
