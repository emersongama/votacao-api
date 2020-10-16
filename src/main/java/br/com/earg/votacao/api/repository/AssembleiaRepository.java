package br.com.earg.votacao.api.repository;

import br.com.earg.votacao.api.domain.Assembleia;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AssembleiaRepository extends JpaRepository<Assembleia, Long> {

}
