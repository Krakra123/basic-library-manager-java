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
        
        title.setText(data.volumeInfo.title);
        String authorS = "";
        for (String a : data.volumeInfo.authors) authorS += a;
        author.setText(authorS);
    }
}
