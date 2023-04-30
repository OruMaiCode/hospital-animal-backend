package com.hospital.animal.cl.services.interfaces;
import com.hospital.animal.cl.services.firebase.FirebaseRegister;

import java.util.List;
import java.util.concurrent.ExecutionException;

public interface FirebaseRegisterService <T extends FirebaseRegister> {
    List<T> getAll() throws InterruptedException, ExecutionException;
    T get(String uid) throws InterruptedException, ExecutionException;
    T create(T model) throws InterruptedException, ExecutionException;
    T update(T model) throws ExecutionException, InterruptedException;
    Boolean delete(String uid) throws InterruptedException;

}
