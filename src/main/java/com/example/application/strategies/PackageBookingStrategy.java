package com.example.application.strategies;

import com.mongodb.MongoException;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import org.bson.Document;

import java.util.Map;

public class PackageBookingStrategy implements BookingStrategy {
    private final MongoCollection<Document> packageBookingCollection;

    public PackageBookingStrategy() {
        try {
            // Attempt to connect to MongoDB
            MongoClient connectedClient = MongoClients.create("mongodb://localhost:27017/");

            packageBookingCollection = connectedClient.getDatabase("Travel_Management_System").getCollection("packageBookings");
            // ... rest of the initialization logic using connectedClient
        } catch (MongoException e) {
            // Handle MongoException in case of connection issues
            throw new RuntimeException("Error connecting to MongoDB: " + e.getMessage());
        }
    }

    @Override
    public void book(Map<String, String> bookingDetails) {
        // Implement booking logic for package
        Document packageBooking = new Document("username", bookingDetails.get("username"))
                .append("package_name", bookingDetails.get("package_name"))
                // Add other package booking details
                .append("date_booked", bookingDetails.get("date_booked"))
                .append("paid", bookingDetails.get("paid"));

        packageBookingCollection.insertOne(packageBooking);
    }
}
