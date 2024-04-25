package com.example.application.views;

import com.example.application.controller.FlightController;
import com.example.application.controller.HotelController;
import com.example.application.controller.TrainController;
import com.example.application.controller.VillaController;
import com.example.application.controller.PackageController;
import com.example.application.manager.BookingManager;
import com.example.application.models.*;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.charts.model.Dial;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;

@Route(value = "manage-bookings", layout = MainLayout.class)
public class ManageBookingView extends VerticalLayout {

    FlightController flightController;
    TrainController trainController;
    HotelController hotelController;
    VillaController villaController;

    PackageController packageController;
    public ManageBookingView(
            FlightController flightController,
            TrainController trainController,
            HotelController hotelController,
            VillaController villaController
    ){
        this.flightController = flightController;
        this.trainController = trainController;
        this.hotelController = hotelController;
        this.villaController = villaController;
        BookingManager bookingManager = new BookingManager();
        add(new H2("All Flights Bookings"));

        Grid<FlightBooking> flightBookingGrid = new Grid<>(FlightBooking.class, false);
        flightBookingGrid.addColumn(FlightBooking::getId).setHeader("Booking ID").setAutoWidth(true);
        flightBookingGrid.addColumn(FlightBooking::getUsername).setHeader("Username").setAutoWidth(true);
        flightBookingGrid.addColumn(FlightBooking::getFlightNumber).setHeader("Flight Number").setAutoWidth(true);
        flightBookingGrid.addColumn(FlightBooking::getFlightName).setHeader("Flight Name").setAutoWidth(true);
        flightBookingGrid.addColumn(FlightBooking::getDepartureDate).setHeader("Departure Date").setAutoWidth(true);
        flightBookingGrid.addColumn(FlightBooking::getArrivalDate).setHeader("Arrival Date").setAutoWidth(true);
        flightBookingGrid.addColumn(FlightBooking::getSeatsBooked).setHeader("Seats Booked").setAutoWidth(true);
        flightBookingGrid.addColumn(FlightBooking::getTotalPrice).setHeader("Total Price").setAutoWidth(true);
        flightBookingGrid.addColumn(FlightBooking::getDateBooked).setHeader("Date Booked").setAutoWidth(true);

        flightBookingGrid.addComponentColumn(flightBooking -> {
           Button editButton = new Button("Edit");
           editButton.addClickListener(e->{
               //handle edit operations
               Dialog dialog = new Dialog();
               dialog.setModal(true);
               dialog.setCloseOnEsc(true);
               dialog.setCloseOnOutsideClick(true);

               TextField FlightNumberField = new TextField("Flight Number");
               TextField FlightNameField = new TextField("Flight Name");
               TextField DepartureDateField = new TextField("Departure Date");
               TextField ArrivalDateField = new TextField("Arrival Date");
               TextField SeatsBookedField = new TextField("Seats booked");
               TextField totalPriceField = new TextField("Total Price");

               FlightNumberField.setValue(flightBooking.getFlightNumber());
               FlightNameField.setValue(flightBooking.getFlightName());
               DepartureDateField.setValue(flightBooking.getDepartureDate());
               ArrivalDateField.setValue((flightBooking.getArrivalDate()));
               SeatsBookedField.setValue(flightBooking.getSeatsBooked());
               totalPriceField.setValue(flightBooking.getTotalPrice());

               Button saveButton = new Button("save", e1 -> {
                   flightBooking.setFlightNumber(FlightNumberField.getValue());
                   flightBooking.setFlightName(FlightNameField.getValue());
                   flightBooking.setDepartureDate(DepartureDateField.getValue());
                   flightBooking.setArrivalDate((ArrivalDateField.getValue()));
                   flightBooking.setTotalPrice(totalPriceField.getValue());
                   flightController.updateFlightBooking(flightBooking);
                   flightBookingGrid.getDataProvider().refreshAll();
                   dialog.close();
               });

               VerticalLayout layout = new VerticalLayout();
               layout.add(FlightNumberField, FlightNameField, DepartureDateField, ArrivalDateField, SeatsBookedField, totalPriceField);
               dialog.add(layout);
               dialog.getFooter().add(saveButton);

               dialog.open();
           });
           return editButton;
        }).setHeader("Edit").setAutoWidth(true);

        flightBookingGrid.addComponentColumn(flightBooking -> {
            Dialog dialog = new Dialog();
            dialog.setCloseOnEsc(true);
            dialog.setModal(true);
            dialog.setHeaderTitle("Do you really want to delete?");
            Button deleteButton = new Button("Delete", (e) -> dialog.open());
            deleteButton.addThemeVariants(ButtonVariant.LUMO_ERROR);
            Button confirmButton = new Button("Confirm", (e)->{
                System.out.println("in view: "+ flightBooking);
                flightController.deleteFlightBooking(flightBooking);
                flightBookingGrid.getDataProvider().refreshAll();
                dialog.close();
            });
            confirmButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY, ButtonVariant.LUMO_ERROR);
            confirmButton.getStyle().set("margin-right", "auto");
            dialog.getFooter().add(confirmButton);
            return  deleteButton;
        }).setHeader("Delete").setAutoWidth(true);

        flightBookingGrid.setDataProvider(new ListDataProvider<>(bookingManager.getAllBookingsFlight()));
        add(flightBookingGrid);

        //TRAIN_BOOKINGS-------------------------------------------

        add(new H2("All Train Bookings"));

        Grid<TrainBooking> trainBookingGrid = new Grid<>(TrainBooking.class, false);
        trainBookingGrid.addColumn(TrainBooking::getId).setHeader("Booking ID").setAutoWidth(true);
        trainBookingGrid.addColumn(TrainBooking::getUsername).setHeader("Username").setAutoWidth(true);
        trainBookingGrid.addColumn(TrainBooking::getTrainNumber).setHeader("Train Number").setAutoWidth(true);
        trainBookingGrid.addColumn(TrainBooking::getTrainName).setHeader("Train Name").setAutoWidth(true);
        trainBookingGrid.addColumn(TrainBooking::getDepartureDate).setHeader("Departure Date").setAutoWidth(true);
        trainBookingGrid.addColumn(TrainBooking::getArrivalDate).setHeader("Arrival Date").setAutoWidth(true);
        trainBookingGrid.addColumn(TrainBooking::getSeatsBooked).setHeader("Seats Booked").setAutoWidth(true);
        trainBookingGrid.addColumn(TrainBooking::getTotalPrice).setHeader("Total Price").setAutoWidth(true);
        trainBookingGrid.addColumn(TrainBooking::getDateBooked).setHeader("Date Booked").setAutoWidth(true);

        trainBookingGrid.addComponentColumn(trainBooking -> {
            Button editButton = new Button("Edit");
            editButton.addClickListener(e->{
                //handle edit operations
                Dialog dialog = new Dialog();
                dialog.setModal(true);
                dialog.setCloseOnEsc(true);
                dialog.setCloseOnOutsideClick(true);

                TextField trainNumberField = new TextField("Train number");
                TextField trainNameField = new TextField("Train name");
                TextField departureDateField = new TextField("Departure Date");
                TextField arrivalDateField = new TextField("Arrival Date");
                TextField seatsBookedField = new TextField("Seats Booked");
                TextField totalPriceField = new TextField("Total Price");

                trainNumberField.setValue(trainBooking.getTrainNumber());
                trainNameField.setValue(trainBooking.getTrainName());
                departureDateField.setValue(trainBooking.getDepartureDate());
                arrivalDateField.setValue(trainBooking.getArrivalDate());
                seatsBookedField.setValue(trainBooking.getSeatsBooked());
                totalPriceField.setValue(trainBooking.getTotalPrice());

                Button saveButton = new Button("save", e1 -> {
                    trainBooking.setTrainName(trainNameField.getValue());
                    trainBooking.setTrainNumber(trainNumberField.getValue());
                    trainBooking.setDepartureDate(departureDateField.getValue());
                    trainBooking.setArrivalDate(arrivalDateField.getValue());
                    trainBooking.setSeatsBooked(seatsBookedField.getValue());
                    trainBooking.setTotalPrice(totalPriceField.getValue());

                    trainController.updateTrainBooking(trainBooking);
                    trainBookingGrid.getDataProvider().refreshAll();
                    dialog.close();
                });

                VerticalLayout layout = new VerticalLayout();
                layout.add(trainNumberField, trainNameField, departureDateField, arrivalDateField, seatsBookedField, totalPriceField);
                dialog.add(layout);
                dialog.getFooter().add(saveButton);

                dialog.open();

            });
            return editButton;
        }).setHeader("Edit").setAutoWidth(true);

        trainBookingGrid.addComponentColumn(trainBooking -> {
            Dialog dialog = new Dialog();
            dialog.setCloseOnEsc(true);
            dialog.setModal(true);
            dialog.setHeaderTitle("Do you really want to delete?");
            Button deleteButton = new Button("Delete", (e) -> dialog.open());
            deleteButton.addThemeVariants(ButtonVariant.LUMO_ERROR);
            Button confirmButton = new Button("Confirm", (e)->{
                System.out.println("in view: "+ trainBooking);
                trainController.deleteTrainBooking(trainBooking);
                trainBookingGrid.getDataProvider().refreshAll();
                dialog.close();
            });
            confirmButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY, ButtonVariant.LUMO_ERROR);
            confirmButton.getStyle().set("margin-right", "auto");
            dialog.getFooter().add(confirmButton);
            return  deleteButton;
        }).setHeader("Delete").setAutoWidth(true);

        trainBookingGrid.setDataProvider(new ListDataProvider<>(bookingManager.getAllBookingsTrain()));
        add(trainBookingGrid);

        //HOTEL_BOOKING----------------------------------------------------------------
        add(new H2("All Hotel Bookings"));

        Grid<HotelBooking> hotelBookingGrid = new Grid<>(HotelBooking.class, false);
        hotelBookingGrid.addColumn(HotelBooking::getId).setHeader("Booking ID").setAutoWidth(true);
        hotelBookingGrid.addColumn(HotelBooking::getUsername).setHeader("Username").setAutoWidth(true);
        hotelBookingGrid.addColumn(HotelBooking::getHotelName).setHeader("Hotel Name").setAutoWidth(true);
        hotelBookingGrid.addColumn(HotelBooking::getLocation).setHeader("Location").setAutoWidth(true);
        hotelBookingGrid.addColumn(HotelBooking::getDeluxeRoomsBooked).setHeader("Deluxe Rooms Booked").setAutoWidth(true);
        hotelBookingGrid.addColumn(HotelBooking::getStandardRoomsBooked).setHeader("Standard Rooms Booked").setAutoWidth(true);
        hotelBookingGrid.addColumn(HotelBooking::getTotalPrice).setHeader("Total Price").setAutoWidth(true);
        hotelBookingGrid.addColumn(HotelBooking::getDateBooked).setHeader("Date Booked").setAutoWidth(true);

        hotelBookingGrid.addComponentColumn(hotelBooking -> {
            Button editButton = new Button("Edit");
                //handle edit operations
                editButton.addClickListener(e->{
                    //handle edit operations
                    Dialog dialog = new Dialog();
                    dialog.setModal(true);
                    dialog.setCloseOnEsc(true);
                    dialog.setCloseOnOutsideClick(true);

                    TextField hotelNameField = new TextField("Hotel Name");
                    TextField locationField = new TextField("Location");
                    TextField deluxeRoomsBookedField = new TextField("Deluxe Rooms Booked");
                    TextField standardRoomsBookedField = new TextField("Standard Rooms Booked");
                    TextField totalPriceField = new TextField("Total Price");
                    TextField dateBookedField = new TextField("Date Booked");

                    hotelNameField.setValue(hotelBooking.getHotelName());
                    locationField.setValue(hotelBooking.getLocation());
                    deluxeRoomsBookedField.setValue(hotelBooking.getDeluxeRoomsBooked());
                    standardRoomsBookedField.setValue(hotelBooking.getStandardRoomsBooked());
                    totalPriceField.setValue(hotelBooking.getTotalPrice());
                    dateBookedField.setValue(hotelBooking.getDateBooked());

                    Button saveButton = new Button("Save", e1 -> {
                        hotelBooking.setHotelName(hotelNameField.getValue());
                        hotelBooking.setLocation(locationField.getValue());
                        hotelBooking.setDeluxeRoomsBooked(deluxeRoomsBookedField.getValue());
                        hotelBooking.setStandardRoomsBooked(standardRoomsBookedField.getValue());
                        hotelBooking.setTotalPrice(totalPriceField.getValue());
                        hotelBooking.setDateBooked(dateBookedField.getValue());


                        hotelController.updateHotelBooking(hotelBooking);


                        hotelBookingGrid.getDataProvider().refreshAll();
                        dialog.close();
                    });

                    VerticalLayout layout = new VerticalLayout();
                    layout.add(hotelNameField, locationField, deluxeRoomsBookedField, standardRoomsBookedField, totalPriceField, dateBookedField);
                    dialog.add(layout);
                    dialog.getFooter().add(saveButton);

                    dialog.open();
                });
            return editButton;
        }).setHeader("Edit").setAutoWidth(true);

        hotelBookingGrid.addComponentColumn(hotelBooking -> {
            Dialog dialog = new Dialog();
            dialog.setCloseOnEsc(true);
            dialog.setModal(true);
            dialog.setHeaderTitle("Do you really want to delete?");
            Button deleteButton = new Button("Delete", (e) -> dialog.open());
            deleteButton.addThemeVariants(ButtonVariant.LUMO_ERROR);
            Button confirmButton = new Button("Confirm", (e)->{
                System.out.println("in view: "+ hotelBooking);
                hotelController.deletehotelBooking(hotelBooking);
                hotelBookingGrid.getDataProvider().refreshAll();
                dialog.close();
            });
            confirmButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY, ButtonVariant.LUMO_ERROR);
            confirmButton.getStyle().set("margin-right", "auto");
            dialog.getFooter().add(confirmButton);
            return  deleteButton;
        }).setHeader("Delete").setAutoWidth(true);

        hotelBookingGrid.setDataProvider((new ListDataProvider<>(bookingManager.getAllBookingsHotel())));
        add(hotelBookingGrid);

        //VILLA_BOOKING--------------------------------------------------------------------------------
        add(new H2("All Villa Bookings"));
        Grid<VillaBooking> villaBookingGrid = new Grid<>(VillaBooking.class, false);
        villaBookingGrid.addColumn(VillaBooking::getId).setHeader("Booking ID").setAutoWidth(true);
        villaBookingGrid.addColumn(VillaBooking::getUsername).setHeader("Username").setAutoWidth(true);
        villaBookingGrid.addColumn(VillaBooking::getVillaName).setHeader("Villa Name").setAutoWidth(true);
        villaBookingGrid.addColumn(VillaBooking::getAddress).setHeader("Address").setAutoWidth(true);
        villaBookingGrid.addColumn(VillaBooking::getTotalPrice).setHeader("Total Price").setAutoWidth(true);
        villaBookingGrid.addColumn(VillaBooking::getDateBooked).setHeader("Date Booked").setAutoWidth(true);

        villaBookingGrid.addComponentColumn(villaBooking -> {
            Button editButton = new Button("Edit");
            editButton.addClickListener(e->{
                //handle edit operations
            });
            return editButton;
        }).setHeader("Edit").setAutoWidth(true);

        villaBookingGrid.addComponentColumn(villaBooking -> {
            Dialog dialog = new Dialog();
            dialog.setCloseOnEsc(true);
            dialog.setModal(true);
            dialog.setHeaderTitle("Do you really want to delete?");
            Button deleteButton = new Button("Delete", (e) -> dialog.open());
            deleteButton.addThemeVariants(ButtonVariant.LUMO_ERROR);
            Button confirmButton = new Button("Confirm", (e)->{
                System.out.println("in view: "+ villaBooking);
                villaController.deletehotelBooking(villaBooking);
                villaBookingGrid.getDataProvider().refreshAll();
                dialog.close();
            });
            confirmButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY, ButtonVariant.LUMO_ERROR);
            confirmButton.getStyle().set("margin-right", "auto");
            dialog.getFooter().add(confirmButton);
            return  deleteButton;
        }).setHeader("Delete").setAutoWidth(true);


        villaBookingGrid.setDataProvider((new ListDataProvider<>(bookingManager.getAllBookingsVilla())));
        add(villaBookingGrid);

// PACKAGE_BOOKINGS-------------------------------------------
        add(new H2("All Package Bookings"));

        Grid<PackageBooking> packageBookingGrid = new Grid<>(PackageBooking.class, true);
        // Add more columns as needed for package details

        packageBookingGrid.addComponentColumn(packageBooking -> {
            Button editButton = new Button("Edit");
            editButton.addClickListener(e -> {
                // Handle edit operations for package booking
            });
            return editButton;
        }).setHeader("Edit").setAutoWidth(true);

        packageBookingGrid.addComponentColumn(packageBooking -> {
            Dialog dialog = new Dialog();
            dialog.setCloseOnEsc(true);
            dialog.setModal(true);
            dialog.setHeaderTitle("Do you really want to delete?");
            Button deleteButton = new Button("Delete", (e) -> dialog.open());
            deleteButton.addThemeVariants(ButtonVariant.LUMO_ERROR);
            Button confirmButton = new Button("Confirm", (e) -> {
                // Handle delete operations for package booking
//                packageController.deletePackageBooking(packageBooking);
                packageBookingGrid.getDataProvider().refreshAll();
                dialog.close();
            });
            confirmButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY, ButtonVariant.LUMO_ERROR);
            confirmButton.getStyle().set("margin-right", "auto");
            dialog.getFooter().add(confirmButton);
            return deleteButton;
        }).setHeader("Delete").setAutoWidth(true);

        packageBookingGrid.setDataProvider(new ListDataProvider<>(bookingManager.getAllBookingsPackage()));
        add(packageBookingGrid);    }
}
