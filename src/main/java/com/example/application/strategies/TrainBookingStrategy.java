package com.example.application.strategies;

import com.mongodb.MongoException;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import org.bson.Document;

import java.util.Map;

public class TrainBookingStrategy implements BookingStrategy {
    private final MongoCollection<Document> trainBookingCollection;
    public TrainBookingStrategy() {
        try {
            // Attempt to connect to MongoDB
            MongoClient connectedClient = MongoClients.create("mongodb://localhost:27017/");

            trainBookingCollection = connectedClient.getDatabase("Travel_Management_System").getCollection("trainBookings");
            // ... rest of the initialization logic using connectedClient
        } catch (MongoException e) {
            // Handle MongoException in case of connection issues
            throw new RuntimeException("Error connecting to MongoDB: " + e.getMessage());
        }


    }
    @Override
    public void book(Map<String, String> bookingDetails) {
        // Implement booking logic for train
        Document trainBooking = new Document("username", bookingDetails.get("username"))
                .append("train_number", bookingDetails.get("train_number"))
                .append("train_name", bookingDetails.get("train_name"))
                .append("departure_date", bookingDetails.get("departure_date"))
                .append("arrival_date", bookingDetails.get("arrival_date"))
                .append("seats_booked", bookingDetails.get("seats_booked"))
                .append("total_price", bookingDetails.get("total_price"))
                .append("date_booked", bookingDetails.get("date_booked"))
                .append("paid", bookingDetails.get("paid"));

        trainBookingCollection.insertOne(trainBooking);
    }
}