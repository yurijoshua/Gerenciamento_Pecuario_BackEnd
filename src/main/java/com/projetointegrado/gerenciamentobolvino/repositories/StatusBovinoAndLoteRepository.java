package com.projetointegrado.gerenciamentobolvino.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.projetointegrado.gerenciamentobolvino.domain.StatusBovinoAndLote;

@Repository
public interface StatusBovinoAndLoteRepository extends JpaRepository<StatusBovinoAndLote, Integer>{

	@Query(value = "SELECT a.brinco,a.raca,a.data_criacao,a.status FROM gerenciadorpecuario.animal as a inner join gerenciadorpecuario.status_bovino_and_lote as sbl ON a.status = 'Vivo' and sbl.tempo_final = '' and sbl.animal_id = a.id and sbl.lote_id = ?1", nativeQuery = true)
	List<Object> findallbovinoslote(Integer idLote);

	@Query(value = "SELECT distinct sbl.id,l.nome_lote,sbl.tempo_inicial,sbl.tempo_final FROM gerenciadorpecuario.lote as l inner join gerenciadorpecuario.status_bovino_and_lote as sbl ON sbl.lote_id = l.id and sbl.animal_id = ?1", nativeQuery = true)
	List<Object> findalllotebovino(Integer idAnimal);

	@Query(value = "SELECT * FROM status_bovino_and_lote as sbl where tempo_final = ?1 and sbl.animal_id = ?2", nativeQuery = true)
	List<Object> verific(String tempoInicial,Integer tempoFinal );

}
