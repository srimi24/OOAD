package com.example.application.models;

import java.util.Date;

public class Train {
    private String trainNumber;
    private String trainName;
    private Date departureDate;
    private Date arrivalDate;
    private String departureStation;
    private String arrivalStation;
    private int numberOfSeatsInTrain;
    private double price;

    public Train(String trainNumber, String trainName, Date departureDate, Date arrivalDate, String departureStation, String arrivalStation, int numberOfSeatsInTrain, double price) {
        this.trainNumber = trainNumber;
        this.trainName = trainName;
        this.departureDate = departureDate;
        this.arrivalDate = arrivalDate;
        this.departureStation = departureStation;
        this.arrivalStation = arrivalStation;
        this.numberOfSeatsInTrain = numberOfSeatsInTrain;
        this.price = price;
    }

    public String getTrainNumber() {
        return trainNumber;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setTrainNumber(String trainNumber) {
        this.trainNumber = trainNumber;
    }

    public String getTrainName() {
        return trainName;
    }

    public void setTrainName(String trainName) {
        this.trainName = trainName;
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

    public String getDepartureStation() {
        return departureStation;
    }

    public void setDepartureStation(String departureStation) {
        this.departureStation = departureStation;
    }

    public String getArrivalStation() {
        return arrivalStation;
    }

    public void setArrivalStation(String arrivalStation) {
        this.arrivalStation = arrivalStation;
    }

    public int getNumberOfSeatsInTrain() {
        return numberOfSeatsInTrain;
    }

    public void setNumberOfSeatsInTrain(int numberOfSeatsInTrain) {
        this.numberOfSeatsInTrain = numberOfSeatsInTrain;
    }
}
