package com.example.application.strategies;

import com.mongodb.MongoException;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import org.bson.Document;

import java.util.Map;

public class HotelBookingStrategy implements BookingStrategy {
    private final MongoCollection<Document> hotelBookingCollection;

    public HotelBookingStrategy() {
        try {
            // Attempt to connect to MongoDB
            MongoClient connectedClient = MongoClients.create("mongodb://localhost:27017/");

            hotelBookingCollection = connectedClient.getDatabase("Travel_Management_System").getCollection("hotelBookings");
            // ... rest of the initialization logic using connectedClient
        } catch (MongoException e) {
            // Handle MongoException in case of connection issues
            throw new RuntimeException("Error connecting to MongoDB: " + e.getMessage());
        }
    }

    @Override
    public void book(Map<String, String> bookingDetails) {
        // Implement booking logic for hotel
        Document hotelBooking = new Document("username", bookingDetails.get("username"))
                .append("hotel_name", bookingDetails.get("hotel_name"))
                .append("location", bookingDetails.get("location"))
                .append("deluxe_rooms_booked", bookingDetails.get("deluxe_rooms_booked"))
                .append("standard_rooms_booked", bookingDetails.get("standard_rooms_booked"))
                .append("total_price", bookingDetails.get("total_price"))
                .append("date_booked", bookingDetails.get("date_booked"))
                .append("paid", bookingDetails.get("paid"));

        hotelBookingCollection.insertOne(hotelBooking);
    }
}