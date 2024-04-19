package com.example.application.builders;

import com.example.application.models.Flight;
import com.example.application.models.Hotel;
import com.example.application.models.Train;
import com.example.application.models.Villa;
import com.example.application.models.Package;

import java.util.Date;

public class PackageBuilder {
    private Package packageInstance;

    public PackageBuilder() {
        packageInstance = null;
    }

    public PackageBuilder startNewPackage(String packageName, double totalPrice) {
        packageInstance = new Package(packageName, totalPrice);
        return this;
    }

    public PackageBuilder addFlight(String flightNumber, String airline, Date departureDate, Date arrivalDate,
                                    String departureAirport, String arrivalAirport, int numberOfSeatsInFlight, double price) {
        Flight flight = new Flight(flightNumber, airline, departureDate, arrivalDate,
                departureAirport, arrivalAirport, numberOfSeatsInFlight, price);
        packageInstance.addFlight(flight);
        return this;
    }

    public PackageBuilder addTrain(String trainNumber, String trainName, Date departureDate, Date arrivalDate,
                                   String departureStation, String arrivalStation, int numberOfSeatsInTrain, double price) {
        Train train = new Train(trainNumber, trainName, departureDate, arrivalDate,
                departureStation, arrivalStation, numberOfSeatsInTrain, price);
        packageInstance.addTrain(train);
        return this;
    }

    public PackageBuilder addHotel(String name, String location, int numberOfDeluxeRooms, double deluxeRoomPricePerNight,
                                   int numberOfStandardRooms, double standardRoomPricePerNight) {
        Hotel hotel = new Hotel(name, location, numberOfDeluxeRooms, deluxeRoomPricePerNight,
                numberOfStandardRooms, standardRoomPricePerNight);
        packageInstance.addHotel(hotel);
        return this;
    }

    public PackageBuilder addVilla(String villaName, String address, int numberOfRooms, String hasPrivateBathroom,
                                   String hasKitchenFacilities, double pricePerNight) {
        Villa villa = new Villa(villaName, address, numberOfRooms, hasPrivateBathroom, hasKitchenFacilities, pricePerNight);
        packageInstance.addVilla(villa);
        return this;
    }

    public Package build() {
        Package builtPackage = packageInstance;
        packageInstance = null;
        return builtPackage;
    }
}
