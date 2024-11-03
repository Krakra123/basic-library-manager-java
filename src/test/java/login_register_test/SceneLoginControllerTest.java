package login_register_test;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import login_register.LogInManager;
import user_account.UserAccount;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;
import app.util.Utilities;
import javafx.application.Application;
import org.testfx.assertions.api.Assertions;
public class SceneLoginControllerTest extends ApplicationTest {

	private Stage primaryStage;  // Store a reference to the stage
	@Override
    public void start(Stage stage) throws Exception {
        System.setProperty("testfx.headless", "false");

        // Store the reference to the primary stage
        this.primaryStage = stage;

        // Load the LogIn_page.fxml to verify it loads correctly
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/login_register/LogIn_page.fxml"));
        Parent root = loader.load();

        // Set up the stage with the loaded scene
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("LogIn Page Preview");
        stage.show();  // Show the stage with the LogIn page as the initial scene
    }
	
	/*@Override
    public void start(Stage stage) throws IOException {
        Utilities.loadWindow("MainMenuPane", "LibraryManager", stage);
    }*/
	
    @Test
    public void testSuccessfulLoginSwitchesToMainPage() {
        // Create a UserAccount with the correct credentials
        UserAccount a = new UserAccount("123", "123");
        LogInManager.addNewAccount(a);

        // Simulate user input
        clickOn("#usernameTextField").write("123");
        clickOn("#passwordTextField").write("123");
        clickOn("#loginButton");

        try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        // Verify that the main menu is shown by checking the stage title
        assertTrue(primaryStage.getTitle().equals("LibraryManager"));

        // Alternatively, check if an element in MainMenuPane is visible
        //Assertions.assertThat(lookup("#mainMenuPaneIdentifier").query()).isVisible();
    }
}