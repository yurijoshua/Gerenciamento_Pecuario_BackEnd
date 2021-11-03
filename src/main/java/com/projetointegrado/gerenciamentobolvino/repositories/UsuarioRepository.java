package com.projetointegrado.gerenciamentobolvino.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.projetointegrado.gerenciamentobolvino.domain.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer>{

	@Query(value = "SELECT * FROM usuario where usuario = ?1", nativeQuery = true)
	List<Object> verific(String usuario);
	
}
