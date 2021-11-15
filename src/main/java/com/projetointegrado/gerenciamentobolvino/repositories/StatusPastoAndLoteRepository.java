package com.projetointegrado.gerenciamentobolvino.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.projetointegrado.gerenciamentobolvino.domain.StatusPastoAndLote;

@Repository
public interface StatusPastoAndLoteRepository extends JpaRepository<StatusPastoAndLote, Integer>{

	@Query(value = "SELECT l.nome_lote,l.data_criacao FROM gerenciadorpecuario.lote as l inner join gerenciadorpecuario.status_pasto_and_lote as spl ON spl.tempo_final = '' and spl.lote_id = l.id and spl.pasto_id = ?1", nativeQuery = true)
	List<Object> findAllLotesByPasto(Integer idPasto);
	
	@Query(value = "SELECT distinct spl.id,p.nome_pasto,spl.tempo_inicial,spl.tempo_final FROM gerenciadorpecuario.pasto as p inner join gerenciadorpecuario.status_pasto_and_lote as spl ON spl.pasto_id = p.id and spl.lote_id  = ?1", nativeQuery = true)
	List<Object> findAllPastosByLote(Integer idLote);

	@Query(value = "SELECT * FROM gerenciadorpecuario.status_pasto_and_lote WHERE tempo_final = '' and lote_id = ?1", nativeQuery = true)
	List<Object> verific(Integer idLote);
	
}
