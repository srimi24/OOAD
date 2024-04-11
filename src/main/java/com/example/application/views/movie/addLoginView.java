package com.example.application.views.movie;

import com.example.application.controller.MovieController;
import com.example.application.views.MainLayout;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.dependency.Uses;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("Login View")
@Route(value = "login", layout = MainLayout.class)
@Uses(Icon.class)

public class addLoginView extends Composite<VerticalLayout> {

    private final MovieController movieController;

//    public addLoginView(MovieController movieController, MovieController movieController1) {
//        this.movieController = movieController1;
//    }
    public addLoginView(MovieController movieController) {
        this.movieController = movieController;

        LoginForm loginForm = new LoginForm();

        loginForm.addLoginListener(e->{
            String username = e.getUsername();
            String password = e.getPassword();

            boolean userExists = movieController.checkUser(username, password);
            if (userExists) {
                Notification.show("Login successful");
            } else {
                Notification.show("User not found");
            }
        });

        getContent().setWidth("100%");
        getContent().getStyle().set("flex-grow", "1");
        //loginForm.setWidth("100%");
        getContent().add(loginForm);
    }
}