package com.hospital.animal.cl.services.firebase.interfaces;
import com.hospital.animal.cl.services.firebase.commons.FirebaseModel;
import com.hospital.animal.cl.dto.DatabaseRegister;

import java.util.List;
import java.util.concurrent.ExecutionException;

public interface FirebaseRepository<T extends DatabaseRegister> {
    List<T> getAll() throws InterruptedException, ExecutionException;
    T getByUid(String uid) throws InterruptedException, ExecutionException;
    T create(T model) throws InterruptedException, ExecutionException;
    T update(T model) throws ExecutionException, InterruptedException;
    Boolean delete(String uid) throws InterruptedException;
    FirebaseModel<T> build();

}
