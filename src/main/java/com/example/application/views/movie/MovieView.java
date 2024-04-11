package com.example.application.views.movie;

import com.example.application.controller.MovieController;
import com.example.application.models.Movie;
import com.example.application.views.MainLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;

import java.util.Timer;

@PageTitle("Movies")
@Route(value = "movies", layout = MainLayout.class)
@RouteAlias(value = "" , layout = MainLayout.class)
public class MovieView extends VerticalLayout {

    private final MovieController movieController;
    private Timer timer;
    private Grid<Movie> movieGrid;

    public MovieView(MovieController movieController) {
        this.movieController = movieController;


        add(new H2("All Movies"));

        movieGrid = new Grid<>(Movie.class, false);
        movieGrid.addColumn(Movie::getTitle, "Title").setAutoWidth(true);
        movieGrid.addColumn(Movie::getReleaseYear, "Year").setAutoWidth(true);
        movieGrid.addColumn(Movie::getGenre, "Genre").setAutoWidth(true);
        movieGrid.addColumn(Movie::getDirector, "Director").setAutoWidth(true);
        movieGrid.addColumn(Movie::getAverageRating, "Rating").setAutoWidth(true);


        movieGrid.setDataProvider(new ListDataProvider<>(movieController.getAllMovies()));
        add(movieGrid);
    }

}