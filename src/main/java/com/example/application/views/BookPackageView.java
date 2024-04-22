package com.example.application.views;//package com.example.application.views;
//
//import com.vaadin.flow.component.html.H1;
//import com.vaadin.flow.component.orderedlayout.VerticalLayout;
//import com.vaadin.flow.router.Route;
//
//@Route(value="book-package" ,layout = MainLayout.class)
//public class BookPackageView extends VerticalLayout {
//    public BookPackageView(){
//        add(new H1("Book Package"));
//    }
//}


import com.example.application.models.*;
import com.example.application.controller.PackageController;
import com.example.application.models.Package;
import com.example.application.views.MainLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.data.renderer.Renderer;
import com.vaadin.flow.data.renderer.TextRenderer;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import java.util.ArrayList;
import java.util.List;

@Route(value = "package", layout = MainLayout.class)
@PageTitle("Book Package")
public class BookPackageView extends VerticalLayout{
    public  BookPackageView(PackageController packageController){
        Grid<Package> packageGrid = new Grid<>(Package.class);
        packageGrid.setItems(packageController.getAllPackages());
        // Get the existing columns from the packageGrid
        List<Grid.Column<Package>> existingColumns = new ArrayList<>(packageGrid.getColumns());

// Add additional columns dynamically for Flights, Hotels, and Villa
        packageGrid.removeAllColumns(); // Remove all existing columns first



        packageGrid.addColumn(Package::getPackageName).setHeader("Package Name");
        packageGrid.addColumn(Package::getTotalPrice).setHeader("Total Price");
//        packageGrid.addColumn(Package -> {
//            // Get the flight details from the package
//            List<Flight> flights = Package.getFlights();
//
//            // Create a renderer for the flight details
//            Renderer<Package> flightRenderer = new TextRenderer<>(packageItem -> {
//                // Construct a string with flight details for display
//                StringBuilder flightDetails = new StringBuilder();
//                for (Flight flight : flights) {
//                    flightDetails.append(flight.getFlightNumber()).append(", ")
//                            .append(flight.getAirline()).append(", ")
//                            .append(flight.getDepartureDate()).append(", ")
//                            .append(flight.getArrivalDate()).append(", ")
//                            .append(flight.getDepartureAirport()).append(", ")
//                            .append(flight.getArrivalAirport()).append(", ")
//                            .append(flight.getNumberOfSeatsInFlight()).append(", ")
//                            .append(flight.getPrice()).append("\n");
//                }
//                return flightDetails.toString();
//            });
//
//            // Set the renderer to display flight details in the column
//            return flightRenderer;
//        }).setHeader("koo").setAutoWidth(true).setFlexGrow(1);
//        packageGrid.addColumn(
//                Package::getFlights) -> {
//                    Grid<Flight> subGrid = new Grid<>(Flight.class); // Specify the bean type for Flight
//                    subGrid.setItems(Package::getFlights());
//                    subGrid.setColumns("flightNumber", "airline", "arrivalDate", "departureDate",
//                            "departureAirport", "arrivalAirport", "numberOfSeatsInFlight", "price");
//                    return subGrid;
//                }).setHeader("Flights").setAutoWidth(true);;

        packageGrid.addComponentColumn(
                package1 -> {
                    Grid<Flight> subGrid = new Grid<>(Flight.class); // Specify the bean type for Flight
                    subGrid.setItems(package1.getFlights()); // Use package1 to get flight details
                    subGrid.setColumns("flightNumber", "airline", "arrivalDate", "departureDate",
                            "departureAirport", "arrivalAirport", "numberOfSeatsInFlight", "price");
                    return subGrid;
                }).setHeader("Flights").setAutoWidth(true);

        packageGrid.addComponentColumn(package1 -> {
            Grid<Train> subGrid = new Grid<>(Train.class); // Specify the bean type for Train
            subGrid.setItems(package1.getTrains());
            subGrid.setColumns("trainNumber", "trainName", "departureDate", "arrivalDate", "departureStation", "arrivalStation", "numberOfSeatsInTrain", "price");
            return subGrid;
        }).setHeader("Trains").setAutoWidth(true);;

        packageGrid.addComponentColumn(package1 -> {
            Grid<Hotel> subGrid = new Grid<>(Hotel.class); // Specify the bean type for Hotel
            subGrid.setItems(package1.getHotels());
            subGrid.setColumns("name", "location", "numberOfDeluxeRooms", "deluxeRoomPricePerNight",
                    "numberOfStandardRooms", "standardRoomPricePerNight");
            return subGrid;
        }).setHeader("Hotels").setAutoWidth(true);;

        packageGrid.addComponentColumn(package1 -> {
            Grid<Villa> subGrid = new Grid<>(Villa.class); // Specify the bean type for Villa
            subGrid.setItems(package1.getVilla());
            subGrid.setColumns("villaName", "address", "numberOfRooms", "hasPrivateBathroom", "hasKitchenFacilities", "pricePerNight");
            return subGrid;
        }).setHeader("Villa").setAutoWidth(true);;

        // Set auto width for main grid columns
        packageGrid.getColumns().forEach(column -> column.setAutoWidth(true));
//        packageGrid.setDataProvider(new ListDataProvider<>(packageController.getAllPackages()));

        add(packageGrid);
    }
}