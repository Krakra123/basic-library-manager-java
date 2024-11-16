package app.managers;

import java.util.ArrayList;
import java.util.List;

import app.controller.LoginPageController;
import app.data.UserAccount;
import app.util.Utilities;

public class LogInManager extends LoadableFXMLContentManager {

	@SuppressWarnings("FieldMayBeFinal")
	private AppManager appManager;

	@SuppressWarnings("FieldMayBeFinal")
    private LoginPageController loginPageController;

	@SuppressWarnings("FieldMayBeFinal")
	private List<UserAccount> accountList;

	public LogInManager(AppManager manager) {
		super("LogInPage");
		appManager = manager;
		accountList = new ArrayList<>();

		loginPageController = fxmlData.getController(LoginPageController.class);
		loginPageController.setManager(this);
	}

	public void addAccount(UserAccount account) {
		accountList.add(account);
	}

	public void tryLogin(String username, String password) {
		if (checkAccount(username, password)) {
			appManager.loadContent(appManager.getMainDisplayManager());
			Utilities.logParentHierarchy(appManager.getRoot());
		} else {
			System.out.println("Username or password not true");
		}
	}

	public boolean checkAccount(String username, String password) {
		for (UserAccount account : accountList) {
			if (account.username.equals(username) && account.checkPassword(password)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public void onEnable() {

	}

	@Override
	public void onDisable() {

	}
}
