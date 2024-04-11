package com.example.application.views.movie;


import com.example.application.controller.MovieController;
import com.example.application.models.Movie;
import com.example.application.views.MainLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import org.springframework.beans.factory.annotation.Autowired;

@PageTitle("Add Movies")
@Route(value = "addMovies", layout = MainLayout.class)
@RouteAlias(value = "add" , layout = MainLayout.class)
public class addMovieView extends VerticalLayout {

    private final MovieController movieController;

    public addMovieView(MovieController movieController, MovieController movieController1){

        this.movieController = movieController1;
    }

    private TextField titleField;
    private IntegerField yearField;
    private TextField directorField;
    private IntegerField ratingField;
    private TextField genreSelect;
    private Button addButton;

    @Autowired
    public addMovieView(MovieController movieController) {
        this.movieController = movieController;
        // Form for movie details
        FormLayout formLayout = new FormLayout();
        titleField = new TextField("Title");
        genreSelect = new TextField("Genre");
        yearField= new IntegerField("Year");
        directorField = new TextField("Director");
        ratingField = new IntegerField();
        ratingField.setLabel("Rating");
        ratingField.setHelperText("Max 5.0 rating");
        ratingField.setMin(0);
        ratingField.setMax(5);
        ratingField.setStepButtonsVisible(true);

        formLayout.setResponsiveSteps(
                new FormLayout.ResponsiveStep("0", 1),
                new FormLayout.ResponsiveStep("320px", 2),
                new FormLayout.ResponsiveStep("500px", 3));
        formLayout.add(titleField, genreSelect, yearField, directorField, ratingField);

        addButton = new Button("Add Movie");
        addButton.addClickListener(click -> {
            String title = titleField.getValue();
            String genre = genreSelect.getValue();
            Integer year = yearField.getValue();
            String director = directorField.getValue();
            Integer rating = ratingField.getValue();
            if (title.isEmpty() || genre == null) {
                Notification.show("Please fill in all fields.");
                return;
            }

            Movie movie = new Movie(title, year, genre,director, rating);
            movieController.addMovie(movie);
            Notification.show("Movie added: " + title + " (" + genre + ")");
            titleField.setValue("");
            genreSelect.setValue("");
        });

        add(formLayout, addButton);
    }
}
