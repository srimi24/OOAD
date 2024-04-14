package com.example.application.controller;

import com.example.application.models.Hotel;
import com.mongodb.MongoException;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.UpdateResult;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.json.JsonWriterSettings;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;

@Controller
public class HotelController {
    private List<Hotel> hotelList;
    private MongoCollection<Document> hotelCollection;

    public HotelController() {
        try {
            // Attempt to connect to MongoDB
            MongoClient connectedClient = MongoClients.create("mongodb://localhost:27017/");

            // Perform pre-flight checks and handle potential issues
            if (!preFlightChecks(connectedClient)) {
                throw new RuntimeException("Failed to connect to MongoDB during pre-flight checks.");
            }

            System.out.println("=> Connection successful: " + preFlightChecks(connectedClient));
            hotelCollection = connectedClient.getDatabase("Travel_Management_System").getCollection("hotels");
            // ... rest of the initialization logic using connectedClient
        } catch (MongoException e) {
            // Handle MongoException in case of connection issues
            throw new RuntimeException("Error connecting to MongoDB: " + e.getMessage());
        }
    }

    public void editAvailableRooms(String hotelName, int newNoOfDeluxeRooms, int newNoOfStandardRooms) {
        Bson filter = Filters.eq("name", hotelName);

        Bson update = Updates.combine(
                Updates.set("numberOfDeluxeRooms", newNoOfDeluxeRooms),
                Updates.set("numberOfStandardRooms", newNoOfStandardRooms)
        );

        UpdateResult result = hotelCollection.updateOne(filter, update);

        if (result.getModifiedCount() == 1) {
            System.out.println("Successfully updated available rooms for hotel " + hotelName);
        } else {
            System.out.println("Failed to update available rooms for hotel " + hotelName);
        }
    }

    private Hotel toHotel(Document doc){
        return new Hotel(
                doc.getString("name"),
                doc.getString("location"),
                doc.getInteger("numberOfDeluxeRooms"),
                doc.getDouble("deluxeRoomPricePerNight"),
                doc.getInteger("numberOfStandardRooms"),
                doc.getDouble("standardRoomPricePerNight")
        );
    }

    public List<Hotel> getAllHotels() {
        hotelList = hotelCollection.find().map(this::toHotel).into(new ArrayList<>());
        return hotelList;
    }

    static boolean preFlightChecks(MongoClient mongoClient) {
        Document pingCommand = new Document("ping", 1);
        Document response = mongoClient.getDatabase("admin").runCommand(pingCommand);
        System.out.println("=> Print result of the '{ping: 1}' command.");
        System.out.println(response.toJson(JsonWriterSettings.builder().indent(true).build()));
        return response.get("ok", Number.class).intValue() == 1;
    }
}
