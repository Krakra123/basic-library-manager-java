package app;

import java.io.IOException;

import app.data.Book;
import app.data.BookDatabase;
import app.util.Utilities;
import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        Utilities.loadWindow("MainMenuPane", "LibraryManager", stage);

        BookDatabase database = new BookDatabase("E:/Hoc/oop-btl-1/src/main/resources/data/test-database.txt");
        Book book = database.GetBook(2);
        System.out.println(book.name + " " + book.publisher);
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}