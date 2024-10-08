package app;

import java.io.IOException;

import app.util.Utilities;

import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        Utilities.loadWindow("MainMenuPane", "LibraryManager", stage);
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}