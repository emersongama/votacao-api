package br.com.earg.votacao.api.repository;

import br.com.earg.votacao.api.domain.VotoAssociado;
import br.com.earg.votacao.api.domain.VotoAssociadoId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface VotoAssociadoRepository extends JpaRepository<VotoAssociado, VotoAssociadoId> {

    @Query("select count(vs) from VotoAssociado vs where vs.id.votacao.id = :idVotacao and vs.voto = :voto")
    Integer consultarQuantidadeVotos(Long idVotacao, String voto);
}
