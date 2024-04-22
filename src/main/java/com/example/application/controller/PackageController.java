//package com.example.application.controller;
//import com.example.application.models.Flight;
//import com.example.application.models.Train;
//import com.example.application.models.Hotel;
//import com.example.application.models.Villa;
//import com.example.application.controller.FlightController;
//import com.example.application.controller.TrainController;
//import com.example.application.controller.HotelController;
//import com.example.application.controller.VillaController;
//import com.example.application.builders.PackageBuilder;
//import com.example.application.models.Package;
//import com.mongodb.MongoException;
//import com.mongodb.client.MongoClient;
//import com.mongodb.client.MongoClients;
//import com.mongodb.client.MongoCollection;
//import com.mongodb.client.MongoDatabase;
//import com.mongodb.client.model.Filters;
//import org.bson.Document;
//import org.bson.conversions.Bson;
//import org.springframework.stereotype.Controller;
//
//import java.lang.reflect.Array;
//import java.util.ArrayList;
//import java.util.Dictionary;
//import java.util.List;
//
//@Controller
//public class PackageController {
//    private MongoCollection<Document> packageCollection;
//    private MongoCollection<Document> packageBookingCollection;
//    private MongoCollection<Document> flightCollection;
//    private MongoCollection<Document> trainCollection;
//    private MongoCollection<Document> hotelCollection;
//    private MongoCollection<Document> villaCollection;
//    private FlightController flightController;
//    private TrainController trainController;
//    private HotelController hotelController;
//    private VillaController villaController;
//
//    private List<Flight> packageList;
//
//    private Package createdPackage;
//
//
//    public PackageController() {
//        try {
//            MongoClient connectedClient = MongoClients.create("mongodb://localhost:27017/");
//            MongoDatabase database = connectedClient.getDatabase("Travel_Management_System");
//
//
//            packageCollection = database.getCollection("packages");
//            packageBookingCollection = database.getCollection("packageBookings");
//            flightCollection = database.getCollection("flights");
//            trainCollection = database.getCollection("trains");
//            hotelCollection = database.getCollection("hotels");
//            villaCollection = database.getCollection("villas");
//
//            flightController = new FlightController();
//            trainController = new TrainController();
//            hotelController = new HotelController();
//            villaController = new VillaController();
//
//        } catch (MongoException e) {
//            throw new RuntimeException("Error connecting to MongoDB: " + e.getMessage());
//        }
//    }
//
//    public Package toPackage(Document doc) {
//        String packageName = doc.getString("packageName");
//        System.out.println("package name" + packageName);
//        List<String> flightNumbers = doc.getList("flightNumbers", String.class);
//        List<String> trainNumbers = doc.getList("trainNumbers", String.class);
//        List<String> hotelNames = doc.getList("hotelNames", String.class);
//        String villaName = doc.getString("villaName");
//        double totalPrice = doc.getDouble("totalPrice");
//
//        Package createdPackage = buildPackage(packageName, totalPrice, flightNumbers, trainNumbers, hotelNames, villaName);
//
//        return createdPackage;
//    }
//
//
//    public void display() {
//        System.out.println("Created Package: " + createdPackage);
//    }
//
//    public Package getCreatedPackage() {
//        return createdPackage;
//    }
//
////    public List<Flight> getAllFlights() {
////        Bson filter = Filters.ne("numberOfSeatsInFlight", 0);
////        packageList = packageCollection.find(filter).map(this::toPackage).into(new ArrayList<>());
////        return packageList;
////    }
//
//
//    private Package buildPackage(String packageName, double totalPrice, List<String> flightNumbers,
//                                 List<String> trainNumbers, List<String> hotelNames, String villaName) {
//        // Create a new PackageBuilder instance
//        PackageBuilder packageBuilder = new PackageBuilder();
//
//        // Start building the package
////        packageBuilder.setPackageName(packageName)
////                .setTotalPrice(totalPrice);
//
//        packageBuilder.startNewPackage(packageName);
//
//        // Retrieve detailed information about flights
//        for (String flightNumber : flightNumbers) {
//            Document flightDoc = flightCollection.find(Filters.eq("flightNumber", flightNumber)).first();
//            if (flightDoc != null) {
//                Flight flight = flightController.toFlight(flightDoc); // Assuming you have a method to convert Document to Flight object
//                packageBuilder.addFlight(flight);
//            }
//        }
//
//        // Retrieve detailed information about trains
//        for (String trainNumber : trainNumbers) {
//            Document trainDoc = trainCollection.find(Filters.eq("trainNumber", trainNumber)).first();
//            if (trainDoc != null) {
//                Train train = trainController.toTrain(trainDoc); // Assuming you have a method to convert Document to Train object
//                packageBuilder.addTrain(train);
//            }
//        }
//
//        // Retrieve detailed information about hotels
//        for (String hotelName : hotelNames) {
//            Document hotelDoc = hotelCollection.find(Filters.eq("name", hotelName)).first();
//            if (hotelDoc != null) {
//                Hotel hotel = hotelController.toHotel(hotelDoc); // Assuming you have a method to convert Document to Hotel object
//                packageBuilder.addHotel(hotel);
//            }
//        }
//
//        // Retrieve detailed information about villas
//        Document villaDoc = villaCollection.find(Filters.eq("villaName", villaName)).first();
//        if (villaDoc != null) {
//            Villa villa = villaController.toVilla(villaDoc); // Assuming you have a method to convert Document to Villa object
//            packageBuilder.setVilla(villa);
//        }
//
//        // Build and return the package
//        return packageBuilder.build();
//    }
//
//}




//package com.example.application.controller;
//import com.example.application.models.Flight;
//import com.example.application.models.Train;
//import com.example.application.models.Hotel;
//import com.example.application.models.Villa;
//import com.example.application.builders.PackageBuilder;
//import com.example.application.models.Package;
//import com.mongodb.MongoException;
//import com.mongodb.client.MongoClient;
//import com.mongodb.client.MongoClients;
//import com.mongodb.client.MongoCollection;
//import com.mongodb.client.MongoDatabase;
//import com.mongodb.client.model.Filters;
//import org.bson.Document;
//import org.springframework.stereotype.Controller;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Controller
//public class PackageController {
//    private MongoCollection<Document> packageCollection;
//    private MongoCollection<Document> flightCollection;
//    private MongoCollection<Document> trainCollection;
//    private MongoCollection<Document> hotelCollection;
//    private MongoCollection<Document> villaCollection;
//    private FlightController flightController;
//    private TrainController trainController;
//    private HotelController hotelController;
//    private VillaController villaController;
//
//    public PackageController() {
//        try {
//            MongoClient connectedClient = MongoClients.create("mongodb://localhost:27017/");
//            MongoDatabase database = connectedClient.getDatabase("Travel_Management_System");
//
//            packageCollection = database.getCollection("packages");
//            flightCollection = database.getCollection("flights");
//            trainCollection = database.getCollection("trains");
//            hotelCollection = database.getCollection("hotels");
//            villaCollection = database.getCollection("villas");
//
//            flightController = new FlightController();
//            trainController = new TrainController();
//            hotelController = new HotelController();
//            villaController = new VillaController();
//
//        } catch (MongoException e) {
//            throw new RuntimeException("Error connecting to MongoDB: " + e.getMessage());
//        }
//    }
//
//    public Package toPackage(Document doc) {
//        String packageName = doc.getString("packageName");
//        List<String> flightNumbers = doc.getList("flightNumbers", String.class);
//        List<String> trainNumbers = doc.getList("trainNumbers", String.class);
//        List<String> hotelNames = doc.getList("hotelNames", String.class);
//        String villaName = doc.getString("villaName");
//        double totalPrice = doc.getDouble("totalPrice");
//
//        Package createdPackage = buildPackage(packageName, totalPrice, flightNumbers, trainNumbers, hotelNames, villaName);
//
//        return createdPackage;
//    }
//
//    private Package buildPackage(String packageName, double totalPrice, List<String> flightNumbers,
//                                 List<String> trainNumbers, List<String> hotelNames, String villaName) {
//        // Retrieve detailed information about flights
//        List<Flight> flights = getFlights(flightNumbers);
//
//        // Retrieve detailed information about trains
//        List<Train> trains = getTrains(trainNumbers);
//
//        // Retrieve detailed information about hotels
//        List<Hotel> hotels = getHotels(hotelNames);
//
//        // Retrieve detailed information about villas
//        Villa villa = getVilla(villaName);
//
//        // Build the package using PackageBuilder
//        return new PackageBuilder()
//                .startNewPackage(packageName)
//                .build();
//    }
//
//    private List<Flight> getFlights(List<String> flightNumbers) {
//        List<Flight> flights = new ArrayList<>();
//        for (String flightNumber : flightNumbers) {
//            Document flightDoc = flightCollection.find(Filters.eq("flightNumber", flightNumber)).first();
//            if (flightDoc != null) {
//                Flight flight = flightController.toFlight(flightDoc);
//                flights.add(flight);
//            }
//        }
//        return flights;
//    }
//
//    private List<Train> getTrains(List<String> trainNumbers) {
//        List<Train> trains = new ArrayList<>();
//        for (String trainNumber : trainNumbers) {
//            Document trainDoc = trainCollection.find(Filters.eq("trainNumber", trainNumber)).first();
//            if (trainDoc != null) {
//                Train train = trainController.toTrain(trainDoc);
//                trains.add(train);
//            }
//        }
//        return trains;
//    }
//
//    private List<Hotel> getHotels(List<String> hotelNames) {
//        List<Hotel> hotels = new ArrayList<>();
//        for (String hotelName : hotelNames) {
//            Document hotelDoc = hotelCollection.find(Filters.eq("name", hotelName)).first();
//            if (hotelDoc != null) {
//                Hotel hotel = hotelController.toHotel(hotelDoc);
//                hotels.add(hotel);
//            }
//        }
//        return hotels;
//    }
//
//    private Villa getVilla(String villaName) {
//        Document villaDoc = villaCollection.find(Filters.eq("villaName", villaName)).first();
//        if (villaDoc != null) {
//            return villaController.toVilla(villaDoc);
//        }
//        return null;
//    }
//}


package com.example.application.controller;

import com.example.application.models.Flight;
import com.example.application.models.Train;
import com.example.application.models.Hotel;
import com.example.application.models.Villa;
import com.example.application.builders.PackageBuilder;
import com.example.application.models.Package;
import com.mongodb.MongoException;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PackageController {
    private MongoCollection<Document> packageCollection;
    private MongoCollection<Document> flightCollection;
    private MongoCollection<Document> trainCollection;
    private MongoCollection<Document> hotelCollection;
    private MongoCollection<Document> villaCollection;
    private FlightController flightController;
    private TrainController trainController;
    private HotelController hotelController;
    private VillaController villaController;

    public PackageController() {
        try {
            MongoClient connectedClient = MongoClients.create("mongodb://localhost:27017/");
            MongoDatabase database = connectedClient.getDatabase("Travel_Management_System");

            packageCollection = database.getCollection("packages");
            flightCollection = database.getCollection("flights");
            trainCollection = database.getCollection("trains");
            hotelCollection = database.getCollection("hotels");
            villaCollection = database.getCollection("villas");

            flightController = new FlightController();
            trainController = new TrainController();
            hotelController = new HotelController();
            villaController = new VillaController();

        } catch (MongoException e) {
            throw new RuntimeException("Error connecting to MongoDB: " + e.getMessage());
        }
    }

    public Package toPackage(Document doc) {
        String packageName = doc.getString("packageName");
        List<String> flightNumbers = doc.getList("flightNumbers", String.class);
        List<String> trainNumbers = doc.getList("trainNumbers", String.class);
        List<String> hotelNames = doc.getList("hotelNames", String.class);
        String villaName = doc.getString("villaName");
        double totalPrice = doc.getDouble("totalPrice");

        Package createdPackage = buildPackage(packageName, totalPrice, flightNumbers, trainNumbers, hotelNames, villaName);

        return createdPackage;
    }

    public List<Package> getAllPackages() {
        List<Package> packages = new ArrayList<>();
        packageCollection.find().forEach(doc -> packages.add(toPackage(doc)));
        return packages;
    }

    private Package buildPackage(String packageName, double totalPrice, List<String> flightNumbers,
                                 List<String> trainNumbers, List<String> hotelNames, String villaName) {
        // Retrieve detailed information about flights
        List<Flight> flights = getFlights(flightNumbers);

        // Retrieve detailed information about trains
        List<Train> trains = getTrains(trainNumbers);

        // Retrieve detailed information about hotels
        List<Hotel> hotels = getHotels(hotelNames);

        // Retrieve detailed information about villas
        Villa villa = getVilla(villaName);

        // Build the package using PackageBuilder
        return new PackageBuilder()
                .startNewPackage(packageName)
                .setTotalPrice(totalPrice)
                .addFlights(flights)
                .addTrains(trains)
                .addHotels(hotels)
                .setVilla(villa)
                .build();
    }

    public List<Flight> getFlights(List<String> flightNumbers) {
        List<Flight> flights = new ArrayList<>();
        for (String flightNumber : flightNumbers) {
            Document flightDoc = flightCollection.find(Filters.eq("flightNumber", flightNumber)).first();
            if (flightDoc != null) {
                Flight flight = flightController.toFlight(flightDoc);
                flights.add(flight);
            }
        }
        return flights;
    }

    public List<Train> getTrains(List<String> trainNumbers) {
        List<Train> trains = new ArrayList<>();
        for (String trainNumber : trainNumbers) {
            Document trainDoc = trainCollection.find(Filters.eq("trainNumber", trainNumber)).first();
            if (trainDoc != null) {
                Train train = trainController.toTrain(trainDoc);
                trains.add(train);
            }
        }
        return trains;
    }

    public List<Hotel> getHotels(List<String> hotelNames) {
        List<Hotel> hotels = new ArrayList<>();
        for (String hotelName : hotelNames) {
            Document hotelDoc = hotelCollection.find(Filters.eq("name", hotelName)).first();
            if (hotelDoc != null) {
                Hotel hotel = hotelController.toHotel(hotelDoc);
                hotels.add(hotel);
            }
        }
        return hotels;
    }

    public Villa getVilla(String villaName) {
        Document villaDoc = villaCollection.find(Filters.eq("villaName", villaName)).first();
        if (villaDoc != null) {
            return villaController.toVilla(villaDoc);
        }
        return null;
    }
}


