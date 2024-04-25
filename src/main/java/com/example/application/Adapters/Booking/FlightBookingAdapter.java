package com.example.application.Adapters.Booking;

import com.example.application.Adapters.Booking.abstractBookingAdapter;
import com.example.application.models.FlightBooking;
import org.bson.Document;

public class FlightBookingAdapter extends abstractBookingAdapter {
    private String flightNumber;
    private String flightName;
    private String departureDate;
    private String arrivalDate;
    private String seatsBooked;

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public String getFlightName() {
        return flightName;
    }

    public void setFlightName(String flightName) {
        this.flightName = flightName;
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

    public FlightBooking toBooking(Document doc) {
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
}
