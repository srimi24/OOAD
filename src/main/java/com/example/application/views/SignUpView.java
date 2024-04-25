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
import com.example.application.views.MainLayout;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.dependency.Uses;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;



@Route(value = "sign-up")
@Uses(Icon.class)
public class SignUpView extends Composite<VerticalLayout> {

    private final UserController userController;

    public SignUpView(UserController userController) {
        this.userController = userController;

        H2 h2 = new H2();
        TextField nameField = new TextField();
        TextArea addressField = new TextArea();

        EmailField emailField = new EmailField();
        TextField usernameField = new TextField();
        PasswordField password1Field = new PasswordField();
        PasswordField password2Field = new PasswordField();

        getContent().setWidth("100%");
        getContent().getStyle().set("flex-grow", "1");
        h2.setText("Please enter your details");
        h2.setWidth("max-content");
        nameField.setLabel("Name");
        nameField.setWidth("min-content");
        addressField.setLabel("Address");
        addressField.setWidth("100%");

        emailField.setLabel("Email");
        emailField.setWidth("min-content");
        usernameField.setLabel("Username");
        usernameField.setWidth("min-content");
        password1Field.setLabel("Password");
        password1Field.setWidth("min-content");
        password2Field.setLabel("Confirm password");
        password2Field.setWidth("min-content");

        getContent().add(h2);
        getContent().add(nameField);
        getContent().add(addressField);

        getContent().add(emailField);
        getContent().add(usernameField);
        getContent().add(password1Field);
        getContent().add(password2Field);



        VerticalLayout layout = getContent();
        layout.setWidth("100%");
        layout.getStyle().set("flex-grow", "1");
        layout.setSizeFull();

        //loginForm.setWidth("100%");


        Button buttonPrimary = new Button();

        buttonPrimary.addClickListener(event -> {
            String name = nameField.getValue();
            String address = addressField.getValue();
            String email = emailField.getValue();
            String username = usernameField.getValue();
            String password1 = password1Field.getValue();
            String password2 = password2Field.getValue();

            if(!password1.equals(password2))
            {
                Notification.show("Password do not match !!!");
            }
            else
            {
                boolean isRegisterSuccessful = userController.register(name,address,email,username,password1);
                if(isRegisterSuccessful)
                {
                    Notification.show("Registration successful");
                    UI.getCurrent().navigate("login");
                }
                else
                {
                    Notification.show("Registration unsuccessful");
                }
            }

        });
        getContent().setWidth("100%");
        getContent().getStyle().set("flex-grow", "1");
        buttonPrimary.setText("Register");
        buttonPrimary.setWidth("min-content");
        buttonPrimary.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        getContent().add(buttonPrimary);
    }
}