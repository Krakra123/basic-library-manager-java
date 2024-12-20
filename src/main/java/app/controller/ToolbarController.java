package app.controller;

import java.io.IOException;

import app.managers.MainDisplayManager;
import javafx.event.ActionEvent;

@SuppressWarnings({"exports"})
public class ToolbarController {

    private MainDisplayManager manager;

    public void setManager(MainDisplayManager manager) {
        this.manager = manager;
    }

    public void openHome(ActionEvent event) throws IOException {
        manager.loadMainMenu();
    }

    public void openLibrary(ActionEvent event) throws IOException {
        manager.loadUserLibrary();
    }

    public void openDashboards(ActionEvent event) throws IOException {
        manager.loadDashboard();
    }

    public void openSettings(ActionEvent event) throws IOException {

    }

    public void openAccount(ActionEvent event) throws IOException {
    	manager.loadAccountTab();
    }
}
