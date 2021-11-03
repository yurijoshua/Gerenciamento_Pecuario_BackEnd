package com.projetointegrado.gerenciamentobolvino.services;

import com.projetointegrado.gerenciamentobolvino.domain.Animal;
import com.projetointegrado.gerenciamentobolvino.domain.StatusBovinoAndLote;
import com.projetointegrado.gerenciamentobolvino.domain.Lote;
import com.projetointegrado.gerenciamentobolvino.dtos.StatusBovinoAndLoteDTO;
import com.projetointegrado.gerenciamentobolvino.repositories.StatusBovinoAndLoteRepository;

import com.projetointegrado.gerenciamentobolvino.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StatusBovinoAndLoteService {

    @Autowired
    private StatusBovinoAndLoteRepository repository;

    @Autowired
    private AnimalService animalService;

    @Autowired
    private LoteService LoteService;

    public List<Object> findalllotebovino(Integer idAnimal){
        return repository.findalllotebovino(idAnimal);
    }
    
    public List<Object> findallbovinoslote(Integer idLote){
        return repository.findallbovinoslote(idLote);
    }
    
    public StatusBovinoAndLote findById(Integer id){
        Optional<StatusBovinoAndLote> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto com id " + id + ", não foi encontrado."));
    }
    
    public StatusBovinoAndLote create(Integer idAnimal, Integer idLote, StatusBovinoAndLote obj){
        obj.setId(null);
        obj.setTempoFinal("");
        Animal animal = animalService.findById(idAnimal);
        Lote Lote = LoteService.findById(idLote);
        obj.setAnimal(animal);
        obj.setLote(Lote);
        if(verific(obj.getTempoFinal(),obj.getAnimal().getId()))
        {
            return repository.save(obj);
        }
        else
        {
        	throw new com.projetointegrado.gerenciamentobolvino.services.exceptions.DataIntegrityViolationException(
            "O animal em questão já possui um lote relacionada a ele!");
        }
    }
    
    public boolean verific(String TempoFinal, Integer idAnimal){
    	List<Object> valid = repository.verific(TempoFinal, idAnimal);
        if(valid.isEmpty()) 
        {
        	return true;
        }
        else
        {
        	return false;
        }
    }

    public StatusBovinoAndLote update(Integer id, StatusBovinoAndLoteDTO objDto) {
        StatusBovinoAndLote obj = findById(id);
        obj.setTempoInicial(objDto.getTempoInicial());
        obj.setTempoFinal(objDto.getTempoFinal());
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
