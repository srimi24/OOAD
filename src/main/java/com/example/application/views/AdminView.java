package com.example.application.views.movie;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;

@Route("admin")
public class AdminView extends VerticalLayout {
    public AdminView(){
        add(new H1("Ahoy ADMIN"));
        add(new H2("What do u want to do?"));

        RouterLink manageUserLink = new RouterLink("Manage Users", ManageUsersView.class);
        add(manageUserLink);

    }
}
