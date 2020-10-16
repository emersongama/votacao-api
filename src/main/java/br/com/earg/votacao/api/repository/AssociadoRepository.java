package br.com.earg.votacao.api.repository;

import br.com.earg.votacao.api.domain.Associado;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AssociadoRepository extends JpaRepository<Associado, Long> {

    Optional<Associado> findByCpf(String cpf);
}
