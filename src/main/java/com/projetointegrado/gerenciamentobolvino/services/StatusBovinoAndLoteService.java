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

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Service
public class StatusBovinoAndLoteService {

    @Autowired
    private StatusBovinoAndLoteRepository repository;

    @Autowired
    private AnimalService animalService;

    @Autowired
    private LoteService LoteService;
    
    @PersistenceContext
    private EntityManager entityMananger;

    public List<Lote> findalllotebovino(Integer idAnimal){
        Query query = entityMananger.createNativeQuery("SELECT sbl.id,l.nome_lote,sbl.tempo_inicial,sbl.tempo_final FROM gerenciadorpecuario.lote as l inner join gerenciadorpecuario.status_bovino_and_lote as sbl ON sbl.lote_id = l.id and sbl.animal_id =:idAnimal").setParameter("idAnimal", idAnimal);
        return query.getResultList();
    }
    
    public List<Animal> findallbovinoslote(Integer idLote){
        Query query = entityMananger.createNativeQuery("SELECT a.brinco,a.raca,a.data_criacao,a.data_saida FROM gerenciadorpecuario.animal as a inner join gerenciadorpecuario.status_bovino_and_lote as sbl ON a.data_saida = '' and sbl.tempo_final = '' and sbl.animal_id = a.id and sbl.lote_id = :idLote order by a.data_criacao").setParameter("idLote", idLote);
        return query.getResultList();
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
            "O animal não pode ser relacionado ao lote pois já se encontra ativo em outro!");
        }
    }
    
    public boolean verific(String TempoFinal, Integer idAnimal){
        Query query = entityMananger.createNativeQuery("SELECT * FROM status_bovino_and_lote as sbl where tempo_final = :TempoFinal and sbl.animal_id = :idAnimal").setParameter("TempoFinal", TempoFinal).setParameter("idAnimal", idAnimal);
        if(query.getResultList().isEmpty()) 
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
