package br.com.earg.votacao.api.domain;

import br.com.earg.votacao.api.shared.enums.StatusVotacao;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "votacao")
public class Votacao implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @OneToOne
    @JoinColumn(name = "id_pauta")
    private Pauta pauta;

    @Column
    private Integer duracao;

    @CreationTimestamp
    @Column(name = "data_hora_inicio", updatable = false)
    private LocalDateTime dataHoraInicio;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Pauta getPauta() {
        return pauta;
    }

    public void setPauta(Pauta pauta) {
        this.pauta = pauta;
    }

    public Integer getDuracao() {
        return duracao;
    }

    public void setDuracao(Integer duracao) {
        this.duracao = duracao;
    }

    public LocalDateTime getDataHoraInicio() {
        return dataHoraInicio;
    }

    public void setDataHoraInicio(LocalDateTime dataHoraInicio) {
        this.dataHoraInicio = dataHoraInicio;
    }

    public StatusVotacao getStatus() {
        return dataHoraInicio.plusMinutes(duracao).isBefore(LocalDateTime.now()) ? StatusVotacao.ENCERRADA : StatusVotacao.EM_ANDAMENTO;
    }

    public Votacao id(Long id) {
        this.id = id;
        return this;
    }

    public Votacao pauta(Pauta pauta) {
        this.pauta = pauta;
        return this;
    }

    public Votacao duracao(Integer duracao) {
        this.duracao = duracao;
        return this;
    }

    public Votacao dataHoraInicio(LocalDateTime dataHoraInicio) {
        this.dataHoraInicio = dataHoraInicio;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Votacao votacao = (Votacao) o;
        return Objects.equals(id, votacao.id) &&
                Objects.equals(pauta, votacao.pauta) &&
                Objects.equals(duracao, votacao.duracao) &&
                Objects.equals(dataHoraInicio, votacao.dataHoraInicio);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, pauta, duracao, dataHoraInicio);
    }

    @Override
    public String toString() {
        return "Votacao{" +
                "id=" + id +
                ", pauta=" + pauta +
                ", duracao=" + duracao +
                ", dataHoraInicio=" + dataHoraInicio +
                '}';
    }
}
