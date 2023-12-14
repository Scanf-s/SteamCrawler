package org.example;

import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;

import java.io.IOException;
import java.io.InputStream;
import java.io.FileInputStream;
import java.util.List;

public class FirebaseManager {

    private static String gameTag = "Simulation";
    static Firestore firestore;

    static {
        try {
            InputStream serviceAccount = new FileInputStream("D:\\Java\\untitled\\src\\main\\resources\\ggamdeal-5f8cb-firebase-adminsdk-qkxfo-30433dc0ae.json");
            GoogleCredentials credentials = GoogleCredentials.fromStream(serviceAccount);
            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(credentials)
                    .build();
            FirebaseApp.initializeApp(options);
            firestore = FirestoreClient.getFirestore();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Save games to Firestore
    public static void saveGamesToFirestore(List<GameInfoContainer> games) {
        for (GameInfoContainer game : games) {
            saveGameToFirestore(game);
        }
    }

    private static void saveGameToFirestore(GameInfoContainer game) {
        try {
            String gameId = game.getTitle();
            CollectionReference colRef = firestore.collection("Game").document("Steam").collection(gameTag);
            DocumentReference docRef = colRef.document(gameId);
            ApiFuture<WriteResult> result = docRef.set(game);
            System.out.println("Game saved to Firestore: " + result.get().getUpdateTime());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}