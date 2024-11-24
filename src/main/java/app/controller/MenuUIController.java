package app.controller;

import java.io.IOException;

import app.managers.BookCollectionHandler.GroupByType;
import app.managers.BookCollectionHandler.SortByType;
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
    private SortByType sortByType = SortByType.ASCENDING;

    @SuppressWarnings("unused")
    @FXML
    public void initialize() {
        groupBy.getItems().addAll("None", "Title", "Category", "Author");
        groupBy.setValue("None");
        groupBy.setOnAction(event -> {
            GroupByType type = GroupByType.NONE;
            switch (groupBy.getValue()) {
                case "None" -> { type = GroupByType.NONE; }
                case "Title" -> { type = GroupByType.TITLE; }
                case "Author" -> { type = GroupByType.AUTHOR; }
                case "Category" -> { type = GroupByType.CATEGORY; }
            }
            setGroupBy(type);
        });

        sortBy.getItems().addAll("Ascending", "Descending");
        sortBy.setValue("Ascending");
        sortBy.setOnAction(event -> {
            SortByType type = SortByType.ASCENDING;
            switch (sortBy.getValue()) {
                case "Ascending" -> { type = SortByType.ASCENDING; }
                case "Descending" -> { type = SortByType.DESCENDING; }
            }
            setSortBy(type);
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

    public void search() {
        manager.search(searchText.getText(), groupByType, sortByType);
    }
    public void search(ActionEvent event) throws IOException {
        manager.search(searchText.getText(), groupByType, sortByType);
    }

    public void setGroupBy(GroupByType type) {
        groupByType = type;
    }
    public void setSortBy(SortByType type) {
        sortByType = type;
    }
}
