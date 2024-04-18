package com.example.application.views;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;

@Route(value = "manage-service", layout = MainLayout.class)
public class ManageServiceView extends VerticalLayout {
    public ManageServiceView(){
        add(new H1("Manage Service Here"));
    }
}
