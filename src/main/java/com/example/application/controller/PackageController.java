package com.example.application.controller;

import com.example.application.builders.PackageBuilder;
import com.example.application.models.Package;
import com.mongodb.MongoException;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import org.springframework.stereotype.Controller;

import java.util.Map;

@Controller
public class PackageController {
    private MongoCollection<Document> flightCollection;
    private MongoCollection<Document> hotelCollection;
    private MongoCollection<Document> villaCollection;
    private MongoCollection<Document> trainCollection;
    private MongoCollection<Document> packageCollection;

    public PackageController() {
        try {
            // Connect to MongoDB and initialize collections
            MongoClient connectedClient = MongoClients.create("mongodb://localhost:27017/");
            MongoDatabase database = connectedClient.getDatabase("Travel_Management_System");

            flightCollection = database.getCollection("flights");
            hotelCollection = database.getCollection("hotels");
            villaCollection = database.getCollection("villas");
            trainCollection = database.getCollection("trains");
            packageCollection = database.getCollection("packages");
        } catch (MongoException e) {
            throw new RuntimeException("Error connecting to MongoDB: " + e.getMessage());
        }
    }

    public void createPackage(Map<String, String> packageDetails) {
        try {
            // Fetch data from MongoDB collections
            Document flightData = flightCollection.find(Filters.eq("flightNumber", packageDetails.get("flightNumber"))).first();
            Document hotelData = hotelCollection.find(Filters.eq("name", packageDetails.get("hotelName"))).first();
            Document villaData = villaCollection.find(Filters.eq("villaName", packageDetails.get("villaName"))).first();
            Document trainData = trainCollection.find(Filters.eq("trainNumber", packageDetails.get("trainNumber"))).first();

            // Create a new package using the builder pattern
            PackageBuilder packageBuilder = new PackageBuilder();
            packageBuilder.startNewPackage(packageDetails.get("packageName"), Double.parseDouble(packageDetails.get("totalPrice")))
                    .addFlight(packageDetails.get("flightNumber"))
                    .addTrain(packageDetails.get("trainNumber"))
                    .addHotel(packageDetails.get("hotelName"))
                    .setVilla(packageDetails.get("villaName"));

            Package createdPackage = packageBuilder.build();

            // Save the created package to the database
//            Document packageDocument = new Document("name", createdPackage.getPackageName())
//                    .append("totalPrice", createdPackage.getTotalPrice())
//                    .append("flights", createdPackage.getFlights())
//                    .append("trains", createdPackage.getTrains())
//                    .append("hotels", createdPackage.getHotels())
//                    .append("villa", createdPackage.getVillas());
//
//            packageCollection.insertOne(packageDocument);

            // Optionally, you can perform additional operations here
        } catch (Exception e) {
            // Handle exceptions
            e.printStackTrace();
        }
    }
}
