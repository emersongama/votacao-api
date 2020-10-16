package br.com.earg.votacao.api.shared.enums;

public enum StatusPauta {

    AGUARDANDO_VOTACAO("Aguardando votação"),
    APROVADA("Aprovada"),
    NAO_APROVADA("Não aprovada");

    private final String descricao;

    StatusPauta(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
