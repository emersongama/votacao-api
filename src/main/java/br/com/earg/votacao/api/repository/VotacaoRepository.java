package br.com.earg.votacao.api.repository;

import br.com.earg.votacao.api.domain.Votacao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VotacaoRepository extends JpaRepository<Votacao, Long> {

}
