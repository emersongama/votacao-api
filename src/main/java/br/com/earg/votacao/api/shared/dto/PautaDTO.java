package br.com.earg.votacao.api.shared.dto;

import br.com.earg.votacao.api.domain.Pauta;
import br.com.earg.votacao.api.shared.enums.StatusPauta;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class PautaDTO implements Serializable {

    private Long id;

    @NotBlank
    private String descricao;

    private String status;

    public Long getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getStatus() {
        return status;
    }

    public PautaDTO id(Long id) {
        this.id = id;
        return this;
    }

    public PautaDTO descricao(String descricao) {
        this.descricao = descricao;
        return this;
    }

    public PautaDTO status(String status) {
        this.status = status;
        return this;
    }

    public Pauta obterPauta() {
        return new Pauta()
                .id(id)
                .descricao(descricao)
                .status(StatusPauta.AGUARDANDO_VOTACAO);
    }

    public static PautaDTO obterDTO(Pauta pauta) {
        return new PautaDTO()
                .id(pauta.getId())
                .descricao(pauta.getDescricao())
                .status(pauta.getStatus().getDescricao());
    }

    public static List<PautaDTO> obterListaDTO(List<Pauta> pautas) {
        return pautas.stream().map(PautaDTO::obterDTO).collect(Collectors.toList());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PautaDTO pautaDTO = (PautaDTO) o;
        return Objects.equals(id, pautaDTO.id) &&
                Objects.equals(descricao, pautaDTO.descricao) &&
                Objects.equals(status, pautaDTO.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, descricao, status);
    }

    @Override
    public String toString() {
        return "PautaDTO{" +
                "id=" + id +
                ", descricao='" + descricao + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
