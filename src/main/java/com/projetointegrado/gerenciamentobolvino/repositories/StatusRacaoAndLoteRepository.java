package com.projetointegrado.gerenciamentobolvino.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.projetointegrado.gerenciamentobolvino.domain.StatusRacaoAndLote;

@Repository
public interface StatusRacaoAndLoteRepository extends JpaRepository<StatusRacaoAndLote, Integer>{
	
	@Query(value = "SELECT l.nome_lote,l.data_criacao FROM gerenciadorpecuario.lote as l inner join gerenciadorpecuario.status_racao_and_lote as sbr ON sbr.tempo_final = '' and sbr.lote_id = l.id and sbr.racao_id  = ?1", nativeQuery = true)
	List<Object> findalllotesracao(Integer idRacao);
	
	@Query(value = "SELECT distinct sbr.id,r.ingredientes,sbr.tempo_inicial,sbr.tempo_final FROM gerenciadorpecuario.racao as r inner join gerenciadorpecuario.status_racao_and_lote as sbr ON racao_id = r.id and sbr.lote_id = ?1", nativeQuery = true)
	List<Object> findallracoeslote(Integer idLote);

	@Query(value = "SELECT * FROM gerenciadorpecuario.status_racao_and_lote WHERE tempo_final = '' and lote_id = ?1 and racao_id = ?2", nativeQuery = true)
	List<Object> verific(Integer idLote,Integer idRacao);
	
}
