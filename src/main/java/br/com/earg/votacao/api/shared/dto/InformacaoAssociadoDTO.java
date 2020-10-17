package br.com.earg.votacao.api.shared.dto;

import br.com.earg.votacao.api.shared.enums.StatusAssociado;

import java.io.Serializable;
import java.util.Objects;

public class InformacaoAssociadoDTO implements Serializable {

    private StatusAssociado status;

    public StatusAssociado getStatus() {
        return status;
    }

    public InformacaoAssociadoDTO setStatus(StatusAssociado status) {
        this.status = status;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InformacaoAssociadoDTO that = (InformacaoAssociadoDTO) o;
        return status == that.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(status);
    }

    @Override
    public String toString() {
        return "InformacaoAssociadoDTO{" +
                "status=" + status +
                '}';
    }
}
