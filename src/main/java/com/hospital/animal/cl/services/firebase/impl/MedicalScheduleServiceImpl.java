package com.hospital.animal.cl.services.firebase.impl;

import com.hospital.animal.cl.dto.Day;
import com.hospital.animal.cl.services.firebase.commons.FirebaseModel;
import com.hospital.animal.cl.services.firebase.interfaces.FirebaseRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutionException;

@NoArgsConstructor
@Service
public class MedicalScheduleServiceImpl implements FirebaseRepository<Day> {

    @Autowired
    private FirebaseModel<Day> firebaseModel;
    @Override
    public List<Day> getAll() throws ExecutionException, InterruptedException {
        return build().getAll();
    }

    @Override
    public Day get(String uid) throws ExecutionException, InterruptedException {
        return this.build().get(uid);
    }

    @Override
    public Day create(Day day) throws ExecutionException, InterruptedException {
        return this.build().create(day);
    }

    @Override
    public Day update(Day day) throws ExecutionException, InterruptedException {
        return this.build().update(day);
    }

    @Override
    public Boolean delete(String uid) throws InterruptedException {

        return this.build().delete(uid);
    }
    public FirebaseModel<Day> build(){
        this.firebaseModel.build(Day.class);
        return this.firebaseModel;
    }
}
