package com.example.application.models;

import java.util.ArrayList;
import java.util.List;

public class Package {
    private String packageName;
    private List<Flight> flights;
    private List<Train> trains;
    private List<Hotel> hotels;
    private Villa villa;
    private double totalPrice;

    public Package(String packageName) {
        this.packageName = packageName;
        this.flights = new ArrayList<Flight>();
        this.trains = new ArrayList<Train>();
        this.hotels = new ArrayList<Hotel>();

        List<String> list=new ArrayList<String>();
//        this.totalPrice = totalPrice;
//        this.flights = flights;
//        this.trains = trains;
//        this.hotels = hotels;
//        this.villa = villa;
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

    public Villa getVilla() {
        return villa;
    }

    public void setVilla(Villa villa) {
        this.villa = villa;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
