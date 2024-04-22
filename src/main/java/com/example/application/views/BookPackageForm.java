package com.example.application.views;

import com.example.application.strategies.BookingContext;
import com.example.application.models.Package;
//import com.example.application.strategies.PackageBookingStrategy;
import com.example.application.controller.FlightController;
import com.example.application.controller.TrainController;
import com.example.application.strategies.PackageBookingStrategy;
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

public class BookPackageForm extends FormLayout {

    private final Dialog dialog;
    private final IntegerField numberOfPeople;
    private final Package package1;

    public BookPackageForm(Dialog dialog, Package package1) {
        this.dialog = dialog;
        this.package1 = package1;

        numberOfPeople = new IntegerField("Number of people");

        Button bookButton = new Button("Book");
        bookButton.addClickListener(event -> {
            bookPackage(package1);
        });

        add(numberOfPeople, bookButton);
    }

    private void bookPackage(Package package1) {

        if (numberOfPeople.getValue() < 1) {
            Notification.show("Number of people should be at least 1.");
            return;
        }

        // Assuming FlightController and TrainController have methods to edit seats
        FlightController flightController = new FlightController();
        TrainController trainController = new TrainController();

        // Reduce seats in flights and trains
        package1.getFlights().forEach(flight -> {
            if (flight.getNumberOfSeatsInFlight() >= numberOfPeople.getValue()) {
                flightController.editSeats(flight.getFlightNumber(), flight.getNumberOfSeatsInFlight() - numberOfPeople.getValue());
            } else {
                Notification.show("Seats not available in one of the flights.");
            }
        });

        package1.getTrains().forEach(train -> {
            if (train.getNumberOfSeatsInTrain() >= numberOfPeople.getValue()) {
                trainController.editSeats(train.getTrainNumber(), train.getNumberOfSeatsInTrain() - numberOfPeople.getValue());
            } else {
                Notification.show("Seats not available in one of the trains.");
            }
        });

        Notification.show("Booking successful for package " + package1.getPackageName());
        BookingContext bookingContext = new BookingContext();

        Map<String, String> packageBookingDetails = new HashMap<>();
        packageBookingDetails.put("username", Session.getUsername());
        packageBookingDetails.put("package_name", package1.getPackageName());
        // Add other package booking details

        bookingContext.setBookingStrategy(new PackageBookingStrategy());
        bookingContext.executeBooking(packageBookingDetails);
        dialog.close();
    }
}
