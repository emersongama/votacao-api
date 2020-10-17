package br.com.earg.votacao.api.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "pauta")
public class Pauta implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column
    private String descricao;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Pauta id(Long id) {
        this.id = id;
        return this;
    }

    public Pauta descricao(String descricao) {
        this.descricao = descricao;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pauta pauta = (Pauta) o;
        return Objects.equals(id, pauta.id) &&
                Objects.equals(descricao, pauta.descricao);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, descricao);
    }

    @Override
    public String toString() {
        return "Pauta{" +
                "id=" + id +
                ", descricao='" + descricao + '\'' +
                '}';
    }
}
