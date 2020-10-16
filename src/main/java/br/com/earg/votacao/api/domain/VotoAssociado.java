package br.com.earg.votacao.api.domain;

import br.com.earg.votacao.api.shared.enums.IndicadorSimNao;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "voto_associado")
public class VotoAssociado implements Serializable {

    @EmbeddedId
    private VotoAssociadoId id;

    @Column
    @Enumerated(EnumType.STRING)
    private IndicadorSimNao voto;

    public VotoAssociadoId getId() {
        return id;
    }

    public void setId(VotoAssociadoId id) {
        this.id = id;
    }

    public IndicadorSimNao getVoto() {
        return voto;
    }

    public void setVoto(IndicadorSimNao voto) {
        this.voto = voto;
    }

    public VotoAssociado id(VotoAssociadoId id) {
        this.id = id;
        return this;
    }

    public VotoAssociado voto(IndicadorSimNao voto) {
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
