package com.example.application.views;

import com.example.application.strategies.BookingContext;
import com.example.application.models.Villa;
import com.example.application.strategies.VillaBookingStrategy;
import com.example.application.controller.VillaController;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.textfield.IntegerField;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class BookVillaForm extends FormLayout {

    private final Dialog dialog;
    private final IntegerField numberOfDays;

    public BookVillaForm(Dialog dialog, Villa villa) {
        this.dialog = dialog;

        numberOfDays = new IntegerField("Number of days");

        Button bookButton = new Button("Book");
        bookButton.addClickListener(event -> {
            bookVilla(villa);
        });

        add(numberOfDays, bookButton);
    }

    private void bookVilla(Villa villa) {
        int requestedDays = numberOfDays.getValue();
        if (requestedDays < 1) {
            Notification.show("Number of days should be at least 1");
            return; // Abort booking
        }

        Notification.show("Booking successful for villa " + villa.getVillaName());

        BookingContext bookingContext = new BookingContext();

        Map<String, String> villaBookingDetails = new HashMap<>();
        villaBookingDetails.put("username", Session.getUsername());
        villaBookingDetails.put("villa_name", villa.getVillaName());
        villaBookingDetails.put("address", villa.getAddress());
        villaBookingDetails.put("total_price", String.valueOf(villa.getPricePerNight() * requestedDays));
        DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        villaBookingDetails.put("date_booked", LocalDate.now().format(DATE_FORMATTER));
        villaBookingDetails.put("paid", "NO");

        bookingContext.setBookingStrategy(new VillaBookingStrategy());
        bookingContext.executeBooking(villaBookingDetails);

        dialog.close();
    }
}
