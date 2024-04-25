package com.example.application.views;

import com.example.application.controller.UserController;
import com.example.application.views.MainLayout;
import com.example.application.views.Session;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.dependency.Uses;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;



@PageTitle("Login View")
@Route(value = "login", layout = MainLayout.class)
@RouteAlias(value = "" , layout = MainLayout.class)
@Uses(Icon.class)
public class LoginView extends Composite<VerticalLayout> {

    private final UserController userController;

    public LoginView(UserController userController) {
        this.userController = userController;

        LoginForm loginForm = new LoginForm();

        loginForm.addLoginListener(e->{
            String username = e.getUsername();
            String password = e.getPassword();

            String User = userController.checkUser(username, password);
            if (User==null)
            {
                Notification.show("User not found");
            }
            else
            {
                Session.login(username);
                if(User.equals("user"))
                    UI.getCurrent().navigate("user");
                else if(User.equals("admin"))
                    UI.getCurrent().navigate("admin");
            }
        });


        VerticalLayout layout = getContent();
        layout.setWidth("100%");
        layout.getStyle().set("flex-grow", "1");
        layout.setSizeFull();
        layout.setAlignItems(FlexComponent.Alignment.CENTER);
        layout.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
        //loginForm.setWidth("100%");
        getContent().add(loginForm);

        Button buttonPrimary = new Button();
        buttonPrimary.addClickListener(event -> {
            UI.getCurrent().navigate("sign-up");
        });
        getContent().setWidth("100%");
        getContent().getStyle().set("flex-grow", "1");
        buttonPrimary.setText("Register as new User?");
        buttonPrimary.setWidth("min-content");
        buttonPrimary.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        getContent().add(buttonPrimary);
    }
}