package com.example.application.controller;

import com.example.application.models.Movie;
import com.mongodb.MongoException;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.json.JsonWriterSettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.example.application.controller.MovieController.preFlightChecks;

@Controller
public class UserController {

    private MongoCollection<Document> userCollection;

    @Autowired
    public UserController(MongoClient mongoClient) {
        try {
            // Attempt to connect to MongoDB
            MongoClient connectedClient = MongoClients.create("mongodb://localhost:27017/");

            // Perform pre-flight checks and handle potential issues
            if (!preFlightChecks(connectedClient)) {
                throw new RuntimeException("Failed to connect to MongoDB during pre-flight checks.");
            }

            System.out.println("=> Connection successful: " + preFlightChecks(connectedClient));
            userCollection = connectedClient.getDatabase("Travel_Management_System").getCollection("users");
            // ... rest of the initialization logic using connectedClient
        } catch (MongoException e) {
            // Handle MongoException in case of connection issues
            throw new RuntimeException("Error connecting to MongoDB: " + e.getMessage());
        }
    }

    public boolean checkUser(String checkUsername, String checkPassword) {
        Bson filter = Filters.and(
                Filters.eq("username",checkUsername),
                Filters.eq("password",checkPassword)
        );
        Document findUser = userCollection.find(filter).first();
        if(findUser==null)
            return false;
        return true;
    }
}