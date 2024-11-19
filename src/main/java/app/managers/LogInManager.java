package app.managers;

import java.util.ArrayList;
import java.util.List;

import app.controller.LoginPageController;
import app.controller.RegisterController;
import app.data.UserAccount;

@SuppressWarnings({"FieldMayBeFinal"})
public class LogInManager extends BaseManager {

	private static final String LOGIN_PAGE_FXML = "LogInPage";
	private static final String REGISTER_PAGE_FXML = "RegisterPage";

	private static final int WINDOW_WIDTH = 400;
    private static final int WINDOW_HEIGHT = 400;

	private LoadableFXMLContent loginPageFXMLContent;
	public LoadableFXMLContent getLoginPageContent() {
		return loginPageFXMLContent;
	}
	private LoadableFXMLContent registerPageFXMLContent;
	public LoadableFXMLContent getRegisterPageContent() {
		return registerPageFXMLContent;
	}

    private LoginPageController loginPageController;
    private RegisterController registerPageController;

	private List<UserAccount> accountList;

	public LogInManager(AppManager manager) {
		super(manager);

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
			manager.loadOnWindow(manager.getMainDisplayManager().getMainDisplayFXMLContent());
		} else {
			System.out.println("Username or password not true");
		}
	}

	public void openLoginPageOnWindow() {
		manager.loadOnWindow(loginPageFXMLContent);
	}
	public void openRigisterPageOnWindow() {
		manager.loadOnWindow(registerPageFXMLContent);
	}

	private void onLoginPageEnable() {
		manager.getStage().setWidth(WINDOW_WIDTH);
        manager.getStage().setHeight(WINDOW_HEIGHT + 40);

		loginPageController.setManager(this);
	}

	private void onRegisterPageEnable() {
		registerPageController.setManager(this);
	}

	private boolean checkAccount(String username, String password) {
		for (UserAccount account : accountList) {
			if (account.username.equals(username) && account.checkPassword(password)) {
				return true;
			}
		}
		return false;
	}
}
