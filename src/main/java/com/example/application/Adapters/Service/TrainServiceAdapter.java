package com.example.application.Adapters.Service;

import com.example.application.models.Train;
import org.bson.Document;

public class TrainServiceAdapter extends abstractServiceAdapter<Train>{
    @Override
    public Train toObject(Document doc) {
        return new Train(
                doc.getString("trainNumber"),
                doc.getString("trainName"),
                doc.getDate("departureDate"),
                doc.getDate("arrivalDate"),
                doc.getString("departureStation"),
                doc.getString("arrivalStation"),
                doc.getInteger("numberOfSeatsInTrain"),
                doc.getDouble("price")
        );
    }
}
