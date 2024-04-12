package com.example.application.views.movie;

import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;

@Route("user")
public class UserView extends VerticalLayout {
    public UserView(){
        add(new H1("Welcome User"));
        add(new H2("What do u want to do?"));

        RouterLink BookPackageLink = new RouterLink("Book Package", BookPackageView.class);
        add(BookPackageLink);
    }
}
