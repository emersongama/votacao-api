package br.com.earg.votacao.api.shared.dto;

import br.com.earg.votacao.api.domain.Associado;
import br.com.earg.votacao.api.domain.Votacao;
import br.com.earg.votacao.api.domain.VotoAssociado;
import br.com.earg.votacao.api.domain.VotoAssociadoId;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

public class VotoAssociadoDTO implements Serializable {

    @NotNull
    private Long idVotacao;

    @NotBlank
    private String cpfAssociado;

    @NotBlank
    private String voto;

    public Long getIdVotacao() {
        return idVotacao;
    }

    public String getCpfAssociado() {
        return cpfAssociado;
    }

    public String getVoto() {
        return voto;
    }

    public VotoAssociadoDTO idVotacao(Long idVotacao) {
        this.idVotacao = idVotacao;
        return this;
    }

    public VotoAssociadoDTO cpfAssociado(String cpfAssociado) {
        this.cpfAssociado = cpfAssociado;
        return this;
    }

    public VotoAssociadoDTO voto(String voto) {
        this.voto = voto;
        return this;
    }

    public VotoAssociado obterVotacaoAssociado() {
        return new VotoAssociado()
                .id(new VotoAssociadoId()
                        .votacao(new Votacao().id(idVotacao))
                        .associado(new Associado().cpf(cpfAssociado)))
                .voto(voto.toUpperCase());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VotoAssociadoDTO that = (VotoAssociadoDTO) o;
        return Objects.equals(idVotacao, that.idVotacao) &&
                Objects.equals(cpfAssociado, that.cpfAssociado) &&
                Objects.equals(voto, that.voto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idVotacao, cpfAssociado, voto);
    }

    @Override
    public String toString() {
        return "VotoAssociadoDTO{" +
                "idVotacao=" + idVotacao +
                ", cpfAssociado=" + cpfAssociado +
                ", voto='" + voto + '\'' +
                '}';
    }
}
