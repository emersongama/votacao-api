package br.com.earg.votacao.api.service.impl;

import br.com.earg.votacao.api.domain.Associado;
import br.com.earg.votacao.api.domain.Pauta;
import br.com.earg.votacao.api.domain.Votacao;
import br.com.earg.votacao.api.domain.VotoAssociado;
import br.com.earg.votacao.api.repository.VotoAssociadoRepository;
import br.com.earg.votacao.api.repository.VotacaoRepository;
import br.com.earg.votacao.api.service.AssociadoService;
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
    private final VotoAssociadoRepository votoAssociadoRepository;
    private final AssociadoService associadoService;

    public VotacaoServiceImpl(VotacaoRepository votacaoRepository,
                              VotoAssociadoRepository votoAssociadoRepository,
                              AssociadoService associadoService) {
        this.votacaoRepository = votacaoRepository;
        this.votoAssociadoRepository = votoAssociadoRepository;
        this.associadoService = associadoService;
    }

    @Override
    public Votacao abrirVotacaoPauta(Votacao votacao) {
        validarSePodeAbrirVotacao(votacao.getPauta());
        return votacaoRepository.save(votacao);
    }

    @Override
    public Votacao votar(VotoAssociado voto) {
        setarDadosVoto(voto);
        validarVoto(voto);
        votoAssociadoRepository.save(voto);
        return consultarPorId(voto.getId().getVotacao().getId());
    }

    @Override
    public Votacao consultarPorId(Long id) {
        return votacaoRepository.findById(id)
                .orElseThrow(() -> new NaoEncontradoException(MSG_VOTACAO_NAO_ENCONTRADA));
    }

    private void validarVoto(VotoAssociado votoAssociado) {
        validarSeAssociadoEstaAptoAhVotar(votoAssociado);
        validarSeAssociadoJaVotou(votoAssociado);
        validarSeVotacaoFoiEncerrada(votoAssociado);
    }

    private void validarSePodeAbrirVotacao(Pauta pauta) {
        Optional<Votacao> votacao = votacaoRepository.findById(pauta.getId());
        if (votacao.isPresent()) {
            validarSeVotacaoFoiEncerrada(votacao.get());
            validarSeVotacaoEstaEmAndamento(votacao.get());
        }
    }

    private void validarSeVotacaoFoiEncerrada(VotoAssociado votoAssociado) {
        validarSeVotacaoFoiEncerrada(votoAssociado.getId().getVotacao());
    }

    private void validarSeVotacaoFoiEncerrada(Votacao votacao) {
        if (StatusVotacao.ENCERRADA.equals(votacao.getStatus())
                || votacao.getDataHoraInicio().plusMinutes(votacao.getDuracao()).isBefore(LocalDateTime.now())) {
            throw new NegocioException(MSG_VOTACAO_ENCERRADA);
        }
    }

    private void validarSeVotacaoEstaEmAndamento(Votacao votacao) {
        if (StatusVotacao.EM_VOTACAO.equals(votacao.getStatus())) {
            throw new NegocioException(MSG_VOTACAO_EM_ANDAMENTO);
        }
    }

    private void setarDadosVoto(VotoAssociado votoAssociado) {
        Associado associado = associadoService.consultarPorCpf(votoAssociado.getId().getAssociado().getCpf());
        Votacao votacao = consultarPorId(votoAssociado.getId().getVotacao().getId());
        votoAssociado.getId().setAssociado(associado);
        votoAssociado.getId().setVotacao(votacao);
    }

    private void validarSeAssociadoEstaAptoAhVotar(VotoAssociado votoAssociado) {
        //TODO: tarefa bônus 1
    }

    private void validarSeAssociadoJaVotou(VotoAssociado votoAssociado) {
        Optional<VotoAssociado> votacaoAssociadoConsultada = votoAssociadoRepository.findById(votoAssociado.getId());
        if (votacaoAssociadoConsultada.isPresent()) {
            String mensagem = String.format(MSG_ASSOCIADO_JA_VOTOU, votoAssociado.getId().getAssociado().getCpf());
            throw new NegocioException(mensagem);
        }
    }
}
