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


import com.example.application.models.Flight;
import com.example.application.controller.PackageController;
import com.example.application.models.Package;
import com.example.application.views.MainLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route(value = "package", layout = MainLayout.class)
@PageTitle("Book Package")
public class BookPackageView extends VerticalLayout{
    public  BookPackageView(PackageController packageController){
        Grid<Package> packageGrid = new Grid<>(Package.class, false);
//        packageGrid.addColumn(Package :: getFlights).setHeader("Flights").setAutoWidth(true);
        // Add columns for Flight details
        System.out.println("hiiiiiiiiiiiiiiiiii");
//        packageGrid.addColumn(Package -> Package.getFlights().size()).setHeader("Number of Flights");
        packageGrid.addColumn(Package::getFlights).setHeader("Flights");
        packageController.display();
        // Add additional columns for each field in Flight class
//        packageGrid.addColumn(Flight::getFlightNumber).setHeader("Flight Number");
//        packageGrid.addColumn(Flight::getAirline).setHeader("Airline");
//        packageGrid.addColumn(Flight::getArrivalDate).setHeader("Arrival Date");
//        packageGrid.addColumn(Flight::getDepartureDate).setHeader("Departure Date");
//        packageGrid.addColumn(Flight::getDepartureAirport).setHeader("Departure Airport");
//        packageGrid.addColumn(Flight::getArrivalAirport).setHeader("Arrival Airport");
//        packageGrid.addColumn(Flight::getNumberOfSeatsInFlight).setHeader("Number of Seats");
//        packageGrid.addColumn(Flight::getPrice).setHeader("Price");

        // Set auto width for columns
        packageGrid.getColumns().forEach(column -> column.setAutoWidth(true));

//        packageGrid.setItems(packageController.getFlights());

        add(packageGrid);
    }
}