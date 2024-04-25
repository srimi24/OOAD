package com.example.application.strategies;

import com.mongodb.MongoException;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import org.bson.Document;

import java.util.Map;

public class VillaBookingStrategy implements BookingStrategy {
    private final MongoCollection<Document> villaBookingCollection;
    public VillaBookingStrategy() {
        try {
            // Attempt to connect to MongoDB
            MongoClient connectedClient = MongoClients.create("mongodb://localhost:27017/");

            villaBookingCollection = connectedClient.getDatabase("Travel_Management_System").getCollection("villaBookings");
            // ... rest of the initialization logic using connectedClient
        } catch (MongoException e) {
            // Handle MongoException in case of connection issues
            throw new RuntimeException("Error connecting to MongoDB: " + e.getMessage());
        }


    }
    @Override
    public void book(Map<String, String> bookingDetails) {
        // Implement booking logic for train
        Document villaBooking = new Document("username", bookingDetails.get("username"))
                .append("villa_name", bookingDetails.get("villa_name"))
                .append("address", bookingDetails.get("address"))
                .append("total_price", bookingDetails.get("total_price"))
                .append("date_booked", bookingDetails.get("date_booked"))
                .append("paid", bookingDetails.get("paid"));

        villaBookingCollection.insertOne(villaBooking);
    }
}