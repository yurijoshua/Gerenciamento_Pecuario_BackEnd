package com.projetointegrado.gerenciamentobolvino.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.projetointegrado.gerenciamentobolvino.domain.Animal;

@Repository
public interface AnimalRepository extends JpaRepository<Animal, Integer>{
		
	@Query(value = "SELECT brinco FROM animal where status = 'Vivo' and brinco = ?1", nativeQuery = true)
	List<Object> verific(String brinco);
	
}
