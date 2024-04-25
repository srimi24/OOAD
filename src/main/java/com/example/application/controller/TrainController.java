package com.example.application.controller;

import com.example.application.database.MongoConnection;
import com.example.application.models.FlightBooking;
import com.example.application.models.Train;
import com.example.application.models.TrainBooking;
import com.mongodb.MongoException;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.json.JsonWriterSettings;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Controller
public class TrainController {
    private List<Train> trainList;
    private final MongoCollection<Document> trainCollection;
    private final MongoCollection<Document> trainBookingCollection;

    public TrainController() {

        MongoConnection mongoConnection = MongoConnection.getInstance("Travel_Management_System");
        trainCollection = mongoConnection.getCollection("trains");
        trainBookingCollection = mongoConnection.getCollection("trainBookings");

    }

    public Train toTrain(Document doc){
        return new Train(
                doc.getString("trainNumber"),
                doc.getString("trainName"),
                doc.getDate("arrivalDate"),
                doc.getDate("departureDate"),
                doc.getString("departureStation"),
                doc.getString("arrivalStation"),
                doc.getInteger("numberOfSeatsInTrain"),
                doc.getDouble("price")
        );
    }

    public List<Train> getAllTrains() {
        Bson filter = Filters.ne("numberOfSeatsInTrain", 0);
        trainList = trainCollection.find(filter).map(this::toTrain).into(new ArrayList<>());
        return trainList;
    }

    public void editSeats(String trainNumber, int seats)
    {
        Bson filter = Filters.eq("trainNumber", trainNumber);

        Bson update = Updates.set("numberOfSeatsInTrain", seats);

        UpdateResult result = trainCollection.updateOne(filter, update);

        if (result.getModifiedCount() == 1) {
            System.out.println("Successfully updated seats for train " + trainNumber);
        } else {
            System.out.println("Failed to update seats for train " + trainNumber);
        }
    }

    public void deleteTrainBooking(TrainBooking trainBooking){
        ObjectId id = new ObjectId(trainBooking.getId());
        Bson filter = Filters.eq("_id", id);
        trainBookingCollection.deleteOne(trainBookingCollection.find(filter).first());
    }

    static boolean preFlightChecks(MongoClient mongoClient) {
        Document pingCommand = new Document("ping", 1);
        Document response = mongoClient.getDatabase("admin").runCommand(pingCommand);
        System.out.println("=> Print result of the '{ping: 1}' command.");
        System.out.println(response.toJson(JsonWriterSettings.builder().indent(true).build()));
        return response.get("ok", Number.class).intValue() == 1;
    }

    public void updateTrainBooking(TrainBooking trainBooking) {
        ObjectId id = new ObjectId(trainBooking.getId());
        Bson filter = Filters.eq("_id", id);

        Document updateDoc = new Document();
        updateDoc.append("username", trainBooking.getUsername());
        updateDoc.append("train_number", trainBooking.getTrainNumber());
        updateDoc.append("train_name", trainBooking.getTrainName());
        updateDoc.append("departure_date", trainBooking.getDepartureDate());
        updateDoc.append("arrival_date", trainBooking.getArrivalDate());
        updateDoc.append("seats_booked", trainBooking.getSeatsBooked());
        updateDoc.append("total_price", trainBooking.getTotalPrice());
        updateDoc.append("date_booked", trainBooking.getDateBooked());
        updateDoc.append("paid", trainBooking.getPaid());

        trainBookingCollection.updateOne(filter, new Document("$set", updateDoc));

    }
}
