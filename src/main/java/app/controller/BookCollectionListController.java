package app.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.layout.StackPane;

@SuppressWarnings("exports")
public class BookCollectionListController {

    @FXML 
    private ListView<StackPane> listView;

    private ObservableList<StackPane> bookViews = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        listView.setItems(bookViews);
    }

    public void addViews(StackPane pane) {
        bookViews.add(pane);
    }

    public void clearViews() {
        bookViews.clear();
    }
}
