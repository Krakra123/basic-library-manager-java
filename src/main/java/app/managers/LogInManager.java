package app.managers;

import java.util.ArrayList;
import java.util.List;

import app.controller.LoginPageController;
import app.controller.RegisterController;
import app.data.UserAccount;

@SuppressWarnings({"FieldMayBeFinal"})
public class LogInManager {

	private static final String LOGIN_PAGE_FXML = "LogInPage";
	private static final String REGISTER_PAGE_FXML = "RegisterPage";

	private AppManager appManager;

	private LoadableFXMLContent loginPageFXMLContent;
	private LoadableFXMLContent registerPageFXMLContent;

    private LoginPageController loginPageController;
    private RegisterController registerPageController;

	private List<UserAccount> accountList;

	public LogInManager(AppManager manager) {
		appManager = manager;
		accountList = new ArrayList<>();

		loginPageFXMLContent = new LoadableFXMLContent(LOGIN_PAGE_FXML);
		registerPageFXMLContent = new LoadableFXMLContent(REGISTER_PAGE_FXML);

		loginPageController = loginPageFXMLContent.getData().getController(LoginPageController.class);
		registerPageController = registerPageFXMLContent.getData().getController(RegisterController.class);

		loginPageFXMLContent.setEnableCallback(() -> { onLoginPageEnable(); });
		registerPageFXMLContent.setEnableCallback(() -> { onRegisterPageEnable(); });
	}

	public void addAccount(UserAccount account) {
		accountList.add(account);
	}

	public void tryLogin(String username, String password) {
		if (checkAccount(username, password)) {
			appManager.loadContent(appManager.getMainDisplayManager().getMainDisplayContent());
		} else {
			System.out.println("Username or password not true");
		}
	}

	public void openLoginPage() {
		appManager.loadContent(loginPageFXMLContent);
	}
	public void openRigisterPage() {
		appManager.loadContent(registerPageFXMLContent);
	}

	public boolean checkAccount(String username, String password) {
		for (UserAccount account : accountList) {
			if (account.username.equals(username) && account.checkPassword(password)) {
				return true;
			}
		}
		return false;
	}

	public LoadableFXMLContent getLoginPageContent() {
		return loginPageFXMLContent;
	}

	public LoadableFXMLContent getRegisterPageContent() {
		return registerPageFXMLContent;
	}

	private void onLoginPageEnable() {
		loginPageController.setManager(this);
	}

	private void onRegisterPageEnable() {
		registerPageController.setManager(this);
	}
}
