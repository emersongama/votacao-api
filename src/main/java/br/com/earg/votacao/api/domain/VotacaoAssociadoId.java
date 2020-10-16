package br.com.earg.votacao.api.domain;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class VotacaoAssociadoId implements Serializable {

    @OneToOne
    @JoinColumn(name = "id_votacao")
    private Votacao votacao;

    @OneToOne
    @JoinColumn(name = "id_associado")
    private Associado associado;

    public Votacao getVotacao() {
        return votacao;
    }

    public void setVotacao(Votacao votacao) {
        this.votacao = votacao;
    }

    public Associado getAssociado() {
        return associado;
    }

    public void setAssociado(Associado associado) {
        this.associado = associado;
    }

    public VotacaoAssociadoId votacao(Votacao votacao) {
        this.votacao = votacao;
        return this;
    }

    public VotacaoAssociadoId associado(Associado associado) {
        this.associado = associado;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VotacaoAssociadoId that = (VotacaoAssociadoId) o;
        return Objects.equals(votacao, that.votacao) &&
                Objects.equals(associado, that.associado);
    }

    @Override
    public int hashCode() {
        return Objects.hash(votacao, associado);
    }

    @Override
    public String toString() {
        return "VotacaoAssociadoId{" +
                "votacao=" + votacao +
                ", associado=" + associado +
                '}';
    }
}
