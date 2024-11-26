package app.controller;

import java.util.Arrays;

import app.data.Book;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

@SuppressWarnings("exports")
public class SetBookDialogController extends ConfirmDialogController {
    
    @FXML
    public TextField titleField;

    @FXML
    public TextField authorField;

    @FXML
    public TextField catField;

    @FXML
    public TextArea desField;

    private Book data;

    public void setBook(Book book) {
        titleField.setText(book.volumeInfo.title);
        authorField.setText(book.volumeInfo.authors.toString().substring(1, book.volumeInfo.authors.toString().length() - 1));
        catField.setText(book.volumeInfo.categories.toString().substring(1, book.volumeInfo.categories.toString().length() - 1));
        desField.setText(book.volumeInfo.description);

        data = book;
    }

    public Book getBook() {
        data.volumeInfo.title = titleField.getText();
        data.volumeInfo.authors = Arrays.asList(authorField.getText().split(", "));
        data.volumeInfo.categories = Arrays.asList(catField.getText().split(", "));
        data.volumeInfo.description = desField.getText();

        return data;
    }
}
