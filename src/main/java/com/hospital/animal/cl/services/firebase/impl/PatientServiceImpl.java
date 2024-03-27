package com.hospital.animal.cl.services.firebase.impl;

import com.hospital.animal.cl.dto.Patient;
import com.hospital.animal.cl.services.firebase.commons.FirebaseModel;
import com.hospital.animal.cl.services.firebase.interfaces.FirebaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Service

public  class PatientServiceImpl implements FirebaseRepository<Patient> {

    @Autowired
    private FirebaseModel<Patient> patient;


    @Override
    public List<Patient> getAll() throws ExecutionException, InterruptedException {
        return this.build().getAll();
    }
    @Override
    public Patient getByUid(String uid) throws ExecutionException, InterruptedException {
        return this.build().getByUid(uid);
    }

    @Override
    public Patient create(Patient patient) throws InterruptedException {
        try{
        return this.build().create(patient);
        }catch (ExecutionException exception){
            return patient;
        }
    }

    @Override
    public Patient update(Patient model) throws ExecutionException, InterruptedException {
        return this.build().update(model);
    }

    @Override
    public Boolean delete(String uid) throws InterruptedException {

        return this.build().delete(uid);
    }
    public FirebaseModel<Patient> build(){
        this.patient.build(Patient.class);
        return this.patient;
    }
}
