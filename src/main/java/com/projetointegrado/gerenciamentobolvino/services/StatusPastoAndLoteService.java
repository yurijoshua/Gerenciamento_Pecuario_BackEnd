package com.projetointegrado.gerenciamentobolvino.services;

import com.projetointegrado.gerenciamentobolvino.domain.Pasto;
import com.projetointegrado.gerenciamentobolvino.domain.Lote;
import com.projetointegrado.gerenciamentobolvino.domain.StatusPastoAndLote;
import com.projetointegrado.gerenciamentobolvino.dtos.StatusPastoAndLoteDTO;
import com.projetointegrado.gerenciamentobolvino.repositories.StatusPastoAndLoteRepository;
import com.projetointegrado.gerenciamentobolvino.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StatusPastoAndLoteService {

    @Autowired
    private StatusPastoAndLoteRepository repository;

    @Autowired
    private PastoService pastoService;

    @Autowired
    private LoteService LoteService;

    public StatusPastoAndLote findById(Integer id){
        Optional<StatusPastoAndLote> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto com id " + id + ", não foi encontrado."));
    }

    public List<Object> findAllLotesByPasto(Integer idPasto){
        return repository.findAllLotesByPasto(idPasto);
    }

    public List<Object> findAllPastosByLote(Integer idLote){
        return repository.findAllPastosByLote(idLote);
    }

    public StatusPastoAndLote create(Integer idPasto, Integer idLote, StatusPastoAndLote obj){
        obj.setId(null);
        Pasto pasto = pastoService.findById(idPasto);
        Lote Lote = LoteService.findById(idLote);
        obj.setPasto(pasto);
        obj.setLote(Lote);
        if(verific(obj.getLote().getId(),obj.getPasto().getId()))
        {
        	return repository.save(obj);
        }
        else
        {
        	throw new com.projetointegrado.gerenciamentobolvino.services.exceptions.DataIntegrityViolationException(
            "O lote em questão já possui um pasto relacionada a ele!");
        }
    }
    
    public boolean verific(Integer idLote,Integer idPasto){
    	List<Object> valid = repository.verific(idLote,idPasto);
        if(valid.isEmpty())
        {
        	return true;
        }
        else
        {
        	return false;
        }
    }

    public StatusPastoAndLote update(Integer id, StatusPastoAndLoteDTO objDto) {
        StatusPastoAndLote obj = findById(id);
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
