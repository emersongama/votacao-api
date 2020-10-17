package br.com.earg.votacao.api.service.impl;

import br.com.earg.votacao.api.domain.Associado;
import br.com.earg.votacao.api.domain.Pauta;
import br.com.earg.votacao.api.domain.Votacao;
import br.com.earg.votacao.api.domain.VotoAssociado;
import br.com.earg.votacao.api.repository.VotacaoRepository;
import br.com.earg.votacao.api.repository.VotoAssociadoRepository;
import br.com.earg.votacao.api.service.AssociadoService;
import br.com.earg.votacao.api.service.VotacaoService;
import br.com.earg.votacao.api.service.client.ConsultaAssociadoClient;
import br.com.earg.votacao.api.service.exception.NaoEncontradoException;
import br.com.earg.votacao.api.service.exception.NegocioException;
import br.com.earg.votacao.api.shared.dto.InformacaoAssociadoDTO;
import br.com.earg.votacao.api.shared.enums.IndicadorSimNao;
import br.com.earg.votacao.api.shared.enums.StatusAssociado;
import br.com.earg.votacao.api.shared.enums.StatusVotacao;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static br.com.earg.votacao.api.shared.Constantes.Mensagem.*;

@Service
public class VotacaoServiceImpl implements VotacaoService {

    private final VotacaoRepository votacaoRepository;
    private final VotoAssociadoRepository votoAssociadoRepository;
    private final AssociadoService associadoService;
    private final ConsultaAssociadoClient consultaAssociadoClient;

    public VotacaoServiceImpl(VotacaoRepository votacaoRepository,
                              VotoAssociadoRepository votoAssociadoRepository,
                              AssociadoService associadoService,
                              ConsultaAssociadoClient consultaAssociadoClient) {
        this.votacaoRepository = votacaoRepository;
        this.votoAssociadoRepository = votoAssociadoRepository;
        this.associadoService = associadoService;
        this.consultaAssociadoClient = consultaAssociadoClient;
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

    @Override
    public Votacao consultarPorPauta(Pauta pauta) {
        return votacaoRepository.findByPauta(pauta).orElse(null);
    }

    @Override
    public Integer consultarTotalVotosFavoraveis(Votacao votacao) {
        return votoAssociadoRepository.consultarQuantidadeVotos(votacao.getId(), IndicadorSimNao.SIM.name());
    }

    @Override
    public Integer consultarTotalVotosContra(Votacao votacao) {
        return votoAssociadoRepository.consultarQuantidadeVotos(votacao.getId(), IndicadorSimNao.NAO.name());
    }

    private void validarVoto(VotoAssociado votoAssociado) {
        validarSeVotacaoFoiEncerrada(votoAssociado);
        validarSeAssociadoEstaAptoAhVotar(votoAssociado);
        validarSeAssociadoJaVotou(votoAssociado);
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
        if (StatusVotacao.ENCERRADA.equals(votacao.getStatus())) {
            throw new NegocioException(MSG_VOTACAO_ENCERRADA);
        }
    }

    private void validarSeVotacaoEstaEmAndamento(Votacao votacao) {
        if (StatusVotacao.EM_ANDAMENTO.equals(votacao.getStatus())) {
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
        InformacaoAssociadoDTO informacaoAssociadoDTO = consultaAssociadoClient.obterInformacoesAssociado(votoAssociado.getId().getAssociado().getCpf());
        if (StatusAssociado.UNABLE_TO_VOTE.equals(informacaoAssociadoDTO.getStatus())) {
            throw new NegocioException(MSG_ASSOCIADO_NAO_ESTA_APTO_PARA_VOTAR);
        }
    }

    private void validarSeAssociadoJaVotou(VotoAssociado votoAssociado) {
        Optional<VotoAssociado> votacaoAssociadoConsultada = votoAssociadoRepository.findById(votoAssociado.getId());
        if (votacaoAssociadoConsultada.isPresent()) {
            String mensagem = String.format(MSG_ASSOCIADO_JA_VOTOU, votoAssociado.getId().getAssociado().getCpf());
            throw new NegocioException(mensagem);
        }
    }
}
