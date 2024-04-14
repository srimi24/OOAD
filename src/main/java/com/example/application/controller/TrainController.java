package com.example.application.controller;

import com.example.application.models.Train;
import com.mongodb.MongoException;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.json.JsonWriterSettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Controller
public class TrainController {
    private List<Train> trainList;
    private MongoCollection<Document> trainCollection;

    public TrainController() {
        try {
            // Attempt to connect to MongoDB
            MongoClient connectedClient = MongoClients.create("mongodb://localhost:27017/");

            // Perform pre-flight checks and handle potential issues
            if (!preFlightChecks(connectedClient)) {
                throw new RuntimeException("Failed to connect to MongoDB during pre-flight checks.");
            }

            System.out.println("=> Connection successful: " + preFlightChecks(connectedClient));
            trainCollection = connectedClient.getDatabase("Travel_Management_System").getCollection("trains");
            // ... rest of the initialization logic using connectedClient
        } catch (MongoException e) {
            // Handle MongoException in case of connection issues
            throw new RuntimeException("Error connecting to MongoDB: " + e.getMessage());
        }


    }

    private Train toTrain(Document doc){
        return new Train(
                doc.getString("trainNumber"),
                doc.getString("trainName"),
                doc.getDate("arrivalDate"),
                doc.getDate("departureDate"),
                doc.getString("departureStation"),
                doc.getString("arrivalStation"),
                doc.getInteger("numberOfSeatsInTrain"),
                doc.getDouble("price")
        );
    }

    public List<Train> getAllTrains() {
        Bson filter = Filters.ne("numberOfSeatsInTrain", 0);
        trainList = trainCollection.find(filter).map(this::toTrain).into(new ArrayList<>());
        return trainList;
    }

    public void editSeats(String trainNumber, int seats)
    {
        Bson filter = Filters.eq("trainNumber", trainNumber);

        Bson update = Updates.set("numberOfSeatsInTrain", seats);

        UpdateResult result = trainCollection.updateOne(filter, update);

        if (result.getModifiedCount() == 1) {
            System.out.println("Successfully updated seats for train " + trainNumber);
        } else {
            System.out.println("Failed to update seats for train " + trainNumber);
        }
    }

    static boolean preFlightChecks(MongoClient mongoClient) {
        Document pingCommand = new Document("ping", 1);
        Document response = mongoClient.getDatabase("admin").runCommand(pingCommand);
        System.out.println("=> Print result of the '{ping: 1}' command.");
        System.out.println(response.toJson(JsonWriterSettings.builder().indent(true).build()));
        return response.get("ok", Number.class).intValue() == 1;
    }
}
