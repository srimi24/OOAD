package com.example.application.controller;

import com.example.application.database.MongoConnection;
import com.example.application.models.Villa;
import com.example.application.models.VillaBooking;
import com.mongodb.MongoException;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.json.JsonWriterSettings;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;

@Controller
public class VillaController {
    private List<Villa> villaList;
    private final MongoCollection<Document> villaCollection;
    private final MongoCollection<Document> villaBookingCollection;

    public VillaController() {

        MongoConnection mongoConnection = MongoConnection.getInstance("Travel_Management_System");
        villaCollection = mongoConnection.getCollection("villas");
        villaBookingCollection = mongoConnection.getCollection("villaBookings");
    }

    public Villa toVilla(Document doc) {
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

    public void deletehotelBooking(VillaBooking villaBooking) {
        ObjectId id = new ObjectId(villaBooking.getId());
        Bson filter = Filters.eq("_id", id);
        villaBookingCollection.deleteOne(villaBookingCollection.find(filter).first());
    }
}
