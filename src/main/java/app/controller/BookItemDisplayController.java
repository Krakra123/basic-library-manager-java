package app.controller;

import java.io.File;
import java.io.IOException;

import app.data.Book;
import app.managers.BookCollectionHandler;
import javafx.concurrent.Task;
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

    private Task<ImageView> changeImageTask;

    public void update(Book book) {
        if (changeImageTask != null) {
            changeImageTask.cancel();
        }

        data = book;

        image.setVisible(true);
        image.setDisable(false);
        image.setFitHeight(367.64);

        title.setText(book.volumeInfo.title);
        author.setText(book.volumeInfo.authors.toString().substring(1, book.volumeInfo.authors.toString().length() - 1));

        changeImageTask = new Task<ImageView>() { 
            @Override
            protected ImageView call() throws Exception {
                image.setImage(null);
                updateImage(book.volumeInfo.imageLinks.thumbnail);
                return image;
            }
        };

        Thread thread = new Thread(changeImageTask);
        thread.start();
    }

    private void updateImage(String url) {
        if (!url.isEmpty()) {
            Image loadImage = new Image(url);
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
