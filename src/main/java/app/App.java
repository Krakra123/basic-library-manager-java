package app;

import java.io.IOException;

import app.data.UserAccount;
import app.managers.AppManager;
import app.util.Utilities;
import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application {

	private Stage primaryStage;

    private AppManager appManager;

    @SuppressWarnings("exports")
    @Override
    public void start(Stage stage) throws IOException {
        primaryStage = stage;
        appManager = new AppManager(primaryStage);

        appManager.getLoginManager().addAccount(new UserAccount("1", "1"));

        appManager.loadContent(appManager.getLoginManager());

        Utilities.logParentHierarchy(appManager.getRoot());
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}