package com.example.application.views.movie;

import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("admin")
public class AdminView extends VerticalLayout {
    public AdminView(){
        add(new H1("Ahoy ADMIN"));
    }
}
