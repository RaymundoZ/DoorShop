package com.raymundo.doorshop.ui;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.Unit;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.page.Page;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteConfiguration;
import com.vaadin.flow.router.Router;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.server.AbstractStreamResource;
import com.vaadin.flow.server.VaadinSession;

@Route(value = "")
public class MainLayout extends AppLayout {

    public MainLayout() {
        Button table = new Button("Table");
        table.addClickListener(event -> {
            UI.getCurrent().navigate(TableLayout.class);
        });
        HorizontalLayout layout = new HorizontalLayout(table);
        layout.setSizeFull();
        layout.setAlignItems(FlexComponent.Alignment.END);
        Image image = new Image("images/image.jpg", "");
        image.setWidth(10f, Unit.PERCENTAGE);
        image.setHeight(10f, Unit.PERCENTAGE);
        Icon icon = new Icon("vaadin", "chart-3d");
        EmailField email = new EmailField("Email", "example@gmail.com");
        PasswordField pass = new PasswordField("Password");
        TextField nickname = new TextField("Nickname", "example");
        Button save = new Button("Save");
        save.addClickListener(new ComponentEventListener<ClickEvent<Button>>() {
            @Override
            public void onComponentEvent(ClickEvent<Button> event) {
                User user = new User(email.getValue(), pass.getValue(), nickname.getValue());
                TableLayout layout1 = UI.getCurrent().navigate(TableLayout.class).get();
                layout1.addUser(user);
            }
        });
        VerticalLayout verticalLayout = new VerticalLayout(icon, image, email, pass, nickname, save);
        verticalLayout.setAlignItems(FlexComponent.Alignment.CENTER);
        verticalLayout.setSizeFull();
        setContent(verticalLayout);
        addToNavbar(layout);
    }

}


