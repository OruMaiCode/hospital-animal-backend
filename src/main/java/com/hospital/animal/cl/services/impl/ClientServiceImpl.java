package com.hospital.animal.cl.services.impl;

import com.hospital.animal.cl.dto.Client;
import com.hospital.animal.cl.services.firebase.FirebaseModel;
import com.hospital.animal.cl.services.interfaces.FirebaseRegisterService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.concurrent.ExecutionException;


@Service
@AllArgsConstructor
@Qualifier("Client")
public  class ClientServiceImpl implements FirebaseRegisterService<Client> {
    @Autowired
    private FirebaseModel<Client> clientFirebaseModel;

    @Override
    public List<Client> getAll() throws InterruptedException, ExecutionException {
        this.clientFirebaseModel.build(Client.class);
        return this.clientFirebaseModel.getAll();

    }
    @Override
    public Client get(String uid) throws InterruptedException, ExecutionException {
        this.clientFirebaseModel.build(Client.class);
        return this.clientFirebaseModel.get(uid);
    }


    @Override
    public Client create(Client client) throws InterruptedException, ExecutionException {
        this.clientFirebaseModel.build(Client.class);
        return this.clientFirebaseModel.create(client);

    }


    @Override
    public Client update(Client client) throws ExecutionException, InterruptedException {
        this.clientFirebaseModel.build(Client.class);
        return this.clientFirebaseModel.update(client);
    }

    @Override
    public Boolean delete(String uid) throws InterruptedException {
        this.clientFirebaseModel.build(Client.class);
        return this.clientFirebaseModel.delete(uid);
    }
}
