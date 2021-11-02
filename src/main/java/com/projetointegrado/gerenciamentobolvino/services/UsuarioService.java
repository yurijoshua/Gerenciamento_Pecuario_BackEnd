package com.projetointegrado.gerenciamentobolvino.services;

import com.projetointegrado.gerenciamentobolvino.domain.Animal;
import com.projetointegrado.gerenciamentobolvino.domain.Usuario;
import com.projetointegrado.gerenciamentobolvino.dtos.UsuarioDTO;
import com.projetointegrado.gerenciamentobolvino.repositories.UsuarioRepository;
import com.projetointegrado.gerenciamentobolvino.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    @PersistenceContext
    private EntityManager entityMananger;
    
    public Usuario findById(Integer id){
        Optional<Usuario> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto com id " + id + ", não foi encontrado."));
    }

    public List<Usuario> findAll(){
        return repository.findAll();
    }

    public Usuario create(Usuario obj){
        obj.setId(null);
        if(verific(obj.getUsuario()))
        {
            return repository.save(obj);
        }
        else
        {
        	throw new com.projetointegrado.gerenciamentobolvino.services.exceptions.DataIntegrityViolationException(
            "O usuário já está sendo utilizado, favor utilizar outro nome para o campo usuário!");
        }
    }
    
    public boolean verific(String usuario){
        Query query = entityMananger.createNativeQuery("SELECT * FROM usuario where usuario = :usuario").setParameter("usuario", usuario);
        if(query.getResultList().isEmpty()) 
        {
        	return true;
        }
        else
        {
        	return false;
        }
    }

    public Usuario update(Integer id, UsuarioDTO objDto) {
        Usuario obj = findById(id);
        obj.setNome(objDto.getNome());
        obj.setUsuario(objDto.getUsuario());
        obj.setSenha(objDto.getSenha());
        obj.setDataCriacao(objDto.getDataCriacao());
        if(verific(obj.getUsuario()))
        {
            return repository.save(obj);
        }
        else
        {
        	throw new com.projetointegrado.gerenciamentobolvino.services.exceptions.DataIntegrityViolationException(
            "O usuário já está sendo utilizado, favor utilizar outro nome para o campo usuário!");
        }
    }
  
    
    public void delete(Integer id) {
        findById(id);
        try {
            repository.deleteById(id);
        }catch (DataIntegrityViolationException e){
            throw new com.projetointegrado.gerenciamentobolvino.services.exceptions.DataIntegrityViolationException(
                    "Objeto não pode ser deletado. Possui dependencias");
        }
    }
}
