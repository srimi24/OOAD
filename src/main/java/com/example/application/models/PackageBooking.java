package com.example.application.models;

import java.util.List;

public class PackageBooking {
    private String id;
    private String username;
    private String packageName;
    private List<Flight> flightsBooked;
    private List<Train> trainsBooked;
    private List<Hotel> hotelsBooked;
    private Villa villaBooked;
    private double totalPrice;
    private String dateBooked;
    private String paid;

    public PackageBooking(String id, String username, String packageName, List<Flight> flightsBooked,
                          List<Train> trainsBooked, List<Hotel> hotelsBooked, Villa villaBooked,
                          double totalPrice, String dateBooked, String paid) {
        this.id = id;
        this.username = username;
        this.packageName = packageName;
        this.flightsBooked = flightsBooked;
        this.trainsBooked = trainsBooked;
        this.hotelsBooked = hotelsBooked;
        this.villaBooked = villaBooked;
        this.totalPrice = totalPrice;
        this.dateBooked = dateBooked;
        this.paid = paid;
    }

    // Getters and setters
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

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public List<Flight> getFlightsBooked() {
        return flightsBooked;
    }

    public void setFlightsBooked(List<Flight> flightsBooked) {
        this.flightsBooked = flightsBooked;
    }

    public List<Train> getTrainsBooked() {
        return trainsBooked;
    }

    public void setTrainsBooked(List<Train> trainsBooked) {
        this.trainsBooked = trainsBooked;
    }

    public List<Hotel> getHotelsBooked() {
        return hotelsBooked;
    }

    public void setHotelsBooked(List<Hotel> hotelsBooked) {
        this.hotelsBooked = hotelsBooked;
    }

    public Villa getVillaBooked() {
        return villaBooked;
    }

    public void setVillaBooked(Villa villaBooked) {
        this.villaBooked = villaBooked;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
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
