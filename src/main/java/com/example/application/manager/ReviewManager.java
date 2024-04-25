package com.example.application.manager;

import com.mongodb.MongoException;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import org.bson.Document;

import java.util.Map;

public class ReviewManager {
    private final MongoCollection<Document> reviewCollection;
    public ReviewManager() {
        try {
            // Attempt to connect to MongoDB
            MongoClient connectedClient = MongoClients.create("mongodb://localhost:27017/");

            reviewCollection = connectedClient.getDatabase("Travel_Management_System").getCollection("reviews");
            // ... rest of the initialization logic using connectedClient
        } catch (MongoException e) {
            // Handle MongoException in case of connection issues
            throw new RuntimeException("Error connecting to MongoDB: " + e.getMessage());
        }


    }

    public void review(String stayOrTravelExperience, String otherFeedback) {
        // Implement booking logic for flight
        Document review = new Document("stayOrTravelExperience", stayOrTravelExperience)
                .append("otherFeedback", otherFeedback);

        reviewCollection.insertOne(review);
    }
}
