package app.controller;

import java.io.IOException;

import app.managers.MainDisplayManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.StackPane;

@SuppressWarnings({"exports"})
public class MainDisplayController {
    
    private MainDisplayManager manager;

    public void setManager(MainDisplayManager manager) {
        this.manager = manager;
    }

    @FXML
    public StackPane contentPane;

    public void openHome(ActionEvent event) throws IOException {
        manager.loadMainMenu();
    }

    public void openLibrary(ActionEvent event) throws IOException {
        manager.loadUserLibrary();
    }

    public void openDashboards(ActionEvent event) throws IOException {

    }

    public void openSetting(ActionEvent event) throws IOException {

    }

    public void openInfo(ActionEvent event) throws IOException {

    }
}
