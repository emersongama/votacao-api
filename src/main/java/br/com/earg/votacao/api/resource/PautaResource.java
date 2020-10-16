package br.com.earg.votacao.api.resource;

import br.com.earg.votacao.api.domain.Pauta;
import br.com.earg.votacao.api.service.PautaService;
import br.com.earg.votacao.api.service.VotacaoService;
import br.com.earg.votacao.api.shared.dto.PautaDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/api/pauta")
public class PautaResource {

    private final Logger LOGGER = LoggerFactory.getLogger(PautaResource.class);

    private final PautaService pautaService;
    private final VotacaoService votacaoService;

    public PautaResource(PautaService pautaService, VotacaoService votacaoService) {
        this.pautaService = pautaService;
        this.votacaoService = votacaoService;
    }

    @PostMapping
    public ResponseEntity<PautaDTO> cadastrar(@Valid @RequestBody PautaDTO pautaDTO) throws URISyntaxException {
        LOGGER.debug("Requisição REST para cadastrar uma nova pauta: {}", pautaDTO);
        Pauta pautaCadastrada = pautaService.cadastrar(pautaDTO.obterPauta());
        return ResponseEntity.created(new URI("/api/pauta/" + pautaCadastrada.getId()))
                .body(PautaDTO.obterDTO(pautaCadastrada));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PautaDTO> consultarPorId(@PathVariable Long id) {
        LOGGER.debug("Requisição REST para consultar uma pauta por id: {}", id);
        Pauta pautaConsultada = pautaService.consultarPorId(id);
        return ResponseEntity.ok(PautaDTO.obterDTO(pautaConsultada));
    }

    @GetMapping
    public ResponseEntity<List<PautaDTO>> consultarTodas() {
        LOGGER.debug("Requisição REST para consultar todas as pautas");
        List<Pauta> pautasConsultadas = pautaService.consultarTodas();
        return ResponseEntity.ok(PautaDTO.obterListaDTO(pautasConsultadas));
    }
}
