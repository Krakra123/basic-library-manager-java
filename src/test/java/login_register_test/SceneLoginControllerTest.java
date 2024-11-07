package login_register_test;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import app.data.UserAccount;
import app.managers.LogInManager;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;
import app.util.Utilities;
import javafx.application.Application;
import org.testfx.assertions.api.Assertions;

public class SceneLoginControllerTest extends ApplicationTest {

	private Stage testStage;
	
	@Override
    public void start(Stage stage) throws IOException {
        Utilities.loadWindow("LogInPage", "Login", stage, testStage);
    }
	
    @Test
    public void testSuccessfulLoginSwitchesToMainPage() {
        UserAccount a = new UserAccount("123", "123");
        LogInManager.addNewAccount(a);

        // clickOn("#usernameTextField").write("123");
        // clickOn("#passwordTextField").write("123");
        // clickOn("#loginButton");

        try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
        // Verify that the main menu is shown by checking the stage title
        assertTrue(testStage.getTitle().equals("LibraryManager"));
    }
}