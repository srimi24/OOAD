package com.example.application.views.movie;

import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("book-train")
public class BookTrainView extends VerticalLayout {
    public BookTrainView(){
        add(new H1("Book Train"));
    }
}