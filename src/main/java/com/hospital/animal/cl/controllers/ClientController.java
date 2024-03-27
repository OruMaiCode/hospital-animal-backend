package com.hospital.animal.cl.controllers;

import com.hospital.animal.cl.dto.Client;
import com.hospital.animal.cl.errors.ErrorMessage;
import com.hospital.animal.cl.services.firebase.impl.ClientServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import jakarta.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController

@RequestMapping("/client")
public class ClientController {
    @Autowired
    private ClientServiceImpl clientService;

    @PostMapping
    public ResponseEntity<Client> createClient(@Valid @RequestBody Client client, BindingResult result){
        if(result.hasErrors()) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ErrorMessage.builder().build().formatMessage(result));
        Client createdClient= null;
        try {
            createdClient = clientService.create(client);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdClient);
        } catch (InterruptedException |ExecutionException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

    }
    @GetMapping
    public ResponseEntity<List<Client>> getAllClients()   {
        List<Client> clients=new ArrayList<>();
        try{
             clients =clientService.getAll();
            return ResponseEntity.status(HttpStatus.OK).body(clients);
        }catch (InterruptedException | ExecutionException ex){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

    }
    @GetMapping("/{uid}")
    public ResponseEntity<Client> getClient(@Valid  @PathVariable("uid") String uid){
        Client client = null;
        try {
            client = clientService.getByUid(uid);
        } catch (InterruptedException |ExecutionException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        return ResponseEntity.status(client == null ?HttpStatus.NOT_FOUND:HttpStatus.OK).body(client);
    }

    @PatchMapping
    public ResponseEntity<Client> updateClient(@Valid @RequestBody Client client, BindingResult result){
        if(result.hasErrors()) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ErrorMessage.builder().build().formatMessage(result));
        Client updatedClient = null;
        try {
            updatedClient = clientService.update(client);
        } catch (ExecutionException |InterruptedException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        return ResponseEntity.status(HttpStatus.OK).body(updatedClient);
    }
    @DeleteMapping(value = "/{uid}")
    public ResponseEntity<Void> deleteClient(@Valid  @PathVariable("uid") String uid){
        Boolean isDeleted = null;
        try {
            isDeleted = clientService.delete(uid);
        } catch (InterruptedException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        return new ResponseEntity<>(Boolean.TRUE.equals(isDeleted)?HttpStatus.OK:HttpStatus.NOT_FOUND);
    }

}
