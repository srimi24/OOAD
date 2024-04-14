package com.example.application.models;

public class Villa {
    private String villaName;
    private String address;
    private int numberOfRooms;
    private String hasPrivateBathroom;
    private String hasKitchenFacilities;
    private double pricePerNight;

    public Villa(String villaName, String address, int numberOfRooms, String hasPrivateBathroom, String hasKitchenFacilities, double pricePerNight) {
        this.villaName = villaName;
        this.address = address;
        this.numberOfRooms = numberOfRooms;
        this.hasPrivateBathroom = hasPrivateBathroom;
        this.hasKitchenFacilities = hasKitchenFacilities;
        this.pricePerNight = pricePerNight;
    }

    public String getVillaName() {
        return villaName;
    }

    public void setVillaName(String villaName) {
        this.villaName = villaName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getNumberOfRooms() {
        return numberOfRooms;
    }

    public void setNumberOfRooms(int numberOfRooms) {
        this.numberOfRooms = numberOfRooms;
    }

    public String getHasPrivateBathroom() {
        return hasPrivateBathroom;
    }

    public void setHasPrivateBathroom(String hasPrivateBathroom) {
        this.hasPrivateBathroom = hasPrivateBathroom;
    }

    public String getHasKitchenFacilities() {
        return hasKitchenFacilities;
    }

    public void setHasKitchenFacilities(String hasKitchenFacilities) {
        this.hasKitchenFacilities = hasKitchenFacilities;
    }

    public double getPricePerNight() {
        return pricePerNight;
    }

    public void setPricePerNight(double pricePerNight) {
        this.pricePerNight = pricePerNight;
    }
}