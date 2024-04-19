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
            packageBuilder.setPackageName(packageDetails.get("packageName"));
            packageBuilder.setTotalPrice(Double.parseDouble(packageDetails.get("totalPrice")));
            packageBuilder.setFlight(flightData.getString("flightNumber"), flightData.getString("airline"),
                    flightData.getDate("departureDate"), flightData.getDate("arrivalDate"));
            packageBuilder.setHotel(hotelData.getString("name"), hotelData.getString("location"),
                    hotelData.getInteger("numberOfRooms"), hotelData.getDouble("roomPricePerNight"));
            packageBuilder.setVilla(villaData.getString("villaName"), villaData.getString("address"),
                    villaData.getInteger("numberOfRooms"), villaData.getString("hasPrivateBathroom"),
                    villaData.getString("hasKitchenFacilities"), villaData.getDouble("pricePerNight"));
            packageBuilder.setTrain(trainData.getString("trainNumber"), trainData.getString("trainName"),
                    trainData.getDate("departureDate"), trainData.getDate("arrivalDate"),
                    trainData.getString("departureStation"), trainData.getString("arrivalStation"));

            Package createdPackage = packageBuilder.build();

            // Save the created package to the database
            Document packageDocument = new Document("name", createdPackage.getName())
                    .append("totalPrice", createdPackage.getTotalPrice())
                    .append("flight", createdPackage.getFlight())
                    .append("hotel", createdPackage.getHotel())
                    .append("villa", createdPackage.getVilla())
                    .append("train", createdPackage.getTrain());

            packageCollection.insertOne(packageDocument);

            // Optionally, you can perform additional operations here
        } catch (Exception e) {
            // Handle exceptions
            e.printStackTrace();
        }
    }
}
