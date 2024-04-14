package com.example.application.models;

public class HotelBooking {
    private String id;
    private String username;
    private String hotelName;
    private String location;
    private String deluxeRoomsBooked;
    private String standardRoomsBooked;
    private String totalPrice;
    private String dateBooked;
    private String paid;

    public HotelBooking(String id, String username, String hotelName, String location, String deluxeRoomsBooked, String standardRoomsBooked, String totalPrice, String dateBooked, String paid) {
        this.id = id;
        this.username = username;
        this.hotelName = hotelName;
        this.location = location;
        this.deluxeRoomsBooked = deluxeRoomsBooked;
        this.standardRoomsBooked = standardRoomsBooked;
        this.totalPrice = totalPrice;
        this.dateBooked = dateBooked;
        this.paid = paid;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

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

    public String getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getDateBooked() {
        return dateBooked;
    }

    public void setDateBooked(String dateBooked) {
        this.dateBooked = dateBooked;
    }

    public String getPaid() {
        return paid;
    }

    public void setPaid(String paid) {
        this.paid = paid;
    }
}
