package br.com.earg.votacao.api.repository;

import br.com.earg.votacao.api.domain.Pauta;
import br.com.earg.votacao.api.domain.Votacao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VotacaoRepository extends JpaRepository<Votacao, Long> {

    Optional<Votacao> findByPauta(Pauta pauta);
}
