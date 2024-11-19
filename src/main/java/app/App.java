package app;

import java.io.IOException;

import app.data.UserAccount;
import app.managers.AppManager;
import javafx.application.Application;
import javafx.stage.Stage;

@SuppressWarnings("exports")
public class App extends Application {

	private Stage primaryStage;
    private AppManager appManager;

    @Override
    public void start(Stage stage) throws IOException {
        setUserAgentStylesheet(STYLESHEET_MODENA);

        primaryStage = stage;
        appManager = new AppManager(primaryStage);

        appManager.getLoginManager().addAccount(new UserAccount("1", "1"));

        appManager.getLoginManager().openLoginPageOnWindow();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}