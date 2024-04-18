package com.example.application.views;

import com.example.application.manager.BookingManager;
import com.example.application.models.FlightBooking;
import com.example.application.models.TrainBooking;
import com.example.application.models.HotelBooking;
import com.example.application.models.VillaBooking;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route(value = "view-bookings", layout = MainLayout.class)
@PageTitle("View your Bookings")
public class BookingView extends VerticalLayout {
    public BookingView() {
        BookingManager bookingManager = new BookingManager();
        add(new H2("Flights Bookings Done by You"));

        Grid<FlightBooking> flightBookingGrid = new Grid<>(FlightBooking.class, false);
        flightBookingGrid.addColumn(FlightBooking::getId).setHeader("Booking ID").setAutoWidth(true);
        flightBookingGrid.addColumn(FlightBooking::getFlightNumber).setHeader("Flight Number").setAutoWidth(true);
        flightBookingGrid.addColumn(FlightBooking::getFlightName).setHeader("Flight Name").setAutoWidth(true);
        flightBookingGrid.addColumn(FlightBooking::getDepartureDate).setHeader("Departure Date").setAutoWidth(true);
        flightBookingGrid.addColumn(FlightBooking::getArrivalDate).setHeader("Arrival Date").setAutoWidth(true);
        flightBookingGrid.addColumn(FlightBooking::getSeatsBooked).setHeader("Seats Booked").setAutoWidth(true);
        flightBookingGrid.addColumn(FlightBooking::getTotalPrice).setHeader("Total Price").setAutoWidth(true);
        flightBookingGrid.addColumn(FlightBooking::getDateBooked).setHeader("Date Booked").setAutoWidth(true);


        flightBookingGrid.addComponentColumn(flightBooking -> {
            if ("NO".equals(flightBooking.getPaid())) {
                Button button = new Button("Pay");
                button.addThemeVariants(ButtonVariant.LUMO_SUCCESS);
                button.addClickListener(event -> {
                // Add Payment Functionality Here
                });
                return button;
            } else {
                return new Text("Paid");
            }
        }).setHeader("Pay Amount").setAutoWidth(true);

        flightBookingGrid.setDataProvider(new ListDataProvider<>(bookingManager.getAllFlightBookings()));
        add(flightBookingGrid);

        add(new H2("Train Bookings Done by You"));

        Grid<TrainBooking> trainBookingGrid = new Grid<>(TrainBooking.class, false);
        trainBookingGrid.addColumn(TrainBooking::getId).setHeader("Booking ID").setAutoWidth(true);
        trainBookingGrid.addColumn(TrainBooking::getTrainNumber).setHeader("Train Number").setAutoWidth(true);
        trainBookingGrid.addColumn(TrainBooking::getTrainName).setHeader("Train Name").setAutoWidth(true);
        trainBookingGrid.addColumn(TrainBooking::getDepartureDate).setHeader("Departure Date").setAutoWidth(true);
        trainBookingGrid.addColumn(TrainBooking::getArrivalDate).setHeader("Arrival Date").setAutoWidth(true);
        trainBookingGrid.addColumn(TrainBooking::getSeatsBooked).setHeader("Seats Booked").setAutoWidth(true);
        trainBookingGrid.addColumn(TrainBooking::getTotalPrice).setHeader("Total Price").setAutoWidth(true);
        trainBookingGrid.addColumn(TrainBooking::getDateBooked).setHeader("Date Booked").setAutoWidth(true);

        trainBookingGrid.addComponentColumn(trainBooking -> {
            if ("NO".equals(trainBooking.getPaid())) {
                Button button = new Button("Pay");
                button.addThemeVariants(ButtonVariant.LUMO_SUCCESS);
                button.addClickListener(event -> {
                    // Add Payment Functionality Here
                });
                return button;
            } else {
                return new Text("Paid");
            }
        }).setHeader("Pay Amount").setAutoWidth(true);

        trainBookingGrid.setDataProvider(new ListDataProvider<>(bookingManager.getAllTrainBookings()));
        add(trainBookingGrid);

        add(new H2("Hotel Bookings Done by You"));

        Grid<HotelBooking> hotelBookingGrid = new Grid<>(HotelBooking.class, false);
        hotelBookingGrid.addColumn(HotelBooking::getId).setHeader("Booking ID").setAutoWidth(true);
        hotelBookingGrid.addColumn(HotelBooking::getHotelName).setHeader("Hotel Name").setAutoWidth(true);
        hotelBookingGrid.addColumn(HotelBooking::getLocation).setHeader("Location").setAutoWidth(true);
        hotelBookingGrid.addColumn(HotelBooking::getDeluxeRoomsBooked).setHeader("Deluxe Rooms Booked").setAutoWidth(true);
        hotelBookingGrid.addColumn(HotelBooking::getStandardRoomsBooked).setHeader("Standard Rooms Booked").setAutoWidth(true);
        hotelBookingGrid.addColumn(HotelBooking::getTotalPrice).setHeader("Total Price").setAutoWidth(true);
        hotelBookingGrid.addColumn(HotelBooking::getDateBooked).setHeader("Date Booked").setAutoWidth(true);

        hotelBookingGrid.addComponentColumn(hotelBooking -> {
            if ("NO".equals(hotelBooking.getPaid())) {
                Button button = new Button("Pay");
                button.addThemeVariants(ButtonVariant.LUMO_SUCCESS);
                button.addClickListener(event -> {
                    // Add Payment Functionality Here
                });
                return button;
            } else {
                return new Text("Paid");
            }
        }).setHeader("Pay Amount").setAutoWidth(true);

        hotelBookingGrid.setDataProvider(new ListDataProvider<>(bookingManager.getAllHotelBookings()));
        add(hotelBookingGrid);

        add(new H2("Villa Bookings Done by You"));

        Grid<VillaBooking> villaBookingGrid = new Grid<>(VillaBooking.class, false);
        villaBookingGrid.addColumn(VillaBooking::getId).setHeader("Booking ID").setAutoWidth(true);
        villaBookingGrid.addColumn(VillaBooking::getVillaName).setHeader("Villa Name").setAutoWidth(true);
        villaBookingGrid.addColumn(VillaBooking::getAddress).setHeader("Address").setAutoWidth(true);
        villaBookingGrid.addColumn(VillaBooking::getTotalPrice).setHeader("Total Price").setAutoWidth(true);
        villaBookingGrid.addColumn(VillaBooking::getDateBooked).setHeader("Date Booked").setAutoWidth(true);

        villaBookingGrid.addComponentColumn(villaBooking -> {
            if ("NO".equals(villaBooking.getPaid())) {
                Button button = new Button("Pay");
                button.addThemeVariants(ButtonVariant.LUMO_SUCCESS);
                button.addClickListener(event -> {
                    // Add Payment Functionality Here
                });
                return button;
            } else {
                return new Text("Paid");
            }
        }).setHeader("Pay Amount").setAutoWidth(true);

        villaBookingGrid.setDataProvider(new ListDataProvider<>(bookingManager.getAllVillaBookings()));
        add(villaBookingGrid);

    }
}
