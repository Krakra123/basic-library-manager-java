package app.controller;

import java.io.IOException;

import app.managers.MainDisplayManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

@SuppressWarnings({"exports"})
public class MainDisplayController {
    
    private MainDisplayManager manager;

    public void setManager(MainDisplayManager manager) {
        this.manager = manager;
    }

    @FXML
    public AnchorPane toolbarPane;

    @FXML
    public AnchorPane contentPane;

    public void handleMenuSettings(ActionEvent event) throws IOException {

    }
    public void handleMenuClose(ActionEvent event) throws IOException {

    }

    public void handleMenuAddBook(ActionEvent event) throws IOException {

    }
    public void handleMenuAddMember(ActionEvent event) throws IOException {

    }

    public void handleAboutMenu(ActionEvent event) throws IOException {

    }
}
