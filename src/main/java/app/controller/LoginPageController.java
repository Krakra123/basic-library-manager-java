package app.controller;

import java.io.IOException;

import app.managers.LogInManager;
import app.managers.MainDisplayManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

@SuppressWarnings("exports")
public class LoginPageController {
	
	private LogInManager manager;

	@FXML
	public TextField usernameTextField;
	
	@FXML
	public TextField passwordTextField;

	public void setManager(LogInManager manager) {
		this.manager = manager;
	}

	public void login(ActionEvent event) throws IOException {
		manager.tryLogin(usernameTextField.getText(), passwordTextField.getText());
	}

	public void register(ActionEvent event) throws IOException {
		manager.openRigisterPage();
	}
}
