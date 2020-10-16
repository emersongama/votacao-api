package br.com.earg.votacao.api.repository;

import br.com.earg.votacao.api.domain.Pauta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PautaRepository extends JpaRepository<Pauta, Long> {

}
