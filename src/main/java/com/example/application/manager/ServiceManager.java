package com.example.application.manager;

import com.example.application.Adapters.Booking.FlightBookingAdapter;
import com.example.application.Adapters.Service.FlightServiceAdapter;
import com.example.application.Adapters.Service.HotelServiceAdapter;
import com.example.application.Adapters.Service.TrainServiceAdapter;
import com.example.application.Adapters.Service.VillaServiceAdapter;
import com.example.application.database.MongoConnection;
import com.example.application.models.Flight;
import com.example.application.models.Hotel;
import com.example.application.models.Train;
import com.example.application.models.Villa;
import com.mongodb.client.MongoCollection;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

public class ServiceManager {
    private List<Flight> flightList;
    private List<Hotel> hotelList;
    private List<Train> trainList;
    private List<Villa> villaList;
    private final MongoCollection<Document> flightCollection;
    private final MongoCollection<Document> trainCollection;
    private final MongoCollection<Document> hotelCollection;
    private final MongoCollection<Document> villaCollection;

    public ServiceManager(){
        MongoConnection mongoConnection = MongoConnection.getInstance("Travel_Management_System");
        flightCollection = mongoConnection.getCollection("flights");
        trainCollection = mongoConnection.getCollection("trains");
        hotelCollection = mongoConnection.getCollection("hotels");
        villaCollection = mongoConnection.getCollection("villas");
    }

    public List<Flight> getFlightList() {
        flightList = flightCollection.find()
                .map(doc -> {
                    FlightServiceAdapter adapter = new FlightServiceAdapter();
                    return adapter.toObject(doc);
                })
                .into(new ArrayList<>());
        return flightList;
    }

    public List<Train> getTrainList() {
        trainList = trainCollection.find()
                .map(doc -> {
                    TrainServiceAdapter adapter = new TrainServiceAdapter();
                    return adapter.toObject(doc);
                })
                .into(new ArrayList<>());
        return trainList;
    }

    public List<Hotel> getHotelList(){
        hotelList = hotelCollection.find()
                .map(doc -> {
                    HotelServiceAdapter adapter = new HotelServiceAdapter();
                    return adapter.toObject(doc);
                })
                .into(new ArrayList<>());
        return hotelList;
    }

    public List<Villa> getVillaList() {
        villaList = villaCollection.find()
                .map(doc->{
                    VillaServiceAdapter adapter = new VillaServiceAdapter();
                    return adapter.toObject(doc);
                })
                .into(new ArrayList<>());
        return villaList;
    }
}
