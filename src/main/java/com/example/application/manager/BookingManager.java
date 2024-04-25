package com.example.application.manager;

import com.example.application.Adapters.Booking.FlightBookingAdapter;
import com.example.application.Adapters.Booking.HotelBookingAdapter;
import com.example.application.Adapters.Booking.TrainBookingAdapter;
import com.example.application.Adapters.Booking.VillaBookingAdapter;
import com.example.application.database.MongoConnection;
import com.example.application.models.*;
import com.example.application.views.Session;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class BookingManager {
    private List<FlightBooking> flightBookingList;
    private final MongoCollection<Document> flightBookingCollection;

    private List<TrainBooking> trainBookingList;
    private final MongoCollection<Document> trainBookingCollection;

    private List<HotelBooking> hotelBookingList;
    private final MongoCollection<Document> hotelBookingCollection;

    private List<VillaBooking> villaBookingList;
    private final MongoCollection<Document> villaBookingCollection;


    public BookingManager() {

        MongoConnection mongoConnection = MongoConnection.getInstance("Travel_Management_System");
        flightBookingCollection = mongoConnection.getCollection("flightBookings");
        trainBookingCollection = mongoConnection.getCollection("trainBookings");
        hotelBookingCollection = mongoConnection.getCollection("hotelBookings");
        villaBookingCollection = mongoConnection.getCollection("villaBookings");

    }

    public List<FlightBooking> getAllFlightBookings() {
        Bson filter = Filters.eq("username", Session.getUsername());
        flightBookingList = flightBookingCollection.find(filter)
                .map(doc -> {
                    FlightBookingAdapter adapter = new FlightBookingAdapter();
                    return adapter.toBooking(doc);
                })
                .into(new ArrayList<>());
        return flightBookingList;
    }

    public List<FlightBooking> getAllBookingsFlight(){
        flightBookingList = flightBookingCollection.find()
                .map(doc -> {
                    FlightBookingAdapter adapter = new FlightBookingAdapter();
                    return adapter.toBooking(doc);
                })
                .into(new ArrayList<>());
        return flightBookingList;
    }

    public List<TrainBooking> getAllTrainBookings() {
        Bson filter = Filters.eq("username", Session.getUsername());
        trainBookingList = trainBookingCollection.find(filter)
                .map(doc -> {
                    TrainBookingAdapter adapter = new TrainBookingAdapter();
                    return adapter.toBooking(doc);
                })
                .into(new ArrayList<>());
        return trainBookingList;
    }

    public List<TrainBooking> getAllBookingsTrain(){
        trainBookingList = trainBookingCollection.find()
                .map(doc->{
                    TrainBookingAdapter adapter = new TrainBookingAdapter();
                    return adapter.toBooking(doc);
                })
                .into(new ArrayList<>());
        return trainBookingList;
    }

    public List<HotelBooking> getAllHotelBookings() {
        Bson filter = Filters.eq("username", Session.getUsername());
        hotelBookingList = hotelBookingCollection.find(filter)
                .map(doc->{
                    HotelBookingAdapter adapter = new HotelBookingAdapter();
                    return adapter.toBooking(doc);
                })
                .into(new ArrayList<>());
        return hotelBookingList;
    }

    public List<HotelBooking> getAllBookingsHotel(){
        hotelBookingList = hotelBookingCollection.find()
                .map(doc->{
                    HotelBookingAdapter adapter = new HotelBookingAdapter();
                    return adapter.toBooking(doc);
                })
                .into(new ArrayList<>());
        return hotelBookingList;
    }

    public List<VillaBooking> getAllVillaBookings() {
        Bson filter = Filters.eq("username", Session.getUsername());
        villaBookingList = villaBookingCollection.find(filter)
                .map(doc->{
                    VillaBookingAdapter adapter = new VillaBookingAdapter();
                    return adapter.toBooking(doc);
                })
                .into(new ArrayList<>());
        return villaBookingList;
    }

    public List<VillaBooking> getAllBookingsVilla(){
        villaBookingList = villaBookingCollection.find()
                .map(doc -> {
                    VillaBookingAdapter adapter = new VillaBookingAdapter();
                    return adapter.toBooking(doc);
                })
                .into(new ArrayList<>());
        return villaBookingList;
    }

    public Collection<PackageBooking> getAllBookingsPackage() {
        return null;
    }
}
