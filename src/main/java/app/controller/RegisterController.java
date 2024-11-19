package app.controller;

import java.io.IOException;

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

	public void setManager(LogInManager manager) {
		this.manager = manager;
	}

	public void register(ActionEvent event) throws IOException {
		manager.openLoginPageOnWindow();
	}

	public void backToLogin(ActionEvent event) throws IOException {
		manager.openLoginPageOnWindow();
	}
}
