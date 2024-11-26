package app.controller;

import app.managers.AccountTabManager;
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
	public void logOut() {
		manager.logOut();
	}
	
}
