package com.example.application.Adapters.Booking;

import com.example.application.Adapters.Booking.abstractBookingAdapter;
import com.example.application.models.TrainBooking;
import org.bson.Document;

public class TrainBookingAdapter extends abstractBookingAdapter {
    private String trainNumber;
    private String trainName;
    private String departureDate;
    private String arrivalDate;
    private String seatsBooked;

    public String getTrainNumber() {
        return trainNumber;
    }

    public void setTrainNumber(String trainNumber) {
        this.trainNumber = trainNumber;
    }

    public String getTrainName() {
        return trainName;
    }

    public void setTrainName(String trainName) {
        this.trainName = trainName;
    }

    public String getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(String departureDate) {
        this.departureDate = departureDate;
    }

    public String getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(String arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public String getSeatsBooked() {
        return seatsBooked;
    }

    public void setSeatsBooked(String seatsBooked) {
        this.seatsBooked = seatsBooked;
    }

    public TrainBooking toBooking(Document doc) {
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
}
