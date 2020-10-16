package br.com.earg.votacao.api.domain;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import java.io.Serializable;

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
}
