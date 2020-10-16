package br.com.earg.votacao.api.shared.dto;

import br.com.earg.votacao.api.domain.Pauta;
import br.com.earg.votacao.api.domain.Votacao;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class AndamentoVotacaoDTO implements Serializable {

    private Long idVotacao;
    private Long idPauta;
    private String status;

    public Long getIdVotacao() {
        return idVotacao;
    }

    public Long getIdPauta() {
        return idPauta;
    }

    public String getStatus() {
        return status;
    }

    public AndamentoVotacaoDTO idVotacao(Long idVotacao) {
        this.idVotacao = idVotacao;
        return this;
    }

    public AndamentoVotacaoDTO idPauta(Long idPauta) {
        this.idPauta = idPauta;
        return this;
    }

    public AndamentoVotacaoDTO status(String status) {
        this.status = status;
        return this;
    }

    public static AndamentoVotacaoDTO obterDTO(Votacao votacao) {
        return new AndamentoVotacaoDTO()
                .idVotacao(votacao.getId())
                .idPauta(votacao.getPauta().getId())
                .status(votacao.getStatus().getDescricao());
    }

    public static List<AndamentoVotacaoDTO> obterListaDTO(List<Votacao> votacoes) {
        return votacoes.stream().map(AndamentoVotacaoDTO::obterDTO).collect(Collectors.toList());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AndamentoVotacaoDTO that = (AndamentoVotacaoDTO) o;
        return Objects.equals(idVotacao, that.idVotacao) &&
                Objects.equals(idPauta, that.idPauta) &&
                Objects.equals(status, that.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idVotacao, idPauta, status);
    }

    @Override
    public String toString() {
        return "AndamentoVotacaoDTO{" +
                "idVotacao=" + idVotacao +
                ", idPauta=" + idPauta +
                ", status='" + status + '\'' +
                '}';
    }
}
