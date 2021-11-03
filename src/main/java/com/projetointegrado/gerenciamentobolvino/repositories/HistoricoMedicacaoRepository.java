package com.projetointegrado.gerenciamentobolvino.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.projetointegrado.gerenciamentobolvino.domain.HistoricoMedicacao;

@Repository
public interface HistoricoMedicacaoRepository extends JpaRepository<HistoricoMedicacao, Integer>{

	@Query(value = "SELECT a.brinco,a.raca,a.data_criacao,a.status FROM gerenciadorpecuario.animal as a inner join gerenciadorpecuario.historico_medicacao as hm ON hm.animal_id = a.id and hm.medicacao_id=?1", nativeQuery = true)
	List<Object> findallbovinosmedic(Integer idMedicacao);

	@Query(value = "SELECT hm.id,m.nome_medicacao,hm.dosagem,hm.data_aplicacao FROM gerenciadorpecuario.medicacao as m inner join gerenciadorpecuario.historico_medicacao as hm ON hm.medicacao_id = m.id and hm.animal_id=?1", nativeQuery = true)
	List<Object> findallmedicbovino(Integer idAnimal);
	
}
