package com.hospital.animal.cl.controllers;

import com.hospital.animal.cl.dto.Day;
import com.hospital.animal.cl.errors.ErrorMessage;
import com.hospital.animal.cl.services.impl.MedicalScheduleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import javax.validation.Valid;
import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/medical-schedule")
public class MedicalScheduleController {

    @Autowired
    private MedicalScheduleServiceImpl medicalScheduleService;

    @GetMapping
    public ResponseEntity<List<Day>> getAllMedicalSchedules(){
        List<Day> days= null;
        try {
            days = this.medicalScheduleService.getAll();
            return ResponseEntity.status(HttpStatus.OK).body(days);
        } catch (InterruptedException |ExecutionException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

    }
    @GetMapping("/{uid}")
    public ResponseEntity<Day> getClient(@Valid  @PathVariable("uid") String uid){
        Day day = null;
        try {
            day = this.medicalScheduleService.get(uid);
            return ResponseEntity.status(day == null ?HttpStatus.NOT_FOUND:HttpStatus.OK).body(day);
        } catch (InterruptedException |ExecutionException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

    }
    @PostMapping
    public ResponseEntity<Day> createClient(@Valid @RequestBody Day day, BindingResult result){
        if(result.hasErrors()) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ErrorMessage.builder().build().formatMessage(result));
        Day createdDay= null;
        try {
            createdDay = this.medicalScheduleService.create(day);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdDay);
        } catch (InterruptedException |ExecutionException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

    }
    @PatchMapping
    public ResponseEntity<Day> updateClient(@Valid @RequestBody Day day, BindingResult result){
        if(result.hasErrors()) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ErrorMessage.builder().build().formatMessage(result));
        Day updatedDay = null;
        try {
            updatedDay = this.medicalScheduleService.update(day);
            return ResponseEntity.status(HttpStatus.OK).body(updatedDay);
        } catch (ExecutionException |InterruptedException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

    }
    @DeleteMapping(value = "/{uid}")
    public ResponseEntity<Void> deleteClient(@Valid  @PathVariable("uid") String uid){
        Boolean isDeleted = null;
        try {
            isDeleted = this.medicalScheduleService.delete(uid);
            return new ResponseEntity<>(Boolean.TRUE.equals(isDeleted)?HttpStatus.OK:HttpStatus.NOT_FOUND);
        } catch (InterruptedException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

    }
}
