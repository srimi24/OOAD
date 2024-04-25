package com.example.application.views;

import com.example.application.controller.UserController;
import com.example.application.views.LoginView;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.html.Footer;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Header;
import com.vaadin.flow.component.orderedlayout.Scroller;
import com.vaadin.flow.component.sidenav.SideNav;
import com.vaadin.flow.component.sidenav.SideNavItem;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.theme.lumo.LumoUtility;
import org.vaadin.lineawesome.LineAwesomeIcon;

import java.util.Objects;

/**
 * The main view is a top-level placeholder for other views.
 */
public class MainLayout extends AppLayout {

    private H2 viewTitle;

    UserController userController;

    public MainLayout(UserController userController) {
        this.userController = userController;
        setPrimarySection(Section.DRAWER);
        addDrawerContent();
        addHeaderContent();
    }

    private void addHeaderContent() {
        DrawerToggle toggle = new DrawerToggle();
        toggle.setAriaLabel("Menu toggle");

        viewTitle = new H2();
        viewTitle.addClassNames(LumoUtility.FontSize.LARGE, LumoUtility.Margin.NONE);

        addToNavbar(true, toggle, viewTitle);
    }

    private void addDrawerContent() {
        H1 appName = new H1("My App");
        appName.addClassNames(LumoUtility.FontSize.LARGE, LumoUtility.Margin.NONE);
        Header header = new Header(appName);

        Scroller scroller = new Scroller(createNavigation());

        addToDrawer(header, scroller, createFooter());
    }

    private SideNav createNavigation() {
        SideNav nav = new SideNav();

        if(Session.getUsername()==null) {
            nav.addItem(new SideNavItem("Login", LoginView.class, LineAwesomeIcon.LONG_ARROW_ALT_RIGHT_SOLID.create()));
        }else{
            if(Objects.equals(userController.getRole(Session.getUsername()), "admin")){
                //admin routes
                nav.addItem(new SideNavItem("Dashboard", AdminView.class, LineAwesomeIcon.LONG_ARROW_ALT_RIGHT_SOLID.create()));
                nav.addItem(new SideNavItem("Manage Users", ManageUsersView.class, LineAwesomeIcon.LONG_ARROW_ALT_RIGHT_SOLID.create()));
                nav.addItem(new SideNavItem("Manage Services", ManageServiceView.class, LineAwesomeIcon.LONG_ARROW_ALT_RIGHT_SOLID.create()));
                nav.addItem(new SideNavItem("Manage Bookings", ManageBookingView.class, LineAwesomeIcon.LONG_ARROW_ALT_RIGHT_SOLID.create()));

            }else{
                //user routes
                nav.addItem(new SideNavItem("Book Flights", BookFlightView.class, LineAwesomeIcon.LONG_ARROW_ALT_RIGHT_SOLID.create()));
                nav.addItem(new SideNavItem("Book Hotels", BookHotelView.class, LineAwesomeIcon.LONG_ARROW_ALT_RIGHT_SOLID.create()));
                nav.addItem(new SideNavItem("Book Villa", BookVillaView.class, LineAwesomeIcon.LONG_ARROW_ALT_RIGHT_SOLID.create()));
                nav.addItem(new SideNavItem("Book Trains", BookTrainView.class, LineAwesomeIcon.LONG_ARROW_ALT_RIGHT_SOLID.create()));
                nav.addItem(new SideNavItem("Book Packages", BookPackageView.class, LineAwesomeIcon.LONG_ARROW_ALT_RIGHT_SOLID.create()));
                nav.addItem(new SideNavItem("View Bookings", BookingView.class, LineAwesomeIcon.LONG_ARROW_ALT_RIGHT_SOLID.create()));
                nav.addItem(new SideNavItem("Review Booking", ReviewBookingView.class, LineAwesomeIcon.LONG_ARROW_ALT_RIGHT_SOLID.create()));
            }
            return nav;
        }
        return nav;
    }

    private Footer createFooter() {

        return new Footer();
    }

    @Override
    protected void afterNavigation() {
        super.afterNavigation();
        viewTitle.setText(getCurrentPageTitle());
    }

    private String getCurrentPageTitle() {
        PageTitle title = getContent().getClass().getAnnotation(PageTitle.class);
        return title == null ? "" : title.value();
    }
}
