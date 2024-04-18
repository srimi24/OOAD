package com.example.application.views;

import com.example.application.controller.UserController;
import com.example.application.models.User;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.charts.model.Dial;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.router.Route;

import java.awt.*;

@Route(value = "manage-user", layout = MainLayout.class)
public class ManageUsersView extends VerticalLayout {

    private final Grid<User> userGrid;
    UserController userController;
    public ManageUsersView(UserController userController){
        this.userController = userController;
        add(new H1("Manage Users"));
        userGrid = new Grid<>();
        userGrid.addColumn(User::getUsername).setHeader("Username");
        userGrid.addColumn(User::getRole).setHeader("Role");
        userGrid.addComponentColumn(user -> {
            Button editButton = new Button("Edit");
            editButton.addClickListener(e->{
                //edit functionality
                Dialog dialog = new Dialog();
                dialog.add(new Text("This is edit box"));
            });
            return editButton;
        }).setHeader("Actions");
        userGrid.addComponentColumn(user -> {

            Dialog dialog = new Dialog();
            dialog.setHeight("30");
            dialog.setWidth("40");
            dialog.setHeaderTitle("Do you really want to delete " + user.getUsername() + "?");
            Button deleteButton = new Button("Delete", (e) -> dialog.open());
            deleteButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY, ButtonVariant.LUMO_ERROR);
            deleteButton.getStyle().set("margin-right", "auto");

            Button confirmButton = new Button("Confirm", (e)->{
                System.out.println("in view: "+ user);
                userController.deleteUser(user);
                Notification.show("Deleted " + user.getUsername() +" successfully");
                dialog.close();
            });
            confirmButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY, ButtonVariant.LUMO_ERROR);
            confirmButton.getStyle().set("margin-right", "auto");
            dialog.getFooter().add(confirmButton);

            Button cancelButton = new Button("Cancel", (e) -> dialog.close());
            cancelButton.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
            dialog.getFooter().add(cancelButton);

            return deleteButton;
        }).setHeader("Actions");

        userGrid.setDataProvider(new ListDataProvider<>(userController.getAllUsers()));
        add(userGrid);
    }

}
