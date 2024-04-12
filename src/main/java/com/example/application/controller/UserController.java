package com.example.application.controller;

import com.example.application.models.User;
import com.mongodb.MongoException;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.json.JsonWriterSettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class UserController {


    private List<User> userList;
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
    // Other controller methods for CRUD operations (Create, Update, Delete) can be

    public String checkUser(String checkUsername, String checkPassword) {
        System.out.println(checkUsername);
        System.out.println(checkPassword);
        Bson filter = Filters.and(
                Filters.eq("username", checkUsername),
                Filters.eq("password", checkPassword)
        );
        Document findUser = userCollection.find(filter).first();
        if (findUser == null)
            return null;
        return findUser.get("role", String.class);
    }

    static boolean preFlightChecks(MongoClient mongoClient) {
        Document pingCommand = new Document("ping", 1);
        Document response = mongoClient.getDatabase("admin").runCommand(pingCommand);
        System.out.println("=> Print result of the '{ping: 1}' command.");
        System.out.println(response.toJson(JsonWriterSettings.builder().indent(true).build()));
        return response.get("ok", Number.class).intValue() == 1;
    }
}
