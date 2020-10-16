package br.com.earg.votacao.api.repository;

import br.com.earg.votacao.api.domain.Associado;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AssociadoRepository extends JpaRepository<Associado, Long> {

}
