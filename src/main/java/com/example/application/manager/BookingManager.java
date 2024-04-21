package com.example.application.manager;

import com.example.application.models.*;
import com.example.application.views.Session;
import com.mongodb.MongoException;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.json.JsonWriterSettings;

import java.util.ArrayList;
import java.util.List;

public class BookingManager {
    private List<FlightBooking> flightBookingList;
    private MongoCollection<Document> flightBookingCollection;

    private List<TrainBooking> trainBookingList;
    private MongoCollection<Document> trainBookingCollection;

    private List<HotelBooking> hotelBookingList;
    private MongoCollection<Document> hotelBookingCollection;

    private List<VillaBooking> villaBookingList;
    private MongoCollection<Document> villaBookingCollection;

    public BookingManager() {
        try {
            // Attempt to connect to MongoDB
            MongoClient connectedClient = MongoClients.create("mongodb://localhost:27017/");

            // Perform pre-flight checks and handle potential issues
            if (!preFlightChecks(connectedClient)) {
                throw new RuntimeException("Failed to connect to MongoDB during pre-flight checks.");
            }

            System.out.println("=> Connection successful: " + preFlightChecks(connectedClient));
            flightBookingCollection = connectedClient.getDatabase("Travel_Management_System").getCollection("flightBookings");
            trainBookingCollection = connectedClient.getDatabase("Travel_Management_System").getCollection("trainBookings");
            hotelBookingCollection = connectedClient.getDatabase("Travel_Management_System").getCollection("hotelBookings");
            villaBookingCollection = connectedClient.getDatabase("Travel_Management_System").getCollection("villaBookings");
            // ... rest of the initialization logic using connectedClient
        } catch (MongoException e) {
            // Handle MongoException in case of connection issues
            throw new RuntimeException("Error connecting to MongoDB: " + e.getMessage());
        }


    }

    private FlightBooking toFlightBooking(Document doc){
        return new FlightBooking(
                doc.getObjectId("_id").toString(),
                doc.getString("username"),
                doc.getString("flight_number"),
                doc.getString("airline"),
                doc.getString("departure_date"),
                doc.getString("arrival_date"),
                doc.getString("seats_booked"),
                doc.getString("total_price"),
                doc.getString("date_booked"),
                doc.getString("paid")
        );
    }

    private TrainBooking toTrainBooking(Document doc){
        return new TrainBooking(
                doc.getObjectId("_id").toString(),
                doc.getString("username"),
                doc.getString("train_number"),
                doc.getString("train_name"),
                doc.getString("departure_date"),
                doc.getString("arrival_date"),
                doc.getString("seats_booked"),
                doc.getString("total_price"),
                doc.getString("date_booked"),
                doc.getString("paid")
        );
    }

    private HotelBooking toHotelBooking(Document doc){
        return new HotelBooking(
                doc.getObjectId("_id").toString(),
                doc.getString("username"),
                doc.getString("hotel_name"),
                doc.getString("location"),
                doc.getString("deluxe_rooms_booked"),
                doc.getString("standard_rooms_booked"),
                doc.getString("total_price"),
                doc.getString("date_booked"),
                doc.getString("paid")
        );
    }

    private VillaBooking toVillaBooking(Document doc){
        return new VillaBooking(
                doc.getObjectId("_id").toString(),
                doc.getString("username"),
                doc.getString("villa_name"),
                doc.getString("address"),
                doc.getString("total_price"),
                doc.getString("date_booked"),
                doc.getString("paid")
        );
    }

    public List<FlightBooking> getAllFlightBookings() {
        Bson filter = Filters.eq("username", Session.getUsername());
        flightBookingList = flightBookingCollection.find(filter).map(this::toFlightBooking).into(new ArrayList<>());
        return flightBookingList;
    }

    public List<FlightBooking> getAllBookingsFlight(){
        flightBookingList = flightBookingCollection.find().map(this::toFlightBooking).into(new ArrayList<>());
        return flightBookingList;
    }

    public List<TrainBooking> getAllTrainBookings() {
        Bson filter = Filters.eq("username", Session.getUsername());
        trainBookingList = trainBookingCollection.find(filter).map(this::toTrainBooking).into(new ArrayList<>());
        return trainBookingList;
    }

    public List<TrainBooking> getAllBookingsTrain(){
        trainBookingList = trainBookingCollection.find().map(this::toTrainBooking).into(new ArrayList<>());
        return trainBookingList;
    }

    public List<HotelBooking> getAllHotelBookings() {
        Bson filter = Filters.eq("username", Session.getUsername());
        hotelBookingList = hotelBookingCollection.find(filter).map(this::toHotelBooking).into(new ArrayList<>());
        return hotelBookingList;
    }

    public List<HotelBooking> getAllBookingsHotel(){
        hotelBookingList = hotelBookingCollection.find().map(this::toHotelBooking).into(new ArrayList<>());
        return hotelBookingList;
    }

    public List<VillaBooking> getAllVillaBookings() {
        Bson filter = Filters.eq("username", Session.getUsername());
        villaBookingList = villaBookingCollection.find(filter).map(this::toVillaBooking).into(new ArrayList<>());
        return villaBookingList;
    }

    public List<VillaBooking> getAllBookingsVilla(){
        villaBookingList = villaBookingCollection.find().map(this::toVillaBooking).into(new ArrayList<>());
        return villaBookingList;
    }


    static boolean preFlightChecks(MongoClient mongoClient) {
        Document pingCommand = new Document("ping", 1);
        Document response = mongoClient.getDatabase("admin").runCommand(pingCommand);
        System.out.println("=> Print result of the '{ping: 1}' command.");
        System.out.println(response.toJson(JsonWriterSettings.builder().indent(true).build()));
        return response.get("ok", Number.class).intValue() == 1;
    }
}
