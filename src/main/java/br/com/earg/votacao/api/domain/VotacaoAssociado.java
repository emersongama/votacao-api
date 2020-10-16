package br.com.earg.votacao.api.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "votacao_associado")
public class VotacaoAssociado implements Serializable {

    @EmbeddedId
    private VotacaoAssociadoId id;

    @Column
    private String voto;

    public VotacaoAssociadoId getId() {
        return id;
    }

    public void setId(VotacaoAssociadoId id) {
        this.id = id;
    }

    public String getVoto() {
        return voto;
    }

    public void setVoto(String voto) {
        this.voto = voto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VotacaoAssociado that = (VotacaoAssociado) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(voto, that.voto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, voto);
    }

    @Override
    public String toString() {
        return "VotacaoAssociado{" +
                "id=" + id +
                ", voto='" + voto + '\'' +
                '}';
    }
}
