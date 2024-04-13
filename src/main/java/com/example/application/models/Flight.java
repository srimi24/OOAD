package com.example.application.models;

import java.util.Date;

public class Flight {
    private String flightNumber;
    private String airline;
    private Date departureDate;
    private Date arrivalDate;
    private String departureAirport;
    private String arrivalAirport;
    private int numberOfSeatsInFlight;
    private double price;

    public Flight(String flightNumber, String airline, Date departureDate, Date arrivalDate, String departureAirport, String arrivalAirport, int numberOfSeatsInFlight, double price) {
        this.flightNumber = flightNumber;
        this.airline = airline;
        this.departureDate = departureDate;
        this.arrivalDate = arrivalDate;
        this.departureAirport = departureAirport;
        this.arrivalAirport = arrivalAirport;
        this.numberOfSeatsInFlight = numberOfSeatsInFlight;
        this.price = price;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public String getAirline() {
        return airline;
    }

    public void setAirline(String airline) {
        this.airline = airline;
    }

    public Date getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(Date departureDate) {
        this.departureDate = departureDate;
    }

    public Date getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(Date arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public String getDepartureAirport() {
        return departureAirport;
    }

    public void setDepartureAirport(String departureAirport) {
        this.departureAirport = departureAirport;
    }

    public String getArrivalAirport() {
        return arrivalAirport;
    }

    public void setArrivalAirport(String arrivalAirport) {
        this.arrivalAirport = arrivalAirport;
    }

    public int getNumberOfSeatsInFlight() {
        return numberOfSeatsInFlight;
    }

    public void setNumberOfSeatsInFlight(int numberOfSeatsInFlight) {
        this.numberOfSeatsInFlight = numberOfSeatsInFlight;
    }
}
