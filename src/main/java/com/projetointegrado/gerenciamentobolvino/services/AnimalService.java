package com.projetointegrado.gerenciamentobolvino.services;

import com.projetointegrado.gerenciamentobolvino.domain.Animal;
import com.projetointegrado.gerenciamentobolvino.domain.Lote;
import com.projetointegrado.gerenciamentobolvino.dtos.AnimalDTO;
import com.projetointegrado.gerenciamentobolvino.repositories.AnimalRepository;
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
public class AnimalService {

    @Autowired
    private AnimalRepository repository;

    @PersistenceContext
    private EntityManager entityMananger;
    
    public Animal findById(Integer id){
        Optional<Animal> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto com id " + id + ", não foi encontrado."));
    }

    public List<Animal> findAll(){
        return repository.findAll();
    }

    public Animal create(Animal obj){
        obj.setId(null);
        obj.setDataSaida("");
        if(verific(obj.getBrinco())) 
        {
        	return repository.save(obj);
        }
        else
        {
        	throw new com.projetointegrado.gerenciamentobolvino.services.exceptions.DataIntegrityViolationException(
                    "Objeto não pode ser cadastrado, pois o brinco está sendo utilizado!");
        }
    }
    
    public boolean verific(String brinco){
        Query query = entityMananger.createNativeQuery("SELECT brinco FROM animal where data_saida = '' and brinco = :brinco").setParameter("brinco", brinco);
        if(query.getResultList().isEmpty()) 
        {
        	return true;
        }
        else
        {
        	return false;
        }
    }

    public Animal update(Integer id, AnimalDTO objDto) {
        Animal obj = findById(id);
        obj.setBrinco(objDto.getBrinco());
        obj.setRaca(objDto.getRaca());
        obj.setDataCriacao(objDto.getDataCriacao());
        obj.setDataSaida(objDto.getDataSaida());
        return repository.save(obj);
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
