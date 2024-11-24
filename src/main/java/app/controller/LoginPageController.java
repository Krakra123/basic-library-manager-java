package app.controller;

import java.io.IOException;

import app.managers.LogInManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

@SuppressWarnings("exports")
public class LoginPageController {
	
	private LogInManager manager;

	public void setManager(LogInManager manager) {
		this.manager = manager;
	}

	@FXML
	public TextField username;
	
	@FXML
	public TextField password;

	public void login() {
		manager.tryLogin(username.getText(), password.getText());
	}
	public void handleLogin(ActionEvent event) throws IOException {
		manager.tryLogin(username.getText(), password.getText());
	}

	public void handleRegister(ActionEvent event) throws IOException {
		manager.openRigisterPageOnWindow();
	}
}
