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

import javax.print.Doc;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

    public String getRole(String Username){
        Bson filter = Filters.eq("username", Username);
        Document findUserRole = userCollection.find(filter).first();
        return (findUserRole!=null)? findUserRole.get("role", String.class) : null;
    }

    private User toUser(Document doc){
        return new User(
            doc.getString("username"),
            doc.getString("password"),
            doc.getString("role")
        );
    }

    public List<User> getAllUsers(){
        Bson filter = Filters.ne("role", "admin");
        userList = userCollection.find(filter).map(this::toUser).into(new ArrayList<>());
        return userList;
    }

    public void deleteUser(User user){
        Bson filter = Filters.eq("username", user.getUsername());
        System.out.println(userCollection.find(filter).first());
        userCollection.deleteOne(userCollection.find(filter).first());
    }

    static boolean preFlightChecks(MongoClient mongoClient) {
        Document pingCommand = new Document("ping", 1);
        Document response = mongoClient.getDatabase("admin").runCommand(pingCommand);
        System.out.println("=> Print result of the '{ping: 1}' command.");
        System.out.println(response.toJson(JsonWriterSettings.builder().indent(true).build()));
        return response.get("ok", Number.class).intValue() == 1;
    }
}
