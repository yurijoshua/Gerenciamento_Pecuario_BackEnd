package com.projetointegrado.gerenciamentobolvino.services;

import com.projetointegrado.gerenciamentobolvino.domain.Usuario;
import com.projetointegrado.gerenciamentobolvino.domain.Venda;
import com.projetointegrado.gerenciamentobolvino.dtos.VendaDTO;
import com.projetointegrado.gerenciamentobolvino.repositories.VendaRepository;
import com.projetointegrado.gerenciamentobolvino.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
public class VendaService {

    @Autowired
    private VendaRepository repository;

    @Autowired
    private UsuarioService usuarioService;

    public Venda findById(Integer id){
        Optional<Venda> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto com id " + id + ", não foi encontrado."));
    }

    public List<Object> findallLotesAtivos(){
    	return repository.findallLotesAtivos();
    }
    
    public List<Venda> findAll(){
        List<Venda> list = repository.findAll();
        return list;
    }

    public Venda create(Integer idUsuario,Integer idLote, Float peso, Venda obj){
        obj.setId(null);
        float valorlote = obj.getValorArroba()*peso;
        obj.setValorLote(valorlote);
        Usuario usuario = usuarioService.findById(idUsuario);
        obj.setUsuario(usuario);
        
        repository.alterstatusAfterVenda(idLote,obj.getDataVenda());
       
        return repository.save(obj);
    }

    public Venda update(Integer id, VendaDTO objDto) {
        Venda obj = findById(id);
        obj.setValorArroba(objDto.getValorArroba());
        obj.setDataVenda(objDto.getDataVenda());
        obj.setRegistroComprador(objDto.getRegistroComprador());
        obj.setValorLote(objDto.getValorLote());
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
