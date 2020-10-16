package br.com.earg.votacao.api.service.impl;

import br.com.earg.votacao.api.domain.Associado;
import br.com.earg.votacao.api.domain.Associado_;
import br.com.earg.votacao.api.repository.AssociadoRepository;
import br.com.earg.votacao.api.service.AssociadoService;
import br.com.earg.votacao.api.service.exception.NaoEncontradoException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

import static br.com.earg.votacao.api.shared.Constantes.Mensagem.MSG_ASSOCIADO_NAO_ENCONTRADO;

@Service
public class AssociadoServiceImpl implements AssociadoService {

    private final AssociadoRepository associadoRepository;

    public AssociadoServiceImpl(AssociadoRepository associadoRepository) {
        this.associadoRepository = associadoRepository;
    }

    @Override
    public Associado consultarPorCpf(String cpf) {
        return associadoRepository.findByCpf(cpf)
                .orElseThrow(() -> new NaoEncontradoException(MSG_ASSOCIADO_NAO_ENCONTRADO));
    }

    @Override
    public List<Associado> consultarTodos() {
        return associadoRepository.findAll(Sort.by(Sort.Direction.ASC, Associado_.NOME));
    }
}
