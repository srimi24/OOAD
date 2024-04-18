package com.example.application.views;

import com.example.application.controller.TrainController;
import com.example.application.models.Train;
import com.example.application.views.BookTrainForm;
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


@Route(value = "trains", layout = MainLayout.class)
@PageTitle("Book Train")
public class BookTrainView extends VerticalLayout {

    private Timer timer;

    public BookTrainView(TrainController trainController) {


        add(new H2("All Trains"));

        Grid<Train> trainGrid = new Grid<>(Train.class, false);
        trainGrid.addColumn(Train::getTrainNumber).setHeader("Train Number").setAutoWidth(true);
        trainGrid.addColumn(Train::getTrainName).setHeader("Train Name").setAutoWidth(true);
        trainGrid.addColumn(Train::getDepartureStation).setHeader("Departure Airport").setAutoWidth(true);
        trainGrid.addColumn(Train::getArrivalStation).setHeader("Arrival Airport").setAutoWidth(true);
        trainGrid.addColumn(Train::getNumberOfSeatsInTrain).setHeader("No of Seats in Train").setAutoWidth(true);
        trainGrid.addColumn(Train::getDepartureDate).setHeader("Departure Date").setAutoWidth(true);
        trainGrid.addColumn(Train::getArrivalDate).setHeader("Arrival Date").setAutoWidth(true);
        trainGrid.addColumn(Train::getPrice).setHeader("Price").setAutoWidth(true);

        trainGrid.addComponentColumn(train -> {
            Button bookButton = new Button("Book");
            bookButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
            bookButton.addClickListener(event -> {
                Dialog bookingDialog = new Dialog();
                bookingDialog.add(new BookTrainForm(bookingDialog, train));
                bookingDialog.open();
            });
            return bookButton;
        }).setHeader("Book");

        trainGrid.setDataProvider(new ListDataProvider<>(trainController.getAllTrains()));
        add(trainGrid);
    }

}