package com.example.application.views;

import com.example.application.strategies.BookingContext;
import com.example.application.models.Train;
import com.example.application.strategies.TrainBookingStrategy;
import com.example.application.controller.TrainController;
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

public class BookTrainForm extends FormLayout {

    private final Dialog dialog;
    private final IntegerField numberOfSeats;

    public BookTrainForm(Dialog dialog, Train train) {
        this.dialog = dialog;

        numberOfSeats = new IntegerField("Number of seats");

        Button bookButton = new Button("Book");
        bookButton.addClickListener(event -> {
            bookTrain(train);
        });

        add(numberOfSeats, bookButton);
    }

    private void bookTrain(Train train) {

        if (numberOfSeats.getValue() > train.getNumberOfSeatsInTrain()) {
            Notification.show("Number of seats requested exceeds available seats.");
            return;
        }

        if (numberOfSeats.getValue() < 1) {
            Notification.show("Number of seats should be at least 1.");
            return;
        }

        TrainController trainController = new TrainController();
        trainController.editSeats(train.getTrainNumber(), train.getNumberOfSeatsInTrain() - numberOfSeats.getValue());

        Notification.show("Booking successful for train " + train.getTrainNumber());
        BookingContext bookingContext = new BookingContext();

        Map<String, String> trainBookingDetails = new HashMap<>();
        trainBookingDetails.put("username", Session.getUsername());
        trainBookingDetails.put("train_number", train.getTrainNumber());
        trainBookingDetails.put("train_name", train.getTrainName());
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
        trainBookingDetails.put("departure_date", dateFormat.format(train.getDepartureDate()));
        trainBookingDetails.put("arrival_date", dateFormat.format(train.getArrivalDate()));
        trainBookingDetails.put("seats_booked", String.valueOf(numberOfSeats.getValue()));
        trainBookingDetails.put("total_price", String.valueOf(train.getPrice() * numberOfSeats.getValue()));
        DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        trainBookingDetails.put("date_booked", LocalDate.now().format(DATE_FORMATTER));
        trainBookingDetails.put("paid", "NO");

        bookingContext.setBookingStrategy(new TrainBookingStrategy());
        bookingContext.executeBooking(trainBookingDetails);
        dialog.close();
    }
}
