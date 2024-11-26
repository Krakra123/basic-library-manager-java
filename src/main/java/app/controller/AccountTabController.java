package app.controller;

import java.io.IOException;

import app.managers.AccountTabManager;
import app.managers.BookCollectionHandler.GroupByType;
import app.managers.BookCollectionHandler.SortByType;
import app.managers.UserLibraryManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;
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
