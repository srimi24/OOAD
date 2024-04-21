package com.example.application.builders;

import com.example.application.models.Package;

public class PackageBuilder {
    private Package packageInstance;

    public PackageBuilder() {
        packageInstance = null;
    }

    public PackageBuilder startNewPackage(String packageName, double totalPrice) {
        packageInstance = new Package(packageName, totalPrice);
        return this;
    }

    public PackageBuilder setPackageName(String packageName) {
        packageInstance.setPackageName(packageName);
        return this;
    }

    public PackageBuilder setTotalPrice(double totalPrice) {
        packageInstance.setTotalPrice(totalPrice);
        return this;
    }

    public PackageBuilder addFlight(String flightNumber) {
        packageInstance.addFlightNumber(flightNumber);
        return this;
    }

    public PackageBuilder addTrain(String trainNumber) {
        packageInstance.addTrainNumber(trainNumber);
        return this;
    }

    public PackageBuilder addHotel(String hotelName) {
        packageInstance.addHotelName(hotelName);
        return this;
    }

    public PackageBuilder setVilla(String villaName) {
        packageInstance.setVillaName(villaName);
        return this;
    }

    public Package build() {
        Package builtPackage = packageInstance;
        packageInstance = null;
        return builtPackage;
    }
}
