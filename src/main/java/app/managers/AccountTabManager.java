package app.managers;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import app.controller.UserLibraryUIController;
import app.data.Book;
import app.data.BookCollection;
import app.managers.BookCollectionHandler.GroupByType;
import app.managers.BookCollectionHandler.SortByType;
import app.controller.AccountTabController;
import javafx.scene.input.KeyCode;
public class AccountTabManager extends BaseManager {

	private static final String ACCOUNT_TAB = "AccountTab";

	private LoadableFXMLContent accountTabFXMLContent;
	
	public LoadableFXMLContent getAccountTabFXMLContent() {
        return accountTabFXMLContent;
    }
	
	private AccountTabController accountTabController;
	
	public AccountTabManager(AppManager manager) {
		super(manager);
		accountTabFXMLContent = new LoadableFXMLContent(ACCOUNT_TAB);
		accountTabController = accountTabFXMLContent.getData().getController(AccountTabController.class);
		
		accountTabFXMLContent.setEnableCallback(() -> { onEnable(); });
		
	}
	
	private void onEnable() {

        accountTabController.setManager(this);
    }
	
	public void logOut() {
		this.manager.openLoginWindow();
	}
	
}
