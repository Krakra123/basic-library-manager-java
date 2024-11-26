package app.managers;

import app.controller.LoginPageController;
import app.controller.RegisterController;
import app.data.Account;
import app.managers.StateManager.State;
import app.util.AccountsManager;
import javafx.scene.input.KeyCode;

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

	public LogInManager(AppManager manager) {
		super(manager);

		loginPageFXMLContent = new LoadableFXMLContent(LOGIN_PAGE_FXML);
		registerPageFXMLContent = new LoadableFXMLContent(REGISTER_PAGE_FXML);

		loginPageController = loginPageFXMLContent.getData().getController(LoginPageController.class);
		registerPageController = registerPageFXMLContent.getData().getController(RegisterController.class);

		loginPageFXMLContent.setEnableCallback(() -> { onLoginPageEnable(); });
		registerPageFXMLContent.setEnableCallback(() -> { onRegisterPageEnable(); });

		InputManager.handleKeyShortcut(KeyCode.ENTER, StateManager.State.LOGIN, () -> {
			loginPageController.login();
		});
		InputManager.handleKeyShortcut(KeyCode.ENTER, StateManager.State.REGISTER, () -> {
			registerPageController.register();
		});
	}

	public void tryLogin(String username, String password) {
		username = username.trim();
		if (username.matches(".*\\s.*|^$")) {
			manager.getDialogsManager().showConfirmDialog("Try again.", "Username invalid", () -> {});
			return;
		}

		if (AccountsManager.tryLogin(username, password)) {
			manager.openMainDisplayWindow();
			manager.getUserManager().setCurrentUser(AccountsManager.findAccount(username));
		} else {
			manager.getDialogsManager().showConfirmDialog("Try again.", "Wrong username or password", () -> {});
		}
	}

	public void tryRegister(String username, String password, String rePassword, Account.AccountType type) {
		if (!password.equals(rePassword)) {
			manager.getDialogsManager().showConfirmDialog("Try again.", "Password mismatched", () -> {});
			return;
		}

		username = username.trim();
		if (username.matches(".*\\s.*|^$")) {
			manager.getDialogsManager().showConfirmDialog("Try again.", "Username invalid", () -> {});
			return;
		}

		if (AccountsManager.tryRegister(username, password, type)) {
			openLoginPageOnWindow();
		} else {
			manager.getDialogsManager().showConfirmDialog("Try again.", "Username already existed", () -> {});
		}
	}

	public void openLoginPageOnWindow() {
		manager.loadOnWindow(loginPageFXMLContent);
		StateManager.setState(State.LOGIN);
	}
	public void openRigisterPageOnWindow() {
		manager.loadOnWindow(registerPageFXMLContent);
		registerPageController.clear();
		StateManager.setState(State.REGISTER);
	}

	private void onLoginPageEnable() {
		manager.getStage().setWidth(WINDOW_WIDTH);
        manager.getStage().setHeight(WINDOW_HEIGHT);
        manager.getStage().centerOnScreen();
        manager.getStage().setResizable(false);

		loginPageController.setManager(this);
	}

	private void onRegisterPageEnable() {
		registerPageController.setManager(this);
	}
}
