package app.controller;

import java.io.IOException;

import app.managers.LogInManager;
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
		
	}

	// public void login(ActionEvent event) throws IOException {
		
	// 	String username = usernameTextField.getText();
	// 	String password = passwordTextField.getText();
		
	// 	if (LogInManager.getAccount(username) == LogInManager.getNoAccount() || !LogInManager.getAccount(username).checkPassword(password)) {
	// 		System.out.println("username or password not true");
	// 	} else {
	// 		// TODO use general scene util load
	// 		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/MainDisplay.fxml"));
	// 		root = loader.load();
			
	// 		//homepageController scene2Controller = loader.getController();
	// 		//homepageController.displayName(username);	
			
	// 		//root = FXMLLoader.load(getClass().getResource("Scene1.fxml"));
	// 		stage = (Stage) ( (Node) event.getSource() ).getScene().getWindow();
	// 		scene = new Scene(root);
	// 		stage.setScene(scene);
	// 		stage.setTitle("LibraryManager");
	// 		stage.show();	
	// 	}
	
	// }
	
	// public void register(ActionEvent event) throws IOException {
	// 	FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/RegisterPage.fxml"));
	// 	root = loader.load();
	// 	stage = (Stage) ( (Node) event.getSource() ).getScene().getWindow();
	// 	scene = new Scene(root);
	// 	stage.setScene(scene);
	// 	stage.setTitle("Register");
	// 	stage.show();
	// }
}
