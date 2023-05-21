package com.hospital.animal.cl.services.firebase.commons;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import java.util.List;
import java.util.concurrent.ExecutionException;

import com.hospital.animal.cl.dto.DatabaseRegister;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;


@Service
public class FirebaseModel<T extends DatabaseRegister> {
    @Autowired
    private FirebaseInitializerService firebaseInitializerService;
    private Firestore firestore;
    private String collectionName;
    private Class<T> t;
    public void build(Class<T>t){
        this.firestore =firebaseInitializerService.getFirestore();
        this.t=t;
        this.collectionName = t.getSimpleName();
    }
    public List<T> getAll() throws InterruptedException, ExecutionException {
        ApiFuture<QuerySnapshot> future = this.firestore.collection(this.collectionName).get();
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();
        return documents.stream().parallel().map(queryDocumentSnapshot -> queryDocumentSnapshot.toObject(t)).toList();
    }

    public T get(String uid) throws InterruptedException, ExecutionException {
        ApiFuture<DocumentSnapshot> future = this.firestore.collection(this.collectionName).document(uid).get();
        DocumentSnapshot document = future.get();
        return document.toObject(this.t);

    }

    public T create(T t) throws InterruptedException, ExecutionException {
        DocumentReference document = this.firestore.collection(this.collectionName).document();
        t.setUid(document.getId());
        ApiFuture<WriteResult> writeResultApiFuture = document.set(t);
        writeResultApiFuture.get();
        if (writeResultApiFuture.isDone()) {
            ApiFuture<DocumentSnapshot> documentSnapshot = document.get();
            return documentSnapshot.get().toObject(this.t);
        }
        return t;
    }

    public T update(T t) throws ExecutionException, InterruptedException {


            DocumentReference document = this.firestore.collection(this.collectionName).document(t.getUid());
            ApiFuture<WriteResult> writeResultApiFuture = document.set(t, SetOptions.merge());
            writeResultApiFuture.get();
            if (writeResultApiFuture.isDone()) {
                ApiFuture<DocumentSnapshot> documentSnapshot = document.get();
                return documentSnapshot.get().toObject(this.t);
            }
        return t;
    }

    public Boolean delete(String uid) throws InterruptedException {
        try{
            DocumentReference document = this.firestore.collection(this.collectionName).document(uid);
            ApiFuture<WriteResult> writeResultApiFuture = document.delete();
            writeResultApiFuture.get();
            return writeResultApiFuture.isDone();
        }catch (IllegalArgumentException |ExecutionException  ex){
            return false;
        }

    }

}
