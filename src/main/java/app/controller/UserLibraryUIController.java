package app.controller;

import java.io.IOException;

import app.managers.BookCollectionHandler.GroupByType;
import app.managers.BookCollectionHandler.SortByType;
import app.managers.UserLibraryManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;

@SuppressWarnings("exports")
public class UserLibraryUIController {

    private UserLibraryManager manager;
    public void setManager(UserLibraryManager manager) {
        this.manager = manager;
    }
    
    @FXML
    public AnchorPane bookListPane;

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
    
    public void apply() {
        manager.apply(groupByType, sortByType);
    }
    public void apply(ActionEvent event) throws IOException {
        manager.apply(groupByType, sortByType);
    }

    public void setGroupBy(GroupByType type) {
        groupByType = type;
    }
    public void setSortBy(SortByType type) {
        sortByType = type;
    }
}
