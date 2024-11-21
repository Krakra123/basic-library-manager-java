package app.managers;

import java.util.ArrayList;
import java.util.List;

import app.controller.LoginPageController;
import app.controller.RegisterController;
import app.data.Account;

@SuppressWarnings({"FieldMayBeFinal"})
public class LogInManager extends BaseManager {

	private static final String LOGIN_PAGE_FXML = "LogInPage";
	private static final String REGISTER_PAGE_FXML = "RegisterPage";

	private static final int WINDOW_WIDTH = 400;
    private static final int WINDOW_HEIGHT = 500;

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

	private AccountsManager accountsManager;

	private List<Account> accountList;

	public LogInManager(AppManager manager) {
		super(manager);

		accountList = new ArrayList<>();

		loginPageFXMLContent = new LoadableFXMLContent(LOGIN_PAGE_FXML);
		registerPageFXMLContent = new LoadableFXMLContent(REGISTER_PAGE_FXML);

		loginPageController = loginPageFXMLContent.getData().getController(LoginPageController.class);
		registerPageController = registerPageFXMLContent.getData().getController(RegisterController.class);

		accountsManager = manager.getAccountsManager();

		loginPageFXMLContent.setEnableCallback(() -> { onLoginPageEnable(); });
		registerPageFXMLContent.setEnableCallback(() -> { onRegisterPageEnable(); });
	}

	public void addAccount(Account account) {
		accountList.add(account);
	}

	public void tryLogin(String username, String password) {
		username = username.trim();
		if (username.matches(".*\\s.*|^$")) {
			System.out.println("Username invalid");
			return;
		}

		if (accountsManager.tryLogin(username, password)) {
			manager.openMainDisplayWindow();
		} else {
			System.out.println("Username or password not true");
		}
	}

	public void tryRegister(String username, String password, String rePassword, Account.AccountType type) {
		if (!password.equals(rePassword)) {
			System.out.println("Password mismatched");
			return;
		}

		username = username.trim();
		if (username.matches(".*\\s.*|^$")) {
			System.out.println("Username invalid");
			return;
		}

		if (accountsManager.tryRegister(username, password, type)) {
			openLoginPageOnWindow();
		} else {
			System.out.println("Username already existed");
		}
	}

	public void openLoginPageOnWindow() {
		manager.loadOnWindow(loginPageFXMLContent);
	}
	public void openRigisterPageOnWindow() {
		manager.loadOnWindow(registerPageFXMLContent);
		registerPageController.clear();
	}

	private void onLoginPageEnable() {
		manager.getStage().setWidth(WINDOW_WIDTH);
        manager.getStage().setHeight(WINDOW_HEIGHT);

		loginPageController.setManager(this);
	}

	private void onRegisterPageEnable() {
		registerPageController.setManager(this);
	}
}
