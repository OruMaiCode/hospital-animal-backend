package com.hospital.animal.cl.services.impl;

import com.hospital.animal.cl.dto.Day;
import com.hospital.animal.cl.services.firebase.FirebaseModel;
import com.hospital.animal.cl.services.interfaces.FirebaseRegisterService;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutionException;

@NoArgsConstructor
@Service
public class MedicalScheduleServiceImpl implements FirebaseRegisterService<Day> {

    @Autowired
    private FirebaseModel<Day> firebaseModel;


    @Override
    public List<Day> getAll() throws ExecutionException, InterruptedException {
        this.firebaseModel.build(Day.class);
        return firebaseModel.getAll();
    }

    @Override
    public Day get(String uid) throws ExecutionException, InterruptedException {
        this.firebaseModel.build(Day.class);
        return this.firebaseModel.get(uid);
    }

    @Override
    public Day create(Day day) throws ExecutionException, InterruptedException {
        this.firebaseModel.build(Day.class);
        return this.firebaseModel.create(day);
    }

    @Override
    public Day update(Day day) throws ExecutionException, InterruptedException {
        this.firebaseModel.build(Day.class);
        return this.firebaseModel.update(day);
    }

    @Override
    public Boolean delete(String uid) throws InterruptedException {
        this.firebaseModel.build(Day.class);
        return this.firebaseModel.delete(uid);
    }
}
