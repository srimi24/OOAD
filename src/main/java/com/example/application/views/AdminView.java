package com.example.application.views;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;

@Route(value = "admin", layout = MainLayout.class)
public class AdminView extends VerticalLayout {
    public AdminView(){
        add(new H1("Ahoy ADMIN"));
        add(new H2("What do u want to do?"));

        Div bigbox1 = new Div();
        bigbox1.addClassName("big-box");
        RouterLink manageUserLink = new RouterLink("Manage Users", ManageUsersView.class);
        bigbox1.add(manageUserLink);
        add(bigbox1);

        Div bigbox2 = new Div();
        bigbox2.addClassName("big-box");
        RouterLink manageServicesLink = new RouterLink("Manage Services", ManageServiceView.class);
        bigbox2.add(manageServicesLink);
        add(bigbox2);

        Div bigbox3 = new Div();
        bigbox3.addClassName("big-box");
        RouterLink manageBookingLink = new RouterLink("Manage Bookings", ManageBookingView.class);
        bigbox3.add(manageBookingLink);
        add(bigbox3);
    }
}
