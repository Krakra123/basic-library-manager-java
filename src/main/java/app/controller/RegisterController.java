package app.controller;

import java.io.IOException;

import app.data.Account;
import app.data.Account.AccountType;
import app.managers.LogInManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

@SuppressWarnings("exports")
public class RegisterController {

	private LogInManager manager;
	
	@FXML
	public TextField usernameTextField;
	
	@FXML
	public TextField passwordTextField;

	@FXML
	public TextField rePasswordTextField;

	private Account.AccountType type = AccountType.USER;

	public void clear() {
		usernameTextField.clear();
		passwordTextField.clear();
		rePasswordTextField.clear();
	}

	public void setManager(LogInManager manager) {
		this.manager = manager;
	}

	public void register() {
		manager.tryRegister(usernameTextField.getText(), passwordTextField.getText(), rePasswordTextField.getText(), type);
	}
	public void register(ActionEvent event) throws IOException {
		manager.tryRegister(usernameTextField.getText(), passwordTextField.getText(), rePasswordTextField.getText(), type);
	}

	public void backToLogin(ActionEvent event) throws IOException {
		manager.openLoginPageOnWindow();
	}

	public void registerAsAdmin(ActionEvent event) throws IOException {
		if (type == AccountType.USER) type = AccountType.ADMIN;
		else if (type == AccountType.ADMIN) type = AccountType.USER;
	}
}
