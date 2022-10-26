package com.raymundo.doorshop.ui;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

import java.util.ArrayList;
import java.util.List;

@Route(value = "tables")
public class TableLayout extends VerticalLayout {

    private Grid<User> table;
    private List<User> users;

    public TableLayout() {
        table = new Grid<>();
        users = new ArrayList<>();
        table.addColumn(User::getEmail).setHeader("Email");
        table.addColumn(User::getPassword).setHeader("Password");
        table.addColumn(User::getNickname).setHeader("Nickname");
        table.setItems(users);
        Icon icon = new Icon("lumo", "cross");
        icon.addClickListener((event) -> {
            UI.getCurrent().navigate(MainLayout.class);
        });
        HorizontalLayout layout = new HorizontalLayout(icon);
        layout.setWidthFull();
        add(layout);
        add(table);
        setSizeFull();
        setAlignItems(Alignment.CENTER);
    }

    public void addUser(User user) {
        users.add(user);
    }

}
