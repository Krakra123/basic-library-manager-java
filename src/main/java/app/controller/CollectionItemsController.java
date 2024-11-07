package app.controller;

import app.data.Book;
import app.data.BookCollection;
import app.util.Utilities;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;

public class CollectionItemsController {

    @FXML 
    private ListView<VBox> listView;

    private ObservableList<VBox> bookViews = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        listView.setItems(bookViews);
    }

    public void update(BookCollection collection) { // FIXME Remove hardcode
        for (Book book : collection.get()) {
            Utilities.FXMLData itemBookFXML = Utilities.loadFXML("ItemBookList");
            if (itemBookFXML.root instanceof VBox root) {
                bookViews.add(root);
            } else {
                throw new RuntimeException("ItemBookDisplay's root must be a VBox");
            }

            if (itemBookFXML.controller instanceof ItemBookListController controller) {
                controller.load(book);
            } else {
                throw new RuntimeException("ItemBookDisplay has wrong controller");
            }
        }
    }
}
