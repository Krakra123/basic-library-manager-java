package app.managers;

import java.util.ArrayList;
import java.util.List;

import app.controller.MainDisplayController;
import app.controller.SceneLoginController;
import app.data.UserAccount;
import app.util.Utilities;
import javafx.scene.Parent;
import javafx.stage.Stage;

public class LogInManager {

    private final String LOGIN_PAGE_FXML = "LogInPage";

	private Utilities.FXMLData loginPageFXML;
	private Parent loginPageRoot;
    private SceneLoginController loginPageController;

	private List<UserAccount> accountList = new ArrayList<UserAccount>();

	private ButtonHandler loginButtonHandler;
	private ButtonHandler registerButtonHandler;

	private Stage testStage; // FIXME remove

	public LogInManager(Stage stage) {
		System.out.println("GTFRDCFGYUH");
		loginPageFXML = Utilities.loadWindow(LOGIN_PAGE_FXML, "Login", stage, testStage);

		if (loginPageFXML.controller instanceof SceneLoginController c) {
            loginPageController = c;
        } else {
            throw new RuntimeException("MainDisplay has wrong controller");
        }

		loginPageRoot = loginPageFXML.root;

		loginButtonHandler = new ButtonHandler(() -> {
			loginHandler();
		});
		loginPageController.setLoginButtonHandler(loginButtonHandler);

		registerButtonHandler = new ButtonHandler(() -> {
			registerHandler();
		});
		loginPageController.setRegisterButtonHandler(registerButtonHandler);
	}

	public void addAccount(UserAccount account) {
		accountList.add(account);
	}

	public boolean checkAccount(String username, String password) {
		for (UserAccount account : accountList) {
			if (account.username.equals(username) && account.checkPassword(password)) {
				return true;
			}
		}
		return false;
	}

	private void loginHandler() {
		String username = loginPageController.usernameTextField.getText();
		String password = loginPageController.passwordTextField.getText();
		
		if (checkAccount(username, password)) {
			MainDisplayManager m = new MainDisplayManager(testStage);
		} else {
			System.out.println("Username or password not true");
		}

		// if (LogInManager.getAccount(username) == LogInManager.getNoAccount() || !LogInManager.getAccount(username).checkPassword(password)) {
		// 	System.out.println("username or password not true");
		// } else {
		// 	// TODO use general scene util load
		// 	FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/MainDisplay.fxml"));
		// 	root = loader.load();
			
		// 	//homepageController scene2Controller = loader.getController();
		// 	//homepageController.displayName(username);	
			
		// 	//root = FXMLLoader.load(getClass().getResource("Scene1.fxml"));
		// 	stage = (Stage) ( (Node) event.getSource() ).getScene().getWindow();
		// 	scene = new Scene(root);
		// 	stage.setScene(scene);
		// 	stage.setTitle("LibraryManager");
		// 	stage.show();	
		// }
	}

	private void registerHandler() {

	}
	
	// public UserAccount getAccount(String username) {
	// 	UserAccount returnAccount = noAccount;
	// 	for (int i = 0;i < accountList.size(); i++) {
	// 		if (username.equals(accountList.get(i).getUsername()) ) {
	// 			returnAccount = accountList.get(i);
	// 			break;
	// 		}
	// 	}
	// 	return returnAccount;
	// }
	
	// private static UserAccount noAccount = new UserAccount("siuuuuuuuuuu", "KieuVanTuyen"); // TODO remove hard-code
	// private static List<UserAccount> accountList = new ArrayList<UserAccount>();


	// public static UserAccount getNoAccount() {
	// 	return noAccount;
	// }
	
	// public static void addNewAccount(UserAccount newAccount) {
	// 	accountList.add(newAccount);
	// }
	
	// public static boolean isUsernameExisted(String username) {
	// 	for (UserAccount account : accountList) {
	// 		if (account.getUsername().equals(username)) {
	// 			return true;
	// 		}
	// 	}
	// 	return false;
	// }
}
