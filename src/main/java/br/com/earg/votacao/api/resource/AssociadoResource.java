package br.com.earg.votacao.api.resource;

import br.com.earg.votacao.api.domain.Associado;
import br.com.earg.votacao.api.service.AssociadoService;
import br.com.earg.votacao.api.shared.dto.AssociadoDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/associado")
public class AssociadoResource {

    private final Logger LOGGER = LoggerFactory.getLogger(AssociadoResource.class);

    private final AssociadoService associadoService;

    public AssociadoResource(AssociadoService associadoService) {
        this.associadoService = associadoService;
    }

    @GetMapping
    public ResponseEntity<List<AssociadoDTO>> consultarTodas() {
        LOGGER.debug("Requisição REST para consultar todos os associados");
        List<Associado> associados = associadoService.consultarTodos();
        return ResponseEntity.ok(AssociadoDTO.obterListaDTO(associados));
    }
}
