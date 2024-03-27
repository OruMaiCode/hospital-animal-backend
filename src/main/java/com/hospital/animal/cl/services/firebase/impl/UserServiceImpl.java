package com.hospital.animal.cl.services.firebase.impl;

import com.hospital.animal.cl.dto.User;
import com.hospital.animal.cl.services.firebase.commons.FirebaseModel;
import com.hospital.animal.cl.services.firebase.interfaces.FirebaseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
@RequiredArgsConstructor
@Qualifier("User")
public class UserServiceImpl implements FirebaseRepository<User> {
    @Autowired
    private FirebaseModel<User> userFirebaseModel;

    public User register(User user){
        return user;
    }
    public User logIn() {
      return new User();
    }
    public void logOut() {
    }
    public void resetPassword() {

    }
    public User getByEmail(String email) throws ExecutionException, InterruptedException {
        return this.build().getByField("email",email);
    }
    @Override
    public List<User> getAll() throws InterruptedException, ExecutionException {
        return this.build().getAll();
    }

    @Override
    public User getByUid(String uid) throws InterruptedException, ExecutionException {
        return this.build().getByUid(uid);
    }

    @Override
    public User create(User user) throws InterruptedException, ExecutionException {
        return this.build().create(user);
    }

    @Override
    public User update(User user) throws ExecutionException, InterruptedException {
        return this.build().update(user);
    }

    @Override
    public Boolean delete(String uid) throws InterruptedException {
        return this.build().delete(uid);
    }


    public FirebaseModel<User> build() {
        this.userFirebaseModel.build(User.class);
        return this.userFirebaseModel;
    }
}
