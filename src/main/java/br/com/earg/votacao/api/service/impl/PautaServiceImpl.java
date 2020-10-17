package br.com.earg.votacao.api.service.impl;

import br.com.earg.votacao.api.domain.Pauta;
import br.com.earg.votacao.api.domain.Pauta_;
import br.com.earg.votacao.api.domain.Votacao;
import br.com.earg.votacao.api.repository.PautaRepository;
import br.com.earg.votacao.api.service.PautaService;
import br.com.earg.votacao.api.service.VotacaoService;
import br.com.earg.votacao.api.service.exception.NaoEncontradoException;
import br.com.earg.votacao.api.shared.dto.PautaDTO;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static br.com.earg.votacao.api.shared.Constantes.Mensagem.MSG_PAUTA_NAO_ENCONTRADA;
import static br.com.earg.votacao.api.util.VerificadorUtil.naoEstaNulo;

@Service
public class PautaServiceImpl implements PautaService {

    private final PautaRepository pautaRepository;
    private final VotacaoService votacaoService;

    public PautaServiceImpl(PautaRepository pautaRepository, VotacaoService votacaoService) {
        this.pautaRepository = pautaRepository;
        this.votacaoService = votacaoService;
    }

    @Override
    public Pauta cadastrar(Pauta pauta) {
        return pautaRepository.save(pauta);
    }

    @Override
    public PautaDTO consultarPorId(Long id) {
        Pauta pauta = pautaRepository.findById(id).orElseThrow(() -> new NaoEncontradoException(MSG_PAUTA_NAO_ENCONTRADA));
        return obterPautaDTO(pauta);
    }

    @Override
    public List<PautaDTO> consultarTodas() {
        List<Pauta> pautas = pautaRepository.findAll(Sort.by(Sort.Direction.DESC, Pauta_.ID));
        return pautas.stream().map(this::obterPautaDTO).collect(Collectors.toList());
    }

    private PautaDTO obterPautaDTO(Pauta pauta) {
        PautaDTO pautaDTO = PautaDTO.obterDTO(pauta);
        Votacao votacaoPauta = votacaoService.consultarPorPauta(pauta);
        setarDadosVotacaoPautaCasoNecessario(pautaDTO, votacaoPauta);
        return pautaDTO;
    }

    private void setarDadosVotacaoPautaCasoNecessario(PautaDTO pautaDTO, Votacao votacaoPauta) {
        if (naoEstaNulo(votacaoPauta)) {
            pautaDTO
                    .totalVotosFavoraveis(votacaoService.consultarTotalVotosFavoraveis(votacaoPauta))
                    .totalVotosContra(votacaoService.consultarTotalVotosContra(votacaoPauta))
                    .idVotacao(votacaoPauta.getId());
        }
    }
}
