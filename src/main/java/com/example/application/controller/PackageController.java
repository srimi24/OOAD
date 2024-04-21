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
//import java.util.Map;
//
//@Controller
//public class PackageController {
//    private MongoCollection<Document> packageCollection;
//    private MongoCollection<Document> flightCollection;
//    private MongoCollection<Document> trainCollection;
//    private MongoCollection<Document> hotelCollection;
//    private MongoCollection<Document> villaCollection;
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
//        } catch (MongoException e) {
//            throw new RuntimeException("Error connecting to MongoDB: " + e.getMessage());
//        }
//    }
//
//    public void displayPackageDetails(String packageId) {
//        try {
//            Document packageDocument = packageCollection.find(Filters.eq("_id", packageId)).first();
//            if (packageDocument != null) {
//                String packageName = packageDocument.getString("packageName");
//                List<String> flightNumbers = packageDocument.getList("flightNumbers", String.class);
//                List<String> trainNumbers = packageDocument.getList("trainNumbers", String.class);
//                List<String> hotelNames = packageDocument.getList("hotelNames", String.class);
//                String villaName = packageDocument.getString("villaName");
//                double totalPrice = packageDocument.getDouble("totalPrice");
//
//                // Retrieve detailed information about flights
//                List<Document> flights = flightCollection.find(Filters.in("flightNumber", flightNumbers)).into(new ArrayList<>());
//
//                // Retrieve detailed information about trains
//                List<Document> trains = trainCollection.find(Filters.in("trainNumber", trainNumbers)).into(new ArrayList<>());
//
//                // Retrieve detailed information about hotels
//                List<Document> hotels = hotelCollection.find(Filters.in("name", hotelNames)).into(new ArrayList<>());
//
//                // Retrieve detailed information about villas
//                Document villa = villaCollection.find(Filters.eq("villaName", villaName)).first();
//
//                // Display or process the retrieved information as needed
//                System.out.println("Package Name: " + packageName);
//                System.out.println("Flight Numbers: " + flightNumbers);
//                System.out.println("Train Numbers: " + trainNumbers);
//                System.out.println("Hotel Names: " + hotelNames);
//                System.out.println("Villa Name: " + villaName);
//                System.out.println("Total Price: " + totalPrice);
//                // Process other retrieved information as needed
//            } else {
//                throw new RuntimeException("Package with ID " + packageId + " not found.");
//            }
//        } catch (Exception e) {
//            // Handle exceptions
//            e.printStackTrace();
//        }
//    }
//}
package com.example.application.controller;
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
import java.util.Dictionary;
import java.util.List;

@Controller
public class PackageController {
    private Dictionary<any, any>
    private MongoCollection<Document> packageCollection;
    private MongoCollection<Document> flightCollection;
    private MongoCollection<Document> trainCollection;
    private MongoCollection<Document> hotelCollection;
    private MongoCollection<Document> villaCollection;

    public PackageController() {
        try {
            MongoClient connectedClient = MongoClients.create("mongodb://localhost:27017/");
            MongoDatabase database = connectedClient.getDatabase("Travel_Management_System");

            packageCollection = database.getCollection("packages");
            flightCollection = database.getCollection("flights");
            trainCollection = database.getCollection("trains");
            hotelCollection = database.getCollection("hotels");
            villaCollection = database.getCollection("villas");
        } catch (MongoException e) {
            throw new RuntimeException("Error connecting to MongoDB: " + e.getMessage());
        }
    }

    public void displayPackageDetails(String packageId) {
        try {
            Document packageDocument = packageCollection.find(Filters.eq("_id", packageId)).first();
            if (packageDocument != null) {
                String packageName = packageDocument.getString("packageName");
                System.out.println("hiiiii");
                List<String> flightNumbers = packageDocument.getList("flightNumbers", String.class);
                List<String> trainNumbers = packageDocument.getList("trainNumbers", String.class);
                List<String> hotelNames = packageDocument.getList("hotelNames", String.class);
                String villaName = packageDocument.getString("villaName");
                double totalPrice = packageDocument.getDouble("totalPrice");

                // Call method to build the package using retrieved data
                Package createdPackage = buildPackage(packageName, totalPrice, flightNumbers, trainNumbers, hotelNames, villaName);

                // Display or process the created package as needed
                System.out.println("Created Package: " + createdPackage);
            } else {
                throw new RuntimeException("Package with ID " + packageId + " not found.");
            }
        } catch (Exception e) {
            // Handle exceptions
            e.printStackTrace();
        }
    }

    private Package buildPackage(String packageName, double totalPrice, List<String> flightNumbers,
                                 List<String> trainNumbers, List<String> hotelNames, String villaName) {
        // Create a new PackageBuilder instance
        PackageBuilder packageBuilder = new PackageBuilder();

        // Start building the package
        packageBuilder.setPackageName(packageName)
                .setTotalPrice(totalPrice);

        // Retrieve detailed information about flights
        List<Document> flights = flightCollection.find(Filters.in("flightNumber", flightNumbers)).into(new ArrayList<>());
        for (Document flight : flights) {
            packageBuilder.addFlight(flight.getString("flightNumber")); // Assuming "flightNumber" is the key in flight documents
        }

        // Retrieve detailed information about trains
        List<Document> trains = trainCollection.find(Filters.in("trainNumber", trainNumbers)).into(new ArrayList<>());
        for (Document train : trains) {
            packageBuilder.addTrain(train.getString("trainNumber")); // Assuming "trainNumber" is the key in train documents
        }

        // Retrieve detailed information about hotels
        List<Document> hotels = hotelCollection.find(Filters.in("name", hotelNames)).into(new ArrayList<>());
        for (Document hotel : hotels) {
            packageBuilder.addHotel(hotel.getString("name")); // Assuming "name" is the key in hotel documents
        }

        // Set the villa name for the package
        packageBuilder.setVilla(villaName);

        // Build and return the package
        return packageBuilder.build();
    }
}


