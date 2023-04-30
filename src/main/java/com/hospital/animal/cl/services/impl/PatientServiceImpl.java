package com.hospital.animal.cl.services.impl;

import com.hospital.animal.cl.dto.Patient;
import com.hospital.animal.cl.services.firebase.FirebaseModel;
import com.hospital.animal.cl.services.interfaces.FirebaseRegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Service

public  class PatientServiceImpl implements FirebaseRegisterService<Patient> {

    @Autowired
    private FirebaseModel<Patient> patient;


    @Override
    public List<Patient> getAll() throws ExecutionException, InterruptedException {
        this.patient.build(Patient.class);
        return this.patient.getAll();
    }
    @Override
    public Patient get(String uid) throws ExecutionException, InterruptedException {
        this.patient.build(Patient.class);
        return this.patient.get(uid);
    }

    @Override
    public Patient create(Patient patient) throws InterruptedException {
        this.patient.build(Patient.class);
        try{

        return this.patient.create(patient);
        }catch (ExecutionException exception){
            return patient;
        }
    }

    @Override
    public Patient update(Patient model) throws ExecutionException, InterruptedException {

        this.patient.build(Patient.class);
        return this.patient.update(model);
    }

    @Override
    public Boolean delete(String uid) throws InterruptedException {
        this.patient.build(Patient.class);
        return this.patient.delete(uid);
    }
}
