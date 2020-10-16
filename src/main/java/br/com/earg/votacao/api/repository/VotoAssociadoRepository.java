package br.com.earg.votacao.api.repository;

import br.com.earg.votacao.api.domain.VotoAssociado;
import br.com.earg.votacao.api.domain.VotoAssociadoId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VotoAssociadoRepository extends JpaRepository<VotoAssociado, VotoAssociadoId> {

}
