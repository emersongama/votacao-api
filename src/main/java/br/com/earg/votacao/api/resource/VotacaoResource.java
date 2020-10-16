package br.com.earg.votacao.api.resource;

import br.com.earg.votacao.api.domain.Votacao;
import br.com.earg.votacao.api.service.VotacaoService;
import br.com.earg.votacao.api.shared.dto.AndamentoVotacaoDTO;
import br.com.earg.votacao.api.shared.dto.VotoAssociadoDTO;
import br.com.earg.votacao.api.shared.dto.VotacaoDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping("/api/votacao")
public class VotacaoResource {

    private final Logger LOGGER = LoggerFactory.getLogger(VotacaoResource.class);

    private final VotacaoService votacaoService;

    public VotacaoResource(VotacaoService votacaoService) {
        this.votacaoService = votacaoService;
    }

    @PostMapping
    public ResponseEntity<AndamentoVotacaoDTO> abrirVotacaoPauta(@Valid @RequestBody VotacaoDTO votacaoDTO) throws URISyntaxException {
        LOGGER.debug("Requisição REST para abrir uma votação de pauta: {}", votacaoDTO);
        Votacao votacao = votacaoService.abrirVotacaoPauta(votacaoDTO.obterVotacao());
        return ResponseEntity.created(new URI("/api/votacao/" + votacao.getId()))
                .body(AndamentoVotacaoDTO.obterDTO(votacao));
    }

    @PostMapping("/votar")
    public ResponseEntity<AndamentoVotacaoDTO> votar(@Valid @RequestBody VotoAssociadoDTO votoAssociadoDTO) throws URISyntaxException {
        LOGGER.debug("Requisição REST para realizar uma votação: {}", votoAssociadoDTO);
        Votacao votacao = votacaoService.votar(votoAssociadoDTO.obterVotacaoAssociado());
        return ResponseEntity.created(new URI("/api/votacao/" + votacao.getId()))
                .body(AndamentoVotacaoDTO.obterDTO(votacao));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AndamentoVotacaoDTO> consultarPorId(@PathVariable Long id) {
        LOGGER.debug("Requisição REST para consultar uma votação por id: {}", id);
        Votacao votacao = votacaoService.consultarPorId(id);
        return ResponseEntity.ok(AndamentoVotacaoDTO.obterDTO(votacao));
    }
}
