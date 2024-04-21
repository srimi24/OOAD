//package com.example.application.builders;
//
//import com.example.application.models.Package;
//
//public class PackageBuilder {
//    private Package packageInstance;
//
//    public PackageBuilder() {
//        packageInstance = null;
//    }
//
//    public PackageBuilder startNewPackage(String packageName, double totalPrice) {
//        packageInstance = new Package(packageName, totalPrice);
//        return this;
//    }
//
//    public PackageBuilder setPackageName(String packageName) {
//        packageInstance.setPackageName(packageName);
//        return this;
//    }
//
//    public PackageBuilder setTotalPrice(double totalPrice) {
//        packageInstance.setTotalPrice(totalPrice);
//        return this;
//    }
//
//    public PackageBuilder addFlight(String flightNumber) {
//        packageInstance.addFlightNumber(flightNumber);
//        return this;
//    }
//
//    public PackageBuilder addTrain(String trainNumber) {
//        packageInstance.addTrainNumber(trainNumber);
//        return this;
//    }
//
//    public PackageBuilder addHotel(String hotelName) {
//        packageInstance.addHotelName(hotelName);
//        return this;
//    }
//
//    public PackageBuilder setVilla(String villaName) {
//        packageInstance.setVillaName(villaName);
//        return this;
//    }
//
//    public Package build() {
//        Package builtPackage = packageInstance;
//        packageInstance = null;
//        return builtPackage;
//    }
//}


package com.example.application.builders;

import com.example.application.models.Package;
import com.example.application.models.Flight;
import com.example.application.models.Train;
import com.example.application.models.Hotel;
import com.example.application.models.Villa;

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

    public PackageBuilder addFlight(Flight flight) {
        packageInstance.addFlight(flight);
        return this;
    }

    public PackageBuilder addTrain(Train train) {
        packageInstance.addTrain(train);
        return this;
    }

    public PackageBuilder addHotel(Hotel hotel) {
        packageInstance.addHotel(hotel);
        return this;
    }

    public PackageBuilder setVilla(Villa villa) {
        packageInstance.setVilla(villa);
        return this;
    }

    public Package build() {
        Package builtPackage = packageInstance;
        packageInstance = null;
        return builtPackage;
    }
}
