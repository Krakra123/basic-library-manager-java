package app.controller;

import java.io.IOException;

import app.login_register.LogInManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class SceneLoginController {

	private Stage stage;
	private Scene scene;
	private Parent root;
	
	@FXML
	TextField usernameTextField;
	
	@FXML
	TextField passwordTextField;
	
	public void login(ActionEvent event) throws IOException {
		
		String username = usernameTextField.getText();
		String password = passwordTextField.getText();
		
		if (LogInManager.getAccount(username) == LogInManager.getNoAccount() || !LogInManager.getAccount(username).checkPassword(password)) {
			System.out.println("username or password not true");
		} else {
			// TODO use general scene util load
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/MainMenuPane.fxml"));
			root = loader.load();
			
			//homepageController scene2Controller = loader.getController();
			//homepageController.displayName(username);	
			
			//root = FXMLLoader.load(getClass().getResource("Scene1.fxml"));
			stage = (Stage) ( (Node) event.getSource() ).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.setTitle("LibraryManager");
			stage.show();	
		}
		
		
	}
	
}
