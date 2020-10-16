package br.com.earg.votacao.api.shared.dto;

import br.com.earg.votacao.api.domain.Associado;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class AssociadoDTO implements Serializable {

    private String nome;
    private String cpf;

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public AssociadoDTO nome(String nome) {
        this.nome = nome;
        return this;
    }

    public AssociadoDTO cpf(String cpf) {
        this.cpf = cpf;
        return this;
    }

    public static AssociadoDTO obterDTO(Associado associado) {
        return new AssociadoDTO()
                .nome(associado.getNome())
                .cpf(associado.getCpf());
    }

    public static List<AssociadoDTO> obterListaDTO(List<Associado> associados) {
        return associados.stream().map(AssociadoDTO::obterDTO).collect(Collectors.toList());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AssociadoDTO that = (AssociadoDTO) o;
        return Objects.equals(nome, that.nome) &&
                Objects.equals(cpf, that.cpf);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome, cpf);
    }

    @Override
    public String toString() {
        return "AssociadoDTO{" +
                "nome='" + nome + '\'' +
                ", cpf='" + cpf + '\'' +
                '}';
    }
}
