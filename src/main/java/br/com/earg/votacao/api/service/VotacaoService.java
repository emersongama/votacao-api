package br.com.earg.votacao.api.service;

import br.com.earg.votacao.api.domain.Votacao;
import br.com.earg.votacao.api.domain.VotacaoAssociado;

public interface VotacaoService {

    Votacao abrirVotacaoPauta(Votacao votacao);

    Votacao consultarPorId(Long id);

    Votacao realizarVotacao(VotacaoAssociado votacaoAssociado);
}
