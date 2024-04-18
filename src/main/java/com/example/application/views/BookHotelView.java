package com.example.application.views;

import com.example.application.controller.HotelController;
import com.example.application.models.Hotel;
import com.example.application.views.BookHotelForm;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route(value = "hotels", layout = MainLayout.class)
@PageTitle("Book Hotel")
public class BookHotelView extends VerticalLayout {

    public BookHotelView(HotelController hotelController) {
        add(new H2("All Hotels"));

        Grid<Hotel> hotelGrid = new Grid<>(Hotel.class, false);
        hotelGrid.addColumn(Hotel::getName).setHeader("Hotel Name").setAutoWidth(true);
        hotelGrid.addColumn(Hotel::getLocation).setHeader("Location").setAutoWidth(true);
        hotelGrid.addColumn(Hotel::getNumberOfDeluxeRooms).setHeader("No of Deluxe Rooms").setAutoWidth(true);
        hotelGrid.addColumn(Hotel::getDeluxeRoomPricePerNight).setHeader("Deluxe Room Price/Night").setAutoWidth(true);
        hotelGrid.addColumn(Hotel::getNumberOfStandardRooms).setHeader("No of Standard Rooms").setAutoWidth(true);
        hotelGrid.addColumn(Hotel::getStandardRoomPricePerNight).setHeader("Standard Room Price/Night").setAutoWidth(true);

        hotelGrid.addComponentColumn(hotel -> {
            Button bookButton = new Button("Book");
            bookButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
            bookButton.addClickListener(event -> {
                Dialog bookingDialog = new Dialog();
                bookingDialog.add(new BookHotelForm(bookingDialog, hotel));
                bookingDialog.open();
            });
            return bookButton;
        }).setHeader("Book");

        hotelGrid.setDataProvider(new ListDataProvider<>(hotelController.getAllHotels()));
        add(hotelGrid);
    }
}
