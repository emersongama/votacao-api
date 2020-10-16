package br.com.earg.votacao.api.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "assemblea")
public class Assembleia implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column
    private String descricao;

    @Column(name = "data_hora")
    private LocalDateTime dataHora;

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

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Assembleia that = (Assembleia) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(descricao, that.descricao) &&
                Objects.equals(dataHora, that.dataHora);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, descricao, dataHora);
    }

    @Override
    public String toString() {
        return "Assembleia{" +
                "id=" + id +
                ", descricao='" + descricao + '\'' +
                ", dataHora=" + dataHora +
                '}';
    }
}
