package com.example.application.Adapters.Booking;

import com.example.application.Adapters.Booking.abstractBookingAdapter;
import com.example.application.models.HotelBooking;
import org.bson.Document;

public class HotelBookingAdapter extends abstractBookingAdapter {
    private String hotelName;
    private String location;
    private String deluxeRoomsBooked;
    private String standardRoomsBooked;

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDeluxeRoomsBooked() {
        return deluxeRoomsBooked;
    }

    public void setDeluxeRoomsBooked(String deluxeRoomsBooked) {
        this.deluxeRoomsBooked = deluxeRoomsBooked;
    }

    public String getStandardRoomsBooked() {
        return standardRoomsBooked;
    }

    public void setStandardRoomsBooked(String standardRoomsBooked) {
        this.standardRoomsBooked = standardRoomsBooked;
    }

    public HotelBooking toBooking(Document doc) {
        return new HotelBooking(
                doc.getObjectId("_id").toString(),
                doc.getString("username"),
                doc.getString("hotel_name"),
                doc.getString("location"),
                doc.getString("deluxe_rooms_booked"),
                doc.getString("standard_rooms_booked"),
                doc.getString("total_price"),
                doc.getString("date_booked"),
                doc.getString("paid")
        );
    }

}
