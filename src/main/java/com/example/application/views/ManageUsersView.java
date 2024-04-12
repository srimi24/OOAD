package com.example.application.views.movie;

import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("manage-user")
public class ManageUsersView extends VerticalLayout {
    public ManageUsersView(){
        add(new H1("Manage Users"));
    }
}
