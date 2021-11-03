package com.projetointegrado.gerenciamentobolvino.resources;

import com.projetointegrado.gerenciamentobolvino.domain.StatusBovinoAndLote;
import com.projetointegrado.gerenciamentobolvino.dtos.StatusBovinoAndLoteDTO;
import com.projetointegrado.gerenciamentobolvino.services.StatusBovinoAndLoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/statusbovinoandlote")
public class StatusBovinoAndLoteResource {

    @Autowired
    private StatusBovinoAndLoteService service;
    
    @GetMapping(value = "/{id}")
    public ResponseEntity<StatusBovinoAndLote> findById(@PathVariable Integer id){
        StatusBovinoAndLote obj = service.findById(id);
        return ResponseEntity.ok().body(obj);
    }
    
    @GetMapping(value = "/findallbyanimal")
    public List<Object> findAllByAnimal(@RequestParam(value = "animal", defaultValue = "0") Integer idAnimal){
        return service.findalllotebovino(idAnimal);
    }

    @GetMapping(value = "/findallbylote")
    public List<Object> findAllByLote(@RequestParam(value = "lote", defaultValue = "0") Integer idLote){
    	return service.findallbovinoslote(idLote);
    }

    @PostMapping
    public ResponseEntity<StatusBovinoAndLote> create(@RequestParam(value = "animal", defaultValue = "0") Integer idAnimal,
                                                     @RequestParam(value = "lote", defaultValue = "0") Integer idLote,
                                                     @RequestBody StatusBovinoAndLote obj){
        obj = service.create(idAnimal, idLote, obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/statusbovinoandlote/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).body(obj);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<StatusBovinoAndLoteDTO> update(@PathVariable Integer id, @RequestBody StatusBovinoAndLoteDTO objDto){
        StatusBovinoAndLote newObj = service.update(id, objDto);
        return ResponseEntity.ok().body(new StatusBovinoAndLoteDTO(newObj));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
