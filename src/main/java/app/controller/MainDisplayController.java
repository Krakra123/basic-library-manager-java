package app.controller;

import app.managers.MainDisplayManager;
import javafx.fxml.FXML;
import javafx.scene.layout.StackPane;

@SuppressWarnings("exports")
public class MainDisplayController {
    
    private MainDisplayManager manager;

    public void setManager(MainDisplayManager manager) {
        this.manager = manager;
    }

    @FXML
    public StackPane contentPane;

    @FXML
    private void handleHomeButton() {
        manager.loadMainMenu();
    }

    @FXML
    private void handleLibraryButton() {

    }

    @FXML
    private void handleDashboardsButton() {

    }

    @FXML
    private void handleSettingButton() {
        
    }

    @FXML
    private void handleInfoButton() {

    }
}
