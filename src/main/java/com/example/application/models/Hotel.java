package com.example.application.models;

public class Hotel {
    private String name;
    private String location;
    private int numberOfDeluxeRooms;
    private double deluxeRoomPricePerNight;
    private int numberOfStandardRooms;
    private double standardRoomPricePerNight;

    public Hotel(String name, String location, int numberOfDeluxeRooms, double deluxeRoomPricePerNight, int numberOfStandardRooms, double standardRoomPricePerNight) {
        this.name = name;
        this.location = location;
        this.numberOfDeluxeRooms = numberOfDeluxeRooms;
        this.deluxeRoomPricePerNight = deluxeRoomPricePerNight;
        this.numberOfStandardRooms = numberOfStandardRooms;
        this.standardRoomPricePerNight = standardRoomPricePerNight;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getNumberOfDeluxeRooms() {
        return numberOfDeluxeRooms;
    }

    public void setNumberOfDeluxeRooms(int numberOfDeluxeRooms) {
        this.numberOfDeluxeRooms = numberOfDeluxeRooms;
    }

    public double getDeluxeRoomPricePerNight() {
        return deluxeRoomPricePerNight;
    }

    public void setDeluxeRoomPricePerNight(double deluxeRoomPricePerNight) {
        this.deluxeRoomPricePerNight = deluxeRoomPricePerNight;
    }

    public int getNumberOfStandardRooms() {
        return numberOfStandardRooms;
    }

    public void setNumberOfStandardRooms(int numberOfStandardRooms) {
        this.numberOfStandardRooms = numberOfStandardRooms;
    }

    public double getStandardRoomPricePerNight() {
        return standardRoomPricePerNight;
    }

    public void setStandardRoomPricePerNight(double standardRoomPricePerNight) {
        this.standardRoomPricePerNight = standardRoomPricePerNight;
    }
}
