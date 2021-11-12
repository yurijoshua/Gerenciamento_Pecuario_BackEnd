package com.projetointegrado.gerenciamentobolvino.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.projetointegrado.gerenciamentobolvino.domain.Venda;

@Repository
public interface VendaRepository extends JpaRepository<Venda, Integer>{

	@Query(value = "SELECT DISTINCT l.id,l.nome_lote,l.data_criacao FROM gerenciadorpecuario.lote as l INNER JOIN status_bovino_and_lote as sbl INNER JOIN animal a WHERE a.status='Vivo' AND sbl.tempo_final = '' AND l.id=sbl.lote_id", nativeQuery = true)
	List<Object> findallLotesAtivos();
	
	@Modifying
	@Transactional
	@Query(value = "UPDATE animal as a INNER JOIN status_bovino_and_lote AS sbl ON a.id = sbl.animal_id SET sbl.tempo_final = ?2, a.status = 'Vendido' WHERE a.status = 'Vivo' and sbl.tempo_final = '' and a.id = sbl.animal_id and sbl.lote_id = ?1", nativeQuery = true) 
	void alterstatusAfterVenda(Integer idLote, String dataVenda);
	
}
