package com.example.application.views.movie;

import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("book-cab")
public class BookCabView extends VerticalLayout {
    public BookCabView(){
        add(new H1("Book Cab"));
    }
}