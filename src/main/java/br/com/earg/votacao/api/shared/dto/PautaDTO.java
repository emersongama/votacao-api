package br.com.earg.votacao.api.shared.dto;

import br.com.earg.votacao.api.domain.Pauta;
import br.com.earg.votacao.api.shared.enums.StatusPauta;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static br.com.earg.votacao.api.util.VerificadorUtil.naoEstaNulo;

public class PautaDTO implements Serializable {

    private Long id;

    @NotBlank
    private String descricao;

    private String status;

    private Integer totalVotosFavoraveis;

    private Integer totalVotosContra;

    @JsonIgnore
    private Long idVotacao;

    public Long getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getStatus() {
        if (naoEstaNulo(idVotacao)) {
            return totalVotosFavoraveis > totalVotosContra ? StatusPauta.APROVADA.getDescricao() : StatusPauta.NAO_APROVADA.getDescricao();
        }
        return status;
    }

    public Integer getTotalVotosFavoraveis() {
        return totalVotosFavoraveis;
    }

    public Integer getTotalVotosContra() {
        return totalVotosContra;
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

    public PautaDTO totalVotosFavoraveis(Integer totalVotosFavoraveis) {
        this.totalVotosFavoraveis = totalVotosFavoraveis;
        return this;
    }

    public PautaDTO totalVotosContra(Integer totalVotosContra) {
        this.totalVotosContra = totalVotosContra;
        return this;
    }

    public PautaDTO idVotacao(Long idVotacao) {
        this.idVotacao = idVotacao;
        return this;
    }

    public Pauta obterPauta() {
        return new Pauta()
                .id(id)
                .descricao(descricao);
    }

    public static PautaDTO obterDTO(Pauta pauta) {
        return new PautaDTO()
                .id(pauta.getId())
                .descricao(pauta.getDescricao())
                .status(StatusPauta.AGUARDANDO_VOTACAO.getDescricao());
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
