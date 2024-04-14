package com.example.application.controller;

import com.example.application.models.Villa;
import com.mongodb.MongoException;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
import org.bson.json.JsonWriterSettings;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;

@Controller
public class VillaController {
    private List<Villa> villaList;
    private MongoCollection<Document> villaCollection;

    public VillaController() {
        try {
            // Attempt to connect to MongoDB
            MongoClient connectedClient = MongoClients.create("mongodb://localhost:27017/");

            // Perform pre-flight checks and handle potential issues
            if (!preFlightChecks(connectedClient)) {
                throw new RuntimeException("Failed to connect to MongoDB during pre-flight checks.");
            }

            System.out.println("=> Connection successful: " + preFlightChecks(connectedClient));
            villaCollection = connectedClient.getDatabase("Travel_Management_System").getCollection("villas");
            // ... rest of the initialization logic using connectedClient
        } catch (MongoException e) {
            // Handle MongoException in case of connection issues
            throw new RuntimeException("Error connecting to MongoDB: " + e.getMessage());
        }
    }

    private Villa toVilla(Document doc) {
        return new Villa(
                doc.getString("villaName"),
                doc.getString("address"),
                doc.getInteger("numberOfRooms"),
                doc.getString("hasPrivateBathroom"),
                doc.getString("hasKitchenFacilities"),
                doc.getDouble("pricePerNight")
        );
    }

    public List<Villa> getAvailableVillas() {
        villaList = villaCollection.find().map(this::toVilla).into(new ArrayList<>());
        return villaList;
    }

    static boolean preFlightChecks(MongoClient mongoClient) {
        Document pingCommand = new Document("ping", 1);
        Document response = mongoClient.getDatabase("admin").runCommand(pingCommand);
        System.out.println("=> Print result of the '{ping: 1}' command.");
        System.out.println(response.toJson(JsonWriterSettings.builder().indent(true).build()));
        return response.get("ok", Number.class).intValue() == 1;
    }
}
