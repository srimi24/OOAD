package com.example.application.views;

import com.example.application.strategies.BookingContext;
import com.example.application.models.Flight;
import com.example.application.strategies.FlightBookingStrategy;
import com.example.application.controller.FlightController;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.textfield.IntegerField;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import java.util.HashMap;
import java.util.Map;

public class BookFlightForm extends FormLayout {

    private final Dialog dialog;
    private final IntegerField numberOfSeats;

    public BookFlightForm(Dialog dialog, Flight flight) {
        this.dialog = dialog;

        numberOfSeats = new IntegerField("Number of seats");

        Button bookButton = new Button("Book");
        bookButton.addClickListener(event -> {
            bookFlight(flight);
        });

        add(numberOfSeats, bookButton);
    }

    private void bookFlight(Flight flight) {

        if (numberOfSeats.getValue() > flight.getNumberOfSeatsInFlight()) {
            Notification.show("Number of seats requested exceeds available seats.");
            return;
        }

        if (numberOfSeats.getValue() < 1) {
            Notification.show("Number of seats should be at least 1.");
            return;
        }

        FlightController flightController = new FlightController();
        flightController.editSeats(flight.getFlightNumber(), flight.getNumberOfSeatsInFlight() - numberOfSeats.getValue());

        Notification.show("Booking successful for flight " + flight.getFlightNumber());
        BookingContext bookingContext = new BookingContext();

        Map<String, String> flightBookingDetails = new HashMap<>();
        flightBookingDetails.put("username", Session.getUsername());
        flightBookingDetails.put("flight_number", flight.getFlightNumber());
        flightBookingDetails.put("airline", flight.getAirline());
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
        flightBookingDetails.put("departure_date", dateFormat.format(flight.getDepartureDate()));
        flightBookingDetails.put("arrival_date", dateFormat.format(flight.getArrivalDate()));
        flightBookingDetails.put("seats_booked", String.valueOf(numberOfSeats.getValue()));
        flightBookingDetails.put("total_price", String.valueOf(flight.getPrice() * numberOfSeats.getValue()));
        DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        flightBookingDetails.put("date_booked", LocalDate.now().format(DATE_FORMATTER));
        flightBookingDetails.put("paid", "NO");

        bookingContext.setBookingStrategy(new FlightBookingStrategy());
        bookingContext.executeBooking(flightBookingDetails);
        dialog.close();
    }
}
