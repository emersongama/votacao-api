package br.com.earg.votacao.api.shared.enums;

public enum IndicadorSimNao {

    SIM("Sim"),
    NAO("Não");

    private final String descricao;

    IndicadorSimNao(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
