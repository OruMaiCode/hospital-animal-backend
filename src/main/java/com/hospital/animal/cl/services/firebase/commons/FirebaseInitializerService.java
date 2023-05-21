package com.hospital.animal.cl.services.firebase.commons;

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
import org.springframework.beans.factory.annotation.Value;

@Service


public class FirebaseInitializerService {

    @Autowired
    ResourceLoader resourceLoader;
    @Value("${firebase.config.path}")
    private String configPath;
    @Value("${firebase.config.url}")
    private String databaseURL;
    @PostConstruct
    public void initialize() throws IOException {

        try {
            File file = new File(resourceLoader.getResource(this.configPath).getURI());
            FileInputStream serviceAccount = new FileInputStream(file);
            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .setDatabaseUrl(this.databaseURL)
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
