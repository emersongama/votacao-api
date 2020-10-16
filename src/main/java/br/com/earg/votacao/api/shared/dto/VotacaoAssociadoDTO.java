package br.com.earg.votacao.api.shared.dto;

import br.com.earg.votacao.api.domain.Associado;
import br.com.earg.votacao.api.domain.Votacao;
import br.com.earg.votacao.api.domain.VotacaoAssociado;
import br.com.earg.votacao.api.domain.VotacaoAssociadoId;
import br.com.earg.votacao.api.shared.enums.IndicadorSimNao;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

public class VotacaoAssociadoDTO implements Serializable {

    @NotNull
    private Long idVotacao;

    @NotNull
    private Long idAssociado;

    @NotBlank
    private String voto;

    public Long getIdVotacao() {
        return idVotacao;
    }

    public Long getIdAssociado() {
        return idAssociado;
    }

    public String getVoto() {
        return voto;
    }

    public VotacaoAssociadoDTO idVotacao(Long idVotacao) {
        this.idVotacao = idVotacao;
        return this;
    }

    public VotacaoAssociadoDTO idAssociado(Long idAssociado) {
        this.idAssociado = idAssociado;
        return this;
    }

    public VotacaoAssociadoDTO voto(String voto) {
        this.voto = voto;
        return this;
    }

    public VotacaoAssociado obterVotacaoAssociado() {
        return new VotacaoAssociado()
                .id(new VotacaoAssociadoId()
                        .votacao(new Votacao().id(idVotacao))
                        .associado(new Associado().id(idAssociado)))
                .voto(IndicadorSimNao.valueOf(voto));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VotacaoAssociadoDTO that = (VotacaoAssociadoDTO) o;
        return Objects.equals(idVotacao, that.idVotacao) &&
                Objects.equals(idAssociado, that.idAssociado) &&
                Objects.equals(voto, that.voto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idVotacao, idAssociado, voto);
    }

    @Override
    public String toString() {
        return "VotacaoAssociadoDTO{" +
                "idVotacao=" + idVotacao +
                ", idAssociado=" + idAssociado +
                ", voto='" + voto + '\'' +
                '}';
    }
}
