package com.example.application.Adapters.Service;

import com.example.application.models.Flight;
import org.bson.Document;

public class FlightServiceAdapter extends abstractServiceAdapter<Flight> {
    @Override
    public Flight toObject(Document doc) {
        return new Flight(
                doc.getString("flightNumber"),
                doc.getString("airline"),
                doc.getDate("departureDate"),
                doc.getDate("arrivalDate"),
                doc.getString("departureAirport"),
                doc.getString("arrivalAirport"),
                doc.getInteger("numberOfSeatsInFlight"),
                doc.getDouble("price")
        );
    }
}
