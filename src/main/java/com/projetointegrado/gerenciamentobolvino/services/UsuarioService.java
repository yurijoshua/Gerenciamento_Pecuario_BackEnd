package com.projetointegrado.gerenciamentobolvino.services;

import com.projetointegrado.gerenciamentobolvino.domain.Usuario;
import com.projetointegrado.gerenciamentobolvino.dtos.UsuarioDTO;
import com.projetointegrado.gerenciamentobolvino.repositories.UsuarioRepository;
import com.projetointegrado.gerenciamentobolvino.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;
    
    public Usuario findById(Integer id){
        Optional<Usuario> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto com id " + id + ", não foi encontrado."));
    }

    public List<Usuario> findAll(){
        return repository.findAll();
    }

    public Usuario create(Usuario obj){
        LocalDateTime myDateObj = LocalDateTime.now();  
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd/MM/yyyy");  
        String formattedDate = myDateObj.format(myFormatObj); 
        obj.setId(null);
        obj.setDataCriacao(formattedDate);
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
        List<Object> valid = repository.verific(usuario);
        if(valid.isEmpty()) 
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
