package app.controller;

import app.data.Book;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

@SuppressWarnings("exports")
public class BookDetailsDisplayController {
    
    @FXML
    public ImageView image;

    @FXML 
    public Label title;

    @FXML 
    public Label authors;

    @FXML 
    public Label publisher;

    @FXML 
    public Label page;

    @FXML 
    public Label description;

    @FXML 
    public Label read;

    @FXML
    public Label date;

    public void update(Book book) {
        title.setText(book.volumeInfo.title);
        authors.setText(book.volumeInfo.authors.toString().substring(1, book.volumeInfo.authors.toString().length() - 1));
        publisher.setText("Pulisher: " + book.volumeInfo.publisher + " | " + book.volumeInfo.publishedDate);
        page.setText("Pages: " + book.volumeInfo.pageCount);
        description.setText("Description: " + book.volumeInfo.description);
        read.setText("Read: " + book.accessInfo.webReaderLink);

    }
}
