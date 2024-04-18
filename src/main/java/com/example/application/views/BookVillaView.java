package com.example.application.views;

import com.example.application.controller.VillaController;
import com.example.application.models.Villa;
import com.example.application.views.BookVillaForm;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route(value = "villas", layout = MainLayout.class)
@PageTitle("Book Villa")
public class BookVillaView extends VerticalLayout {

    public BookVillaView(VillaController villaController) {
        add(new H2("All Villas"));

        Grid<Villa> villaGrid = new Grid<>(Villa.class, false);
        villaGrid.addColumn(Villa::getVillaName).setHeader("Villa Name").setAutoWidth(true);
        villaGrid.addColumn(Villa::getAddress).setHeader("Address").setAutoWidth(true);
        villaGrid.addColumn(Villa::getNumberOfRooms).setHeader("Number of Rooms").setAutoWidth(true);
        villaGrid.addColumn(Villa::getHasPrivateBathroom).setHeader("Private Bathroom").setAutoWidth(true);
        villaGrid.addColumn(Villa::getHasKitchenFacilities).setHeader("Kitchen Facilities").setAutoWidth(true);
        villaGrid.addColumn(Villa::getPricePerNight).setHeader("Price per Night").setAutoWidth(true);

        villaGrid.addComponentColumn(villa -> {
            Button bookButton = new Button("Book");
            bookButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
            bookButton.addClickListener(event -> {
                Dialog bookingDialog = new Dialog();
                bookingDialog.add(new BookVillaForm(bookingDialog, villa));
                bookingDialog.open();
            });
            return bookButton;
        }).setHeader("Book");

        villaGrid.setDataProvider(new ListDataProvider<>(villaController.getAvailableVillas()));
        add(villaGrid);
    }

}
