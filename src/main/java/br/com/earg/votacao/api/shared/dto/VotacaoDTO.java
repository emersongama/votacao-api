package br.com.earg.votacao.api.shared.dto;

import br.com.earg.votacao.api.domain.Pauta;
import br.com.earg.votacao.api.domain.Votacao;
import br.com.earg.votacao.api.util.VerificadorUtil;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

import static br.com.earg.votacao.api.shared.Constantes.UM_MINUTO;

public class VotacaoDTO implements Serializable {

    @NotNull
    private Long idPauta;

    private Integer duracao;

    public Long getIdPauta() {
        return idPauta;
    }

    public Integer getDuracao() {
        return duracao;
    }

    public VotacaoDTO idPauta(Long idPauta) {
        this.idPauta = idPauta;
        return this;
    }

    public VotacaoDTO duracao(Integer duracao) {
        this.duracao = duracao;
        return this;
    }

    public Votacao obterVotacao() {
        return new Votacao()
                .pauta(new Pauta().id(idPauta))
                .duracao(VerificadorUtil.naoEstaNuloOuVazio(duracao) ? duracao : UM_MINUTO);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VotacaoDTO that = (VotacaoDTO) o;
        return Objects.equals(idPauta, that.idPauta) &&
                Objects.equals(duracao, that.duracao);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idPauta, duracao);
    }

    @Override
    public String toString() {
        return "VotacaoDTO{" +
                "idPauta=" + idPauta +
                ", duracao=" + duracao +
                '}';
    }
}
