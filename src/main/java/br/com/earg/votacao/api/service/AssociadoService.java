package br.com.earg.votacao.api.service;

import br.com.earg.votacao.api.domain.Associado;

import java.util.List;

public interface AssociadoService {

    List<Associado> consultarTodos();
}
