package app.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;

public class BookCollectionController extends BaseController {

    @FXML 
    private ListView<VBox> listView;

    private ObservableList<VBox> bookViews = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        listView.setItems(bookViews);
    }

    public void addViews(VBox vbox) {
        bookViews.add(vbox);
    }

    public void clearViews() {
        bookViews.clear();
    }
}
