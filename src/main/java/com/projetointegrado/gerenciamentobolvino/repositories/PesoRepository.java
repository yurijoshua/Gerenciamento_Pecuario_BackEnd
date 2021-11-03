package com.projetointegrado.gerenciamentobolvino.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.projetointegrado.gerenciamentobolvino.domain.Peso;

@Repository
public interface PesoRepository extends JpaRepository<Peso, Integer>{

	@Query(value = "SELECT * FROM peso where animal_id = ?1 order by data_pesagem", nativeQuery = true)
	List<Object> findAllbyAnimal(Integer idAnimal);

	@Query(value = "SELECT * FROM peso where data_pesagem = ?1 and animal_id = ?2", nativeQuery = true)
	List<Object> verific(String dataPesagem,Integer idAnimal);
	
}
