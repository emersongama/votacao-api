package br.com.earg.votacao.api.shared.enums;

public enum StatusVotacao {

    EM_ANDAMENTO("Em andamento"),
    ENCERRADA("Encerrada");

    private final String descricao;

    StatusVotacao(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
