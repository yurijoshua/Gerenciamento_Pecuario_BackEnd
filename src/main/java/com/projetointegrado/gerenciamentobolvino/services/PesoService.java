package com.projetointegrado.gerenciamentobolvino.services;

import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.projetointegrado.gerenciamentobolvino.domain.Animal;
import com.projetointegrado.gerenciamentobolvino.domain.Peso;
import com.projetointegrado.gerenciamentobolvino.dtos.PesoDTO;
import com.projetointegrado.gerenciamentobolvino.repositories.PesoRepository;
import com.projetointegrado.gerenciamentobolvino.services.exceptions.ObjectNotFoundException;

@Service
public class PesoService {

    @Autowired
    private PesoRepository repository;

    @Autowired
    private AnimalService animalService;
    
    public Peso findById(Integer id){
        Optional<Peso> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto com id " + id + ", não foi encontrado."));
    }

    public List<Object> findAllbyAnimal(Integer idAnimal){
        return repository.findAllbyAnimal(idAnimal);
    }

    public Peso create(Integer idAnimal, Peso obj){
        obj.setId(null);
        Animal animal = animalService.findById(idAnimal);
        obj.setAnimal(animal);
        if(verific(obj.getDataPesagem(),obj.getAnimal().getId()))
        {
            return repository.save(obj);
        }
        else
        {
        	throw new com.projetointegrado.gerenciamentobolvino.services.exceptions.DataIntegrityViolationException(
            "Já foi realizado na data atual a pesagem do animal, caso necessário atulize a informação através da lista de pesos dele!");
        }
    }

    public Peso update(Integer id, PesoDTO objDto) {
        Peso obj = findById(id);
        obj.setPeso(objDto.getPeso());
        obj.setDataPesagem(objDto.getDataPesagem());
        if(verific(obj.getDataPesagem(),obj.getAnimal().getId()))
        {
            return repository.save(obj);
        }
        else
        {
        	throw new com.projetointegrado.gerenciamentobolvino.services.exceptions.DataIntegrityViolationException(
            "Já foi realizado na data atual a pesagem do animal, caso necessário atulize a informação através da lista de pesos dele!");
        }
    }
    
    public boolean verific(String dataPesagem, Integer idAnimal){
        List<Object> valid = repository.verific(dataPesagem,idAnimal);
        if(valid.isEmpty()) 
        {
        	return true;
        }
        else
        {
        	return false;
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
