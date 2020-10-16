package br.com.earg.votacao.api.service;

import br.com.earg.votacao.api.domain.Votacao;
import br.com.earg.votacao.api.domain.VotoAssociado;

public interface VotacaoService {

    Votacao abrirVotacaoPauta(Votacao votacao);

    Votacao consultarPorId(Long id);

    Votacao votar(VotoAssociado votoAssociado);
}
