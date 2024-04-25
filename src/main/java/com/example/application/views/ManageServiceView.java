package com.example.application.views;

import com.example.application.controller.FlightController;
import com.example.application.controller.HotelController;
import com.example.application.controller.TrainController;
import com.example.application.controller.VillaController;
import com.example.application.manager.ServiceManager;
import com.example.application.models.Flight;
import com.example.application.models.Hotel;
import com.example.application.models.Train;
import com.example.application.models.Villa;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;

import javax.swing.*;
import java.awt.*;

@Route(value = "manage-service", layout = MainLayout.class)
public class ManageServiceView extends VerticalLayout {

    FlightController flightController;
    TrainController trainController;
    HotelController hotelController;
    VillaController villaController;

    public ManageServiceView(
            FlightController flightController,
            TrainController trainController,
            HotelController hotelController,
            VillaController villaController
    ){
        this.flightController = flightController;
        this.hotelController = hotelController;
        this.trainController = trainController;
        this.villaController = villaController;
        ServiceManager serviceManager = new ServiceManager();

        //MANAGE FLIGHTS
        add(new H1("Manage Flights"));
        Grid<Flight> flightGrid = new Grid<>(Flight.class, true);
        flightGrid.setDataProvider(new ListDataProvider<>(serviceManager.getFlightList()));
        add(flightGrid);

        // MANAGE TRAINS
        add(new H1("Manage Trains"));
        Grid<Train> trainGrid = new Grid<>(Train.class, true);
        trainGrid.setDataProvider(new ListDataProvider<>(serviceManager.getTrainList()));
        add(trainGrid);

        //MANAGE HOTELS
        add(new H1("Manage Hotels"));
        Grid<Hotel> hotelGrid = new Grid<>(Hotel.class, true);
        hotelGrid.setDataProvider(new ListDataProvider<>(serviceManager.getHotelList()));
        add(hotelGrid);

        //MANAGE VILLAS
        add(new H1("Manage Villas"));
        Grid<Villa> villaGrid = new Grid<>(Villa.class, true);
        villaGrid.setDataProvider(new ListDataProvider<>(serviceManager.getVillaList()));
        add(villaGrid);

    }
}
