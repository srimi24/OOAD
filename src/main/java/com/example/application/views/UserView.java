package com.example.application.views.movie;

import com.example.application.views.Session;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;

@Route("user")
public class UserView extends VerticalLayout {
    public UserView(){
        add(new H1("Welcome " + Session.getUsername()));
        add(new H2("What do u want to do?"));

        add(new H3("Book Travel"));

        // Creating router links for booking different types of travel
        RouterLink bookFlightLink = new RouterLink("Book Flight", BookFlightView.class);
        RouterLink bookBusLink = new RouterLink("Book Bus", BookBusView.class);
        RouterLink bookCabLink = new RouterLink("Book Cab", BookCabView.class);
        RouterLink bookTrainLink = new RouterLink("Book Train", BookTrainView.class);

        add(bookCabLink);
        add(bookBusLink);
        add(bookFlightLink);
        add(bookTrainLink);

        add(new H3("Book Accomodation"));

        // Creating router links for booking different types of travel
        RouterLink bookHotelLink = new RouterLink("Book Hotel", BookHotelView.class);
        RouterLink bookResortLink = new RouterLink("Book Resort", BookResortView.class);
        RouterLink bookHomestayLink = new RouterLink("Book Homestay", BookHomestayView.class);

        add(bookHotelLink);
        add(bookResortLink);
        add(bookHomestayLink);

        add(new H3("Book Package"));

        RouterLink BookPackageLink = new RouterLink("Book Package", BookPackageView.class);
        add(BookPackageLink);
    }
}
