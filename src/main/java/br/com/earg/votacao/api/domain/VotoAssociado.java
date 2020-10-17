package br.com.earg.votacao.api.domain;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "voto_associado")
public class VotoAssociado implements Serializable {

    @EmbeddedId
    private VotoAssociadoId id;

    @Column
    private String voto;

    public VotoAssociadoId getId() {
        return id;
    }

    public void setId(VotoAssociadoId id) {
        this.id = id;
    }

    public String getVoto() {
        return voto;
    }

    public void setVoto(String voto) {
        this.voto = voto;
    }

    public VotoAssociado id(VotoAssociadoId id) {
        this.id = id;
        return this;
    }

    public VotoAssociado voto(String voto) {
        this.voto = voto;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VotoAssociado that = (VotoAssociado) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(voto, that.voto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, voto);
    }

    @Override
    public String toString() {
        return "VotoAssociado{" +
                "id=" + id +
                ", voto='" + voto + '\'' +
                '}';
    }
}
