package com.hospital.animal.cl.services.impl;

import com.hospital.animal.cl.dto.User;
import com.hospital.animal.cl.services.interfaces.FirebaseRegisterService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements FirebaseRegisterService<User> {



    public void logIn() {

    }


    public void logOut() {

    }
    public void resetPassword() {

    }

    @Override
    public List<User> getAll() throws InterruptedException, ExecutionException {
        return null;
    }

    @Override
    public User get(String uid) throws InterruptedException, ExecutionException {
        return null;
    }

    @Override
    public User create(User model) throws InterruptedException, ExecutionException {
        return null;
    }

    @Override
    public User update(User model) throws ExecutionException, InterruptedException {
        return null;
    }

    @Override
    public Boolean delete(String uid) throws InterruptedException {
        return null;
    }
}
