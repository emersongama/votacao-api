package br.com.earg.votacao.api.service.impl;

import br.com.earg.votacao.api.domain.Pauta;
import br.com.earg.votacao.api.domain.Pauta_;
import br.com.earg.votacao.api.repository.PautaRepository;
import br.com.earg.votacao.api.service.PautaService;
import br.com.earg.votacao.api.service.exception.NaoEncontradoException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

import static br.com.earg.votacao.api.shared.Constantes.Mensagem.MSG_PAUTA_NAO_ENCONTRADA;

@Service
public class PautaServiceImpl implements PautaService {

    private final PautaRepository pautaRepository;

    public PautaServiceImpl(PautaRepository pautaRepository) {
        this.pautaRepository = pautaRepository;
    }

    @Override
    public Pauta cadastrar(Pauta pauta) {
        return pautaRepository.save(pauta);
    }

    @Override
    public Pauta consultarPorId(Long id) {
        return pautaRepository.findById(id)
                .orElseThrow(() -> new NaoEncontradoException(MSG_PAUTA_NAO_ENCONTRADA));
    }

    @Override
    public List<Pauta> consultarTodas() {
        return pautaRepository.findAll(Sort.by(Sort.Direction.DESC, Pauta_.ID));
    }
}
