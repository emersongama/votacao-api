package br.com.earg.votacao.api.service.impl;

import br.com.earg.votacao.api.domain.Pauta;
import br.com.earg.votacao.api.domain.Votacao;
import br.com.earg.votacao.api.domain.VotacaoAssociado;
import br.com.earg.votacao.api.repository.VotacaoRepository;
import br.com.earg.votacao.api.service.VotacaoService;
import br.com.earg.votacao.api.service.exception.NaoEncontradoException;
import br.com.earg.votacao.api.service.exception.NegocioException;
import br.com.earg.votacao.api.shared.enums.StatusVotacao;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

import static br.com.earg.votacao.api.shared.Constantes.Mensagem.*;

@Service
public class VotacaoServiceImpl implements VotacaoService {

    private final VotacaoRepository votacaoRepository;

    public VotacaoServiceImpl(VotacaoRepository votacaoRepository) {
        this.votacaoRepository = votacaoRepository;
    }

    @Override
    public Votacao abrirVotacaoPauta(Votacao votacao) {
        validarSePodeAbrirVotacao(votacao.getPauta());
        return votacaoRepository.save(votacao);
    }

    @Override
    public Votacao realizarVotacao(VotacaoAssociado votacaoAssociado) {
        return null;
    }

    @Override
    public Votacao consultarPorId(Long id) {
        return votacaoRepository.findById(id)
                .orElseThrow(() -> new NaoEncontradoException(MSG_VOTACAO_NAO_ENCONTRADA));
    }

    private void validarSePodeAbrirVotacao(Pauta pauta) {
        Optional<Votacao> votacao = votacaoRepository.findById(pauta.getId());
        if (votacao.isPresent()) {
            validarSeVotacaoFoiEncerrada(votacao.get());
            validarSeVotacaoEstaEmAndamento(votacao.get());
        }
    }

    private void validarSeVotacaoFoiEncerrada(Votacao votacao) {
        if (votacao.getDataHoraInicio().plusMinutes(votacao.getDuracao()).isBefore(LocalDateTime.now())) {
            throw new NegocioException(MSG_VOTACAO_ENCERRADA);
        }
    }

    private void validarSeVotacaoEstaEmAndamento(Votacao votacao) {
        if (StatusVotacao.EM_VOTACAO.equals(votacao.getStatus())) {
            throw new NegocioException(MSG_VOTACAO_EM_ANDAMENTO);
        }
    }
}
