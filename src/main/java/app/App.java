package app;

import java.io.IOException;

import app.controller.MainPaneController;
import app.data.Book;
import app.data.BookDatabase;
import app.library.BookCollection;
import app.util.Utilities;
import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application {

	private Stage primaryStage;

    @Override
    public void start(Stage stage) throws IOException {
        Utilities.loadWindow("MainMenuPane", "LibraryManager", stage, primaryStage);
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}