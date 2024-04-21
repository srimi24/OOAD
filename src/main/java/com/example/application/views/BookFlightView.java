package com.example.application.views;

import com.example.application.controller.FlightController;
import com.example.application.models.Flight;
import com.example.application.views.BookFlightForm;
import com.example.application.views.MainLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;

import java.util.Timer;
import java.util.TimerTask;


@Route(value = "flights",layout = MainLayout.class)
@PageTitle("Book Flight")
public class BookFlightView extends VerticalLayout {

    private Timer timer;

    public BookFlightView(FlightController flightController) {


        add(new H2("All Flights"));

        Grid<Flight> flightGrid = new Grid<>(Flight.class, false);
        flightGrid.addColumn(Flight::getFlightNumber).setHeader("Flight Number").setAutoWidth(true);
        flightGrid.addColumn(Flight::getAirline).setHeader("Airline").setAutoWidth(true);
        flightGrid.addColumn(Flight::getDepartureAirport).setHeader("Departure Airport").setAutoWidth(true);
        flightGrid.addColumn(Flight::getArrivalAirport).setHeader("Arrival Airport").setAutoWidth(true);
        flightGrid.addColumn(Flight::getNumberOfSeatsInFlight).setHeader("No of Seats in Flight").setAutoWidth(true);
        flightGrid.addColumn(Flight::getDepartureDate).setHeader("Departure Date").setAutoWidth(true);
        flightGrid.addColumn(Flight::getArrivalDate).setHeader("Arrival Date").setAutoWidth(true);
        flightGrid.addColumn(Flight::getPrice).setHeader("Price").setAutoWidth(true);

        flightGrid.addComponentColumn(flight -> {
            Button bookButton = new Button("Book");
            bookButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY); // Optional: Apply a visual style
            bookButton.addClickListener(event -> {
                // Add your booking logic here, using flight as the selected flight
                Dialog bookingDialog = new Dialog();
                bookingDialog.add(new BookFlightForm(bookingDialog, flight));
                bookingDialog.open();
            });
            return bookButton;
        }).setHeader("Book");

        flightGrid.setDataProvider(new ListDataProvider<>(flightController.getAllFlights()));
        add(flightGrid);
    }
}