package app.controller;

import java.io.IOException;

import app.managers.BookCollectionHandler.GroupByType;
import app.managers.MenuManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

@SuppressWarnings({"exports"})
public class MenuUIController {

    private MenuManager manager;
    public void setManager(MenuManager manager) {
        this.manager = manager;
    }

    @FXML
    public TextField searchText;

    @FXML
    public Button searchButton;

    @FXML 
    public AnchorPane contentPane;

    public void lockSearch() {
        searchText.setDisable(true);
        searchButton.setDisable(true);
    }
    public void unlockSearch() {
        searchText.setDisable(false);
        searchButton.setDisable(false);
    }

    public void search(ActionEvent event) throws IOException {
        manager.search(searchText.getText(), GroupByType.TITLE);
    }
}
