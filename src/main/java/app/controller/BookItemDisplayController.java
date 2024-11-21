package app.controller;

import java.io.IOException;

import app.data.Book;
import app.managers.BookCollectionHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

@SuppressWarnings("exports")
public class BookItemDisplayController {
    
    private BookCollectionHandler manager;
    public void setManager(BookCollectionHandler manager) {
        this.manager = manager;
    }

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

    public void select(ActionEvent event) throws IOException {
        manager.viewDetails(data);
    }
}
