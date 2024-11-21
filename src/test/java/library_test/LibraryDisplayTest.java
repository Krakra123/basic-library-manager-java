package library_test;

import java.io.IOException;

import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;

import app.managers.AppManager;
import javafx.stage.Stage;

public class LibraryDisplayTest extends ApplicationTest {
    
	private Stage primaryStage;

    private AppManager appManager;

    @Override
    public void start(Stage stage) throws IOException {
        appManager = new AppManager();

        primaryStage = stage;
        appManager.makeWindow(primaryStage);

        appManager.loadLoginPage();
    }

    @Test
    public void RunFor10sec() {
        try {
            Thread.sleep(10000);
        } catch (Exception e) {
        }
    }
}
