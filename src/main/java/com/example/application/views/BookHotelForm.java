package com.example.application.views;

import com.example.application.controller.HotelController;
import com.example.application.models.Hotel;
import com.example.application.strategies.BookingContext;
import com.example.application.strategies.HotelBookingStrategy;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.textfield.IntegerField;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class BookHotelForm extends FormLayout {

    private final Dialog dialog;
    private final IntegerField numberOfStandardRooms;
    private final IntegerField numberOfDeluxeRooms;

    public BookHotelForm(Dialog dialog, Hotel hotel) {
        this.dialog = dialog;

        numberOfDeluxeRooms = new IntegerField("Number of deluxe rooms");
        numberOfStandardRooms = new IntegerField("Number of standard rooms");

        Button bookButton = new Button("Book");
        bookButton.addClickListener(event -> {
            bookHotel(hotel);
        });

        add(numberOfDeluxeRooms, numberOfStandardRooms, bookButton);
    }

    private void bookHotel(Hotel hotel) {

        if (numberOfDeluxeRooms.getValue() > hotel.getNumberOfDeluxeRooms()) {
            Notification.show("Number of deluxe rooms exceeds available deluxe rooms");
            return;
        }

        if (numberOfDeluxeRooms.getValue() < 1) {
            Notification.show("Number of deluxe rooms should be at least 1");
            return;
        }

        if (numberOfStandardRooms.getValue() > hotel.getNumberOfStandardRooms()) {
            Notification.show("Number of standard rooms exceeds available standard rooms");
            return;
        }

        if (numberOfStandardRooms.getValue() < 1) {
            Notification.show("Number of standard rooms should be at least 1");
            return;
        }

        double totalPrice = (hotel.getDeluxeRoomPricePerNight() * numberOfDeluxeRooms.getValue()) + (hotel.getStandardRoomPricePerNight() * numberOfStandardRooms.getValue());
        HotelController hotelController = new HotelController();
        hotelController.editAvailableRooms(hotel.getName(), hotel.getNumberOfDeluxeRooms() - numberOfDeluxeRooms.getValue(), hotel.getNumberOfStandardRooms() - numberOfStandardRooms.getValue());

        Notification.show("Booking successful for hotel " + hotel.getName());
        BookingContext bookingContext = new BookingContext();

        Map<String, String> hotelBookingDetails = new HashMap<>();
        hotelBookingDetails.put("username", Session.getUsername());
        hotelBookingDetails.put("hotel_name", hotel.getName());
        hotelBookingDetails.put("location", hotel.getLocation());
        hotelBookingDetails.put("deluxe_rooms_booked", String.valueOf(numberOfDeluxeRooms.getValue()));
        hotelBookingDetails.put("standard_rooms_booked", String.valueOf(numberOfStandardRooms.getValue()));
        hotelBookingDetails.put("total_price", String.valueOf(totalPrice));
        DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        hotelBookingDetails.put("date_booked", LocalDate.now().format(DATE_FORMATTER));
        hotelBookingDetails.put("paid", "NO");

        bookingContext.setBookingStrategy(new HotelBookingStrategy());
        bookingContext.executeBooking(hotelBookingDetails);
        dialog.close();
    }
}
