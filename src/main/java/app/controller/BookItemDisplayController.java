package app.controller;

import java.io.File;
import java.io.IOException;

import app.data.Book;
import app.managers.BookCollectionHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

@SuppressWarnings("exports")
public class BookItemDisplayController {
    
    private static final String NO_COVER_DIR = "src/main/resources/pic/no-cover.png";

    private BookCollectionHandler manager;
    public void setManager(BookCollectionHandler manager) {
        this.manager = manager;
    }

    @FXML
    public ImageView image;

    @FXML
    private Label title;

    @FXML
    private Label author;

    private Book data;

    public void update(Book book) {
        data = book;

        image.setVisible(true);
        image.setDisable(false);
        image.setFitHeight(367.64);

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
        author.setText(book.volumeInfo.authors.toString().substring(1, book.volumeInfo.authors.toString().length() - 1));
    }

    private void loadNoCover() {
        File file = new File(NO_COVER_DIR);
        Image loadImage = new Image(file.toURI().toString());
        image.setImage(loadImage);
    }

    public void select(ActionEvent event) throws IOException {
        manager.viewDetails(data);
    }
}
