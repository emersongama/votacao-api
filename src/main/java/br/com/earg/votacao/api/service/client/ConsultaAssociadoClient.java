package br.com.earg.votacao.api.service.client;

import br.com.earg.votacao.api.shared.dto.InformacaoAssociadoDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "consultaAssociadoClient", url="https://user-info.herokuapp.com/users")
public interface ConsultaAssociadoClient {

    @GetMapping("/{cpf}")
    InformacaoAssociadoDTO obterInformacoesAssociado(@PathVariable String cpf);
}
