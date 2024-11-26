package app.controller;

import java.io.IOException;

import app.managers.AccountTabManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class AccountTabController {

	private AccountTabManager manager;

	public AccountTabManager getManager() {
		return manager;
	}

	public void setManager(AccountTabManager manager) {
		this.manager = manager;
	}
	
	@FXML
	private Button button;
	public void logOut(ActionEvent event) throws IOException {
		manager.logOut();
	}
	
}
