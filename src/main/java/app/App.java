package app;

import java.io.IOException;

import app.data.Book;
import app.data.BookCollection;
import app.data.UserAccount;
import app.managers.LogInManager;
import app.managers.MainDisplayManager;
import app.util.Utilities;
import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application {

	private Stage primaryStage;

    private MainDisplayManager mainDisplayManager;

    @Override
    public void start(Stage stage) throws IOException {
        LogInManager m = new LogInManager(stage);
        m.addAccount(new UserAccount("123", "123"));
        // mainDisplayManager = new MainDisplayManager(Utilities.loadWindow("MainDisplay", "LibraryManager", stage, primaryStage));
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}