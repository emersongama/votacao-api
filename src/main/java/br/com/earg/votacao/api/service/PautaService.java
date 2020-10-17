package br.com.earg.votacao.api.service;

import br.com.earg.votacao.api.domain.Pauta;
import br.com.earg.votacao.api.shared.dto.PautaDTO;

import java.util.List;

public interface PautaService {

    Pauta cadastrar(Pauta pauta);

    PautaDTO consultarPorId(Long id);

    List<PautaDTO> consultarTodas();
}
