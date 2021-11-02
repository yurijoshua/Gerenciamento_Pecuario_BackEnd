package com.projetointegrado.gerenciamentobolvino.services;

import com.projetointegrado.gerenciamentobolvino.domain.Racao;
import com.projetointegrado.gerenciamentobolvino.domain.Lote;
import com.projetointegrado.gerenciamentobolvino.domain.StatusRacaoAndLote;
import com.projetointegrado.gerenciamentobolvino.dtos.StatusRacaoAndLoteDTO;
import com.projetointegrado.gerenciamentobolvino.repositories.StatusRacaoAndLoteRepository;
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
public class StatusRacaoAndLoteService {

    @Autowired
    private StatusRacaoAndLoteRepository repository;

    @Autowired
    private RacaoService racaoService;

    @Autowired
    private LoteService LoteService;
    
    @PersistenceContext
    private EntityManager entityMananger;

    public List<Lote> findallracoesbovino(Integer idRacao){
        Query query = entityMananger.createNativeQuery("SELECT l.nome_lote,l.data_criacao FROM gerenciadorpecuario.lote as l inner join gerenciadorpecuario.status_racao_and_lote as sbr ON sbr.tempo_final = '' and sbr.lote_id = l.id and sbr.racao_id  = :idRacao").setParameter("idRacao", idRacao);
        return query.getResultList();
    }

    public StatusRacaoAndLote findById(Integer id){
        Optional<StatusRacaoAndLote> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto com id " + id + ", não foi encontrado."));
    }

    public StatusRacaoAndLote create(Integer idRacao, Integer idLote, StatusRacaoAndLote obj){
        obj.setId(null);
        obj.setTempoFinal(""); 	
        Racao racao = racaoService.findById(idRacao);
        Lote Lote = LoteService.findById(idLote);
        obj.setRacao(racao);
        obj.setLote(Lote);
        if(verific(obj.getLote().getId(),obj.getRacao().getId()))
        {
        	return repository.save(obj);
        }
        else
        {
        	throw new com.projetointegrado.gerenciamentobolvino.services.exceptions.DataIntegrityViolationException(
            "O lote em questão já possui uma ração relacionada a ele!");
        }
    }

    public boolean verific(Integer idLote,Integer idRacao){
        Query query = entityMananger.createNativeQuery("SELECT * FROM gerenciadorpecuario.status_racao_and_lote WHERE tempo_final = '' and lote_id = :idLote and racao_id = :idRacao").setParameter("idLote", idLote).setParameter("idRacao", idRacao);
        if(query.getResultList().isEmpty()) 
        {
        	return true;
        }
        else
        {
        	return false;
        }
    }

    public StatusRacaoAndLote update(Integer id, StatusRacaoAndLoteDTO objDto) {
        StatusRacaoAndLote obj = findById(id);
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
