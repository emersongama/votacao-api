package br.com.earg.votacao.api.service;

import br.com.earg.votacao.api.domain.Pauta;

import java.util.List;

public interface PautaService {

    Pauta cadastrar(Pauta pauta);

    Pauta consultarPorId(Long id);

    List<Pauta> consultarTodas();
}
