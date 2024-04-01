package com.example.application.views.movie;


import com.example.application.controller.MovieController;
import com.example.application.models.Movie;
import com.example.application.views.MainLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Random;

@PageTitle("Delete Movie")
@Route(value = "deleteMovies", layout = MainLayout.class)
@RouteAlias(value = "delete" , layout = MainLayout.class)
public class deleteMovieView extends VerticalLayout {

    private final MovieController movieController;

    public deleteMovieView(MovieController movieController, MovieController movieController1){

        this.movieController = movieController1;
    }

    private TextField titleField;
    private IntegerField yearField;
    private Button addButton;

    @Autowired
    public deleteMovieView(MovieController movieController) {
        this.movieController = movieController;
        // Form for movie details
        FormLayout formLayout = new FormLayout();
        titleField = new TextField("Title");
        yearField= new IntegerField("Year");

        formLayout.setResponsiveSteps(
                new FormLayout.ResponsiveStep("0", 1),
                new FormLayout.ResponsiveStep("320px", 2),
                new FormLayout.ResponsiveStep("500px", 3));
        formLayout.add(titleField, yearField);

        addButton = new Button("Delete Movie");
        addButton.addClickListener(click -> {
            String title = titleField.getValue();
            Integer year = yearField.getValue();
            if (title.isEmpty()) {
                Notification.show("Please fill in all fields.");
                return;
            }

            boolean isDeleted = movieController.deleteMovie(title,year);
            if(isDeleted)
            {
                Notification.show("Movie deleted successfully");
                titleField.clear();
                yearField.clear();
            }
            else
            {
                Notification.show("Movie does not exist");
            }
        });

        add(formLayout, addButton);
    }
}
