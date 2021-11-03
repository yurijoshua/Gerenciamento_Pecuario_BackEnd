package com.projetointegrado.gerenciamentobolvino.resources;

import com.projetointegrado.gerenciamentobolvino.domain.StatusPastoAndLote;
import com.projetointegrado.gerenciamentobolvino.dtos.StatusPastoAndLoteDTO;
import com.projetointegrado.gerenciamentobolvino.services.StatusPastoAndLoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/statuspastoandlote")
public class StatusPastoAndLoteResource {

    @Autowired
    private StatusPastoAndLoteService service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<StatusPastoAndLote> findById(@PathVariable Integer id){
        StatusPastoAndLote obj = service.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @GetMapping(value = "/findallbypasto")
    public List<Object> findAllByPasto(@RequestParam(value = "pasto", defaultValue = "0") Integer idPasto){
        return service.findAllLotesByPasto(idPasto);
    }

    @GetMapping(value = "/findallbylote")
    public List<Object> findAllByLote(@RequestParam(value = "lote", defaultValue = "0") Integer idLote){
        return service.findAllPastosByLote(idLote);
    }

    @PostMapping
    public ResponseEntity<StatusPastoAndLote> create(@RequestParam(value = "pasto", defaultValue = "0") Integer idPasto,
                                                      @RequestParam(value = "lote", defaultValue = "0") Integer idLote,
                                                      @RequestBody StatusPastoAndLote obj){
        obj = service.create(idPasto, idLote, obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/statusPastoandlote/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).body(obj);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<StatusPastoAndLoteDTO> update(@PathVariable Integer id, @RequestBody StatusPastoAndLoteDTO objDto){
        StatusPastoAndLote newObj = service.update(id, objDto);
        return ResponseEntity.ok().body(new StatusPastoAndLoteDTO(newObj));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
