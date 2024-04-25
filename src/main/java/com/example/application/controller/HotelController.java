package com.example.application.controller;

import com.example.application.database.MongoConnection;
import com.example.application.models.Hotel;
import com.example.application.models.HotelBooking;
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
import org.bson.types.ObjectId;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;

@Controller
public class HotelController {
    private final MongoCollection<Document> hotelCollection;
    private final MongoCollection<Document> hotelBookingCollection;

    public HotelController() {

        MongoConnection mongoConnection = MongoConnection.getInstance("Travel_Management_System");
        hotelCollection = mongoConnection.getCollection("hotels");
        hotelBookingCollection = mongoConnection.getCollection("hotelsBookings");
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

    public Hotel toHotel(Document doc){
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
        Bson filter = Filters.or(Filters.ne("numberOfDeluxeRooms", 0), Filters.ne("numberOfStandardRooms", 0));
        return hotelCollection.find(filter).map(this::toHotel).into(new ArrayList<>());
    }

    public void deletehotelBooking(HotelBooking hotelBooking) {
        ObjectId id = new ObjectId(hotelBooking.getId());
        Bson filter = Filters.eq("_id", id);
        hotelBookingCollection.deleteOne(hotelBookingCollection.find(filter).first());
    }

    static boolean preFlightChecks(MongoClient mongoClient) {
        Document pingCommand = new Document("ping", 1);
        Document response = mongoClient.getDatabase("admin").runCommand(pingCommand);
        System.out.println("=> Print result of the '{ping: 1}' command.");
        System.out.println(response.toJson(JsonWriterSettings.builder().indent(true).build()));
        return response.get("ok", Number.class).intValue() == 1;
    }


    public void updateHotelBooking(HotelBooking hotelBooking) {
        ObjectId id = new ObjectId(hotelBooking.getId());
        Bson filter = Filters.eq("_id", id);

        Document updateDoc = new Document("$set", new Document()
                .append("hotelName", hotelBooking.getHotelName())
                .append("location", hotelBooking.getLocation())
                .append("deluxeRoomsBooked", hotelBooking.getDeluxeRoomsBooked())
                .append("standardRoomsBooked", hotelBooking.getStandardRoomsBooked())
                .append("totalPrice", hotelBooking.getTotalPrice())
                .append("dateBooked", hotelBooking.getDateBooked())
                .append("paid", hotelBooking.getPaid()));
        hotelBookingCollection.updateOne(filter, updateDoc);
    }
}
