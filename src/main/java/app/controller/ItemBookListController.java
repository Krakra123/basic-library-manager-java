package app.controller;

import app.data.Book;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class ItemBookListController {
    
    @FXML
    private Label name;

    @FXML
    private Label publisher;

    public void load(Book book) {
        name.setText(book.name);
        publisher.setText(book.publisher);
    }
}
