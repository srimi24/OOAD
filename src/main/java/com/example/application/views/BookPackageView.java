package com.example.application.views;

import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route(value="book-package" ,layout = MainLayout.class)
public class BookPackageView extends VerticalLayout {
    public BookPackageView(){
        add(new H1("Book Package"));
    }
}

