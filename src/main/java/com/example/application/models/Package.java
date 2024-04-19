package com.example.application.models;

import java.util.ArrayList;
import java.util.List;

public class Package {
    private String packageName;
    private List<Flight> flights;
    private List<Train> trains;
    private List<Hotel> hotels;
    private List<Villa> villas;
    private double totalPrice;

    public Package(String packageName, double totalPrice) {
        this.packageName = packageName;
        this.totalPrice = totalPrice;
        this.flights = new ArrayList<>();
        this.trains = new ArrayList<>();
        this.hotels = new ArrayList<>();
        this.villas = new ArrayList<>();
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public List<Flight> getFlights() {
        return flights;
    }

    public void addFlight(Flight flight) {
        this.flights.add(flight);
    }

    public List<Train> getTrains() {
        return trains;
    }

    public void addTrain(Train train) {
        this.trains.add(train);
    }

    public List<Hotel> getHotels() {
        return hotels;
    }

    public void addHotel(Hotel hotel) {
        this.hotels.add(hotel);
    }

    public List<Villa> getVillas() {
        return villas;
    }

    public void addVilla(Villa villa) {
        this.villas.add(villa);
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
