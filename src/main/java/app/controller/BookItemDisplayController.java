package app.controller;

import app.data.Book;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class BookItemDisplayController {
    
    @FXML
    private Label title;

    @FXML
    private Label author;

    private Book data;

    public void update(Book book) {
        data = book;
        
        title.setText(data.title);
        author.setText(data.author);
    }
}
