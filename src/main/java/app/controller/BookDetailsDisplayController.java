package app.controller;

import java.io.File;

import app.data.Book;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

@SuppressWarnings("exports")
public class BookDetailsDisplayController {
    
    private static final String NO_COVER_DIR = "src/main/resources/pic/no-cover.png";

    @FXML
    public ImageView image;

    @FXML
    public Button saveButton;

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

    public void reset() {
        image.setVisible(false);
        image.setDisable(true);
        image.setFitHeight(100);

        saveButton.setVisible(false);
        saveButton.setDisable(true);

        loadNoCover();
        title.setText("");
        authors.setText("<< Select a book to view >>");
        publisher.setText("");
        page.setText("");
        description.setText("");
        read.setText("");
    }

    public void update(Book book) {
        image.setVisible(true);
        image.setDisable(false);
        image.setFitHeight(367.64);

        saveButton.setVisible(true);
        saveButton.setDisable(false);

        if (!book.volumeInfo.imageLinks.thumbnail.isEmpty()) {
            Image loadImage = new Image(book.volumeInfo.imageLinks.thumbnail);
            if (!loadImage.isError()) {
                image.setImage(loadImage);
            }
            else {
                loadNoCover();
            }
        } 
        else {
            loadNoCover();
        }
        title.setText(book.volumeInfo.title);
        authors.setText(book.volumeInfo.authors.toString().substring(1, book.volumeInfo.authors.toString().length() - 1));
        publisher.setText("Pulisher: " + book.volumeInfo.publisher + " | " + book.volumeInfo.publishedDate);
        page.setText("Pages: " + book.volumeInfo.pageCount);
        description.setText("Description: " + book.volumeInfo.description);
        read.setText("Read: " + book.accessInfo.webReaderLink);
    }

    private void loadNoCover() {
        File file = new File(NO_COVER_DIR);
        Image loadImage = new Image(file.toURI().toString());
        image.setImage(loadImage);
    }
}
