package app.managers;

import java.util.ArrayList;
import java.util.List;

import app.controller.LoginPageController;
import app.controller.RegisterController;
import app.data.UserAccount;
import app.util.Utilities;

@SuppressWarnings({"FieldMayBeFinal"})
public class LogInManager {

	private AppManager appManager;

	private LoadableFXMLContent loginPageContent;
	private LoadableFXMLContent registerPageContent;

    private LoginPageController loginPageController;
    private RegisterController registerPageController;

	private List<UserAccount> accountList;

	public LogInManager(AppManager manager) {
		appManager = manager;
		accountList = new ArrayList<>();

		loginPageContent = new LoadableFXMLContent("LogInPage");
		registerPageContent = new LoadableFXMLContent("RegisterPage");

		loginPageController = loginPageContent.getData().getController(LoginPageController.class);
		registerPageController = registerPageContent.getData().getController(RegisterController.class);

		loginPageContent.setEnableCallback(() -> { onLoginPageEnable(); });
		registerPageContent.setEnableCallback(() -> { onRegisterPageEnable(); });
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
		appManager.loadContent(loginPageContent);
	}
	public void openRigisterPage() {
		appManager.loadContent(registerPageContent);
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
		return loginPageContent;
	}

	public LoadableFXMLContent getRegisterPageContent() {
		return registerPageContent;
	}

	private void onLoginPageEnable() {
		loginPageController.setManager(this);
	}

	private void onRegisterPageEnable() {
		// registerPageController.setManager(this);
	}
}
