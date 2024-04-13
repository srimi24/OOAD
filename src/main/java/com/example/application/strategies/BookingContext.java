package com.example.application.strategies;
import java.util.Map;

public class BookingContext {
    private BookingStrategy bookingStrategy;

    public void setBookingStrategy(BookingStrategy bookingStrategy) {
        this.bookingStrategy = bookingStrategy;
    }

    public void executeBooking(Map<String, String> bookingDetails) {
        if (bookingStrategy != null) {
            bookingStrategy.book(bookingDetails);
        }
    }
}
