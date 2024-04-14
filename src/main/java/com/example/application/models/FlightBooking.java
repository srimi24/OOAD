package com.example.application.models;

public class FlightBooking {
    private String id;
    private String username;
    private String flightNumber;
    private String flightName;
    private String departureDate;
    private String arrivalDate;
    private String seatsBooked;
    private String totalPrice;
    private String dateBooked;
    private String paid;

    public FlightBooking(String id, String username, String flightNumber, String flightName, String departureDate, String arrivalDate, String seatsBooked, String totalPrice, String dateBooked, String paid) {
        this.id = id;
        this.username = username;
        this.flightNumber = flightNumber;
        this.flightName = flightName;
        this.departureDate = departureDate;
        this.arrivalDate = arrivalDate;
        this.seatsBooked = seatsBooked;
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
