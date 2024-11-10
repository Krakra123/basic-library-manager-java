package app.controller;

import java.io.IOException;

import app.managers.ButtonHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class RegisterController {

	private Stage stage;
	private Scene scene;
	private Parent root;
	
	@FXML
	TextField usernameTextField;
	
	@FXML
	TextField passwordTextField;
	
	@FXML 
	TextField reenterPassWordTextField;
	
	@FXML
	Label alert;

	private ButtonHandler registerButtonHandler;

	public void setRegisterButtonHandler(ButtonHandler registerButtonHandler) {
		this.registerButtonHandler = registerButtonHandler;
	}

	public void register(ActionEvent event) throws IOException {
		registerButtonHandler.Call();
	}
	
	// @FXML
	// public void register(ActionEvent event) throws IOException {
	// 	String username = usernameTextField.getText();
	// 	String password = passwordTextField.getText();
	// 	String reenterPassword = reenterPassWordTextField.getText();
		
	// 	if (LogInManager.isUsernameExisted(username)) {
	// 		System.out.println("username existed");
	// 		alert.setText("Username is already existed");
	// 		return;
	// 	} else if (!password.equals(reenterPassword)) {
	// 		System.out.println("your password and reenter password not the same");
	// 		alert.setText("Your password and reenter password is not the same");
	// 		return;
	// 	} else {
	// 		LogInManager.addNewAccount(new UserAccount(username, password));
	// 		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/LoginPage.fxml"));
	// 		root = loader.load();
	// 		stage = (Stage) ( (Node) event.getSource() ).getScene().getWindow();
	// 		scene = new Scene(root);
	// 		stage.setScene(scene);
	// 		stage.setTitle("LoginPage");
	// 		stage.show();
	// 	}
	// }
}
