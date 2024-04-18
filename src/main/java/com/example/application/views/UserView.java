package com.example.application.views;

import com.example.application.views.BookHotelView;
import com.example.application.views.BookingView;
import com.example.application.views.ReviewBookingView;
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
        RouterLink bookTrainLink = new RouterLink("Book Train", BookTrainView.class);

        add(bookFlightLink);
        add(bookTrainLink);

        add(new H3("Book Accomodation"));

        // Creating router links for booking different types of travel
        RouterLink bookHotelLink = new RouterLink("Book Hotel", BookHotelView.class);
        RouterLink bookVillaLink = new RouterLink("Book Villa", BookVillaView.class);

        add(bookHotelLink);
        add(bookVillaLink);

        add(new H3("Book Package"));

        RouterLink BookPackageLink = new RouterLink("Book Package", BookPackageView.class);
        add(BookPackageLink);

        add(new H3("View your bookings"));
        RouterLink BookingViewLink = new RouterLink("View Your Bookings", BookingView.class);
        add(BookingViewLink);

        add(new H3("Give review on your bookings"));
        RouterLink ReviewBookingViewLink = new RouterLink("Add your Review", ReviewBookingView.class);
        add(ReviewBookingViewLink);
    }
}
