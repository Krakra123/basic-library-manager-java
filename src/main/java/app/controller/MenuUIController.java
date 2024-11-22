package app.controller;

import java.io.IOException;

import app.managers.BookCollectionHandler.GroupByType;
import app.managers.MenuManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
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

    @FXML
    public ComboBox<String> groupBy;

    @FXML
    public ComboBox<String> sortBy;

    private GroupByType groupByType = GroupByType.NONE;

    @FXML
    public void initialize() {
        groupBy.getItems().addAll("None", "Title", "Author");
        groupBy.setValue("None");
        groupBy.setOnAction(event -> {
            GroupByType type = GroupByType.NONE;
            switch (groupBy.getValue()) {
                case "None" -> { type = GroupByType.NONE; }
                case "Title" -> { type = GroupByType.TITLE; }
                case "Author" -> { type = GroupByType.AUTHOR; }
            }
            setGroupBy(type);
        });
    }

    public void lockSearch() {
        searchText.setDisable(true);
        searchButton.setDisable(true);
    }
    public void unlockSearch() {
        searchText.setDisable(false);
        searchButton.setDisable(false);
    }

    public void search(ActionEvent event) throws IOException {
        manager.search(searchText.getText(), groupByType); // FIXME
    }

    public void setGroupBy(GroupByType type) {
        groupByType = type;
    }
}
