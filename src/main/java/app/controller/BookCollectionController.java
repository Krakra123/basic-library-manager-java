package app.controller;

import app.App;
import app.data.Book;
import app.library.BookCollection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;

public class BookCollectionController {

    @FXML 
    private ListView<VBox> listView;

    private ObservableList<VBox> bookViews = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        listView.setItems(bookViews);
    }

    public void update(BookCollection collection) {
        for (Book book : collection.get()) {
            try {
                FXMLLoader loader = new FXMLLoader(App.class.getResource("/fxml/BookDisplayOnCollection.fxml"));
                VBox bookView = loader.load();
                BookDisplayOnCollectionController controller = loader.getController();
                controller.load(book);
                bookViews.add(bookView);
            } catch (Exception e) {
                e.getStackTrace();
            }
        }
    }
}
