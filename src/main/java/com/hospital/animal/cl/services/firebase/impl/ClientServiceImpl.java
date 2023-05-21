package com.hospital.animal.cl.services.firebase.impl;

import com.hospital.animal.cl.dto.Client;
import com.hospital.animal.cl.services.firebase.commons.FirebaseModel;
import com.hospital.animal.cl.services.firebase.interfaces.FirebaseRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.concurrent.ExecutionException;


@Service
@AllArgsConstructor
@Qualifier("Client")
public  class ClientServiceImpl implements FirebaseRepository<Client> {
    @Autowired
    private FirebaseModel<Client> clientFirebaseModel;

    @Override
    public List<Client> getAll() throws InterruptedException, ExecutionException {
      return this.build().getAll();

    }
    @Override
    public Client get(String uid) throws InterruptedException, ExecutionException {
        return this.build().get(uid);
    }


    @Override
    public Client create(Client client) throws InterruptedException, ExecutionException {
        return this.build().create(client);
    }


    @Override
    public Client update(Client client) throws ExecutionException, InterruptedException {
        return this.build().update(client);
    }

    @Override
    public Boolean delete(String uid) throws InterruptedException {
        return this.build().delete(uid);
    }
    public FirebaseModel<Client> build(){
        this.clientFirebaseModel.build(Client.class);
        return this.clientFirebaseModel;
    }
}
