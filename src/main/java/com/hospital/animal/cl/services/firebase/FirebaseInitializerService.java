package com.hospital.animal.cl.services.firebase;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;


@Service
@AllArgsConstructor

public class FirebaseInitializerService {

    @Autowired
    ResourceLoader resourceLoader;
    @PostConstruct
    public void initialize() throws IOException {

        try {
            File file = new File(resourceLoader.getResource("classpath:hospital-animal-firebase-adminsdk-e4u6d-c7598955b3.json").getURI());
            FileInputStream serviceAccount = new FileInputStream(file);
            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .setDatabaseUrl("https://hospital-animal.firebaseapp.com")
                    .build();
            if(FirebaseApp.getApps().isEmpty()) FirebaseApp.initializeApp(options);
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {

        }

    }
    public Firestore getFirestore(){
        return FirestoreClient.getFirestore();
    }
}
