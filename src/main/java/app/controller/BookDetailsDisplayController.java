package app.controller;

import java.io.File;
import java.io.IOException;

import app.data.Book;
import app.managers.AppManager;
import app.managers.BookCollectionHandler;
import app.util.AccountsManager;
import app.util.BookAPI;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

@SuppressWarnings("exports")
public class BookDetailsDisplayController {

    private BookCollectionHandler manager;
    public void setManager(BookCollectionHandler manager) {
        this.manager = manager;
    }
    
    private static final String NO_COVER_DIR = "src/main/resources/pic/no-cover.png";

    @FXML
    public ImageView image;

    @FXML
    public Button saveButton;

    @FXML
    public Button editButton;

    @FXML
    public Button unsaveButton;

    @FXML
    public Button readButton;

    @FXML 
    public Label title;

    @FXML 
    public Label authors;

    @FXML 
    public Label cat;

    @FXML 
    public Label publisher;

    @FXML 
    public Label page;

    @FXML 
    public Label description;

    private boolean editable;

    public Book data;

    public void save(ActionEvent event) throws IOException {
        BookAPI.saveBook(data.id, data);
        manager.raiseSaveCallback();
    }

    public void unsave(ActionEvent event) throws IOException {
        manager.raiseUnSaveCallback();
    }

    public void edit(ActionEvent event) throws IOException {
        BookAPI.saveBook(data.id, data);
        manager.raiseEditCallback();
    }

    public void read(ActionEvent event) throws IOException {
        manager.raiseOpenCallback();
    }

    public void reset(boolean editable) {
        this.editable = editable;

        image.setVisible(false);
        image.setDisable(true);
        image.setFitHeight(100);

        saveButton.setVisible(false);
        saveButton.setDisable(true);
        unsaveButton.setVisible(false);
        unsaveButton.setDisable(true);
        editButton.setVisible(false);
        editButton.setDisable(true);
        readButton.setVisible(false);
        readButton.setDisable(true);

        loadNoCover();
        title.setText("");
        authors.setText("<< Select a book to view >>");
        publisher.setText("");
        page.setText("");
        description.setText("");
    }

    public void update(Book book) {
        data = book;

        image.setVisible(true);
        image.setDisable(false);
        image.setFitHeight(367.64);

        saveButton.setVisible(true);
        saveButton.setDisable(false);
        // unsaveButton.setVisible(false);
        // unsaveButton.setDisable(true);
        if (editable) {
            editButton.setVisible(true);
            editButton.setDisable(false);
        }
        else {
            editButton.setVisible(false);
            editButton.setDisable(true);
        }
        readButton.setVisible(true);
        readButton.setDisable(false);

        if (AccountsManager.hasBook(AppManager.getInstance().getUserManager().getCurrentUser(), book)) {
            unsaveButtonGogo();
        }
        else {
            saveButtonGogo();
        }

        if (book.accessInfo.webReaderLink.isEmpty()) {
            readButton.setVisible(false);
            readButton.setDisable(true);
        }

        title.setText(book.volumeInfo.title);
        authors.setText(book.volumeInfo.authors.toString().substring(1, book.volumeInfo.authors.toString().length() - 1));
        cat.setText("Categories: " + book.volumeInfo.categories.toString().substring(1, book.volumeInfo.categories.toString().length() - 1));
        publisher.setText("Publisher: " + book.volumeInfo.publisher + " | " + book.volumeInfo.publishedDate);
        page.setText("Pages: " + book.volumeInfo.pageCount);
        description.setText("Description: " + book.volumeInfo.description);

        image.setImage(null);
        updateImage(book.volumeInfo.imageLinks.thumbnail);
    }

    private void saveButtonGogo() {
        saveButton.setVisible(true);
        saveButton.setDisable(false);
        unsaveButton.setVisible(false);
        unsaveButton.setDisable(true);
    }
    private void unsaveButtonGogo() {
        saveButton.setVisible(false);
        saveButton.setDisable(true);
        unsaveButton.setVisible(true);
        unsaveButton.setDisable(false);
    }

    private void updateImage(String url) {
        if (!url.isEmpty()) {
            Image loadImage = new Image(url, true);
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
        Image loadImage = new Image(file.toURI().toString(), true);
        image.setImage(loadImage);
    }
}
