package com.example.digital.controllers;

import com.example.digital.exceptions.ResourceNotFoundException;
import com.example.digital.models.Patito;
import com.example.digital.repository.PatitoRepository;
import javafx.application.Application;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import javax.xml.ws.Response;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/v1")
public class PatitoController {
    @Autowired
    private PatitoRepository repository;


    @GetMapping(value = "/patitos")
    public List<Patito> listAll(){
        return repository.findAll();
    }

    @PostMapping(value = "/patitos", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Patito savePatito(@RequestBody Patito patito){
        patito.setBorrado(false);
        return repository.save(patito);
    }

    @GetMapping("/patitos/{id}")
    public ResponseEntity<Patito> findPatitoById(@PathVariable Integer id) throws ResourceNotFoundException {
        Patito patito =  repository.findById(id)
                .orElseThrow( ()-> new ResourceNotFoundException("No existe el patito con el ID: " + id));
        return ResponseEntity.ok(patito);
    }
    @PutMapping(value = "patitos/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public  ResponseEntity<Patito> updatePatito (@PathVariable Integer id, @RequestBody Patito patito) throws ResourceNotFoundException {
        Patito newPatito = repository.findById(id).
                orElseThrow( ()-> new ResourceNotFoundException("No existe el patito con el ID: " + id));

        newPatito.setColor(patito.getColor());
        newPatito.setTamaño(patito.getTamaño());
        newPatito.setPrecio(patito.getPrecio());
        newPatito.setCantidad(patito.getCantidad());

        Patito updatedPatito = repository.save(newPatito);
        return ResponseEntity.ok(updatedPatito);
    }

    @DeleteMapping("patitos/{id}")
    public ResponseEntity<Map<String,Boolean>> deletePatito(@PathVariable Integer id) throws ResourceNotFoundException {
        Patito newPatito = repository.findById(id)
                .orElseThrow( ()-> new ResourceNotFoundException("No existe el patito con el ID: " + id));
        repository.delete(newPatito);
        Map<String,Boolean> res = new HashMap<>();
        res.put("eliminar",Boolean.TRUE);
        return ResponseEntity.ok(res);
    }

}
