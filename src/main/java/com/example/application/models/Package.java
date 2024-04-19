package com.example.application.models;

import java.util.ArrayList;
import java.util.List;

public class Package {
    private String packageName;
    private List<String> flightNumbers; // Only store flight numbers for comparison
    private List<String> trainNumbers; // Only store train numbers for comparison
    private List<String> hotelNames; // Only store hotel names for comparison
    private String villaName; // Only store villa name for comparison
    private double totalPrice;

    public Package(String packageName, double totalPrice) {
        this.packageName = packageName;
        this.totalPrice = totalPrice;
        this.flightNumbers = new ArrayList<>();
        this.trainNumbers = new ArrayList<>();
        this.hotelNames = new ArrayList<>();
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public List<String> getFlightNumbers() {
        return flightNumbers;
    }

    public void addFlightNumber(String flightNumber) {
        this.flightNumbers.add(flightNumber);
    }

    public List<String> getTrainNumbers() {
        return trainNumbers;
    }

    public void addTrainNumber(String trainNumber) {
        this.trainNumbers.add(trainNumber);
    }

    public List<String> getHotelNames() {
        return hotelNames;
    }

    public void addHotelName(String hotelName) {
        this.hotelNames.add(hotelName);
    }

    public String getVillaName() {
        return villaName;
    }

    public void setVillaName(String villaName) {
        this.villaName = villaName;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
