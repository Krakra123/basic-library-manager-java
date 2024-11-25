package app.managers;

import app.controller.MainDisplayController;
import app.controller.ToolbarController;
import javafx.scene.layout.AnchorPane;

@SuppressWarnings({"FieldMayBeFinal", "exports"})
public class MainDisplayManager extends BaseManager {
    
    private static final String MAIN_DISPLAY_FXML = "MainDisplay";
    private static final String TOOLBAR_FXML = "Toolbar";
    private static final int WINDOW_WIDTH = 1200;
    private static final int WINDOW_HEIGHT = 750;

    private LoadableFXMLContent mainDisplayFXMLContent;
    public LoadableFXMLContent getMainDisplayFXMLContent() {
        return mainDisplayFXMLContent;
    }
    private MainDisplayController mainDisplayController;

    private LoadableFXMLContent toolbarFXMLContent;
    private ToolbarController toolbarController;

    private AnchorPane contentPane;
    public AnchorPane getContentPane() {
        return contentPane;
    }
    private LoadableFXMLContent currentFXMLContent;

    private MenuManager menuManager;
    public MenuManager getMenuManager() {
        return menuManager;
    }
    private UserLibraryManager userLibraryManager;
    public UserLibraryManager getUserLibraryManager() {
        return userLibraryManager;
    }

    private AccountTabManager accountTabManager;
    public AccountTabManager getAccountTabManager() {
        return accountTabManager;
    }
    
    public MainDisplayManager(AppManager manager) {
        super(manager);

        mainDisplayFXMLContent = new LoadableFXMLContent(MAIN_DISPLAY_FXML);
        mainDisplayController = mainDisplayFXMLContent.getData().getController(MainDisplayController.class);

        toolbarFXMLContent = new LoadableFXMLContent(TOOLBAR_FXML);
        toolbarController = toolbarFXMLContent.getData().getController(ToolbarController.class);

        menuManager = new MenuManager(manager);
        userLibraryManager = new UserLibraryManager(manager);
        accountTabManager = new AccountTabManager(manager);

		mainDisplayFXMLContent.setEnableCallback(() -> { onMainDisplayEnable(); });
    }

    public void loadMainMenu() {
        loadOnMainDisplay(menuManager.getMainMenuFXMLContent());
    }

    public void loadUserLibrary() {
        loadOnMainDisplay(userLibraryManager.getUserLibraryFXMLContent());
    }

    public void loadAccountTab() {
    	loadOnMainDisplay(accountTabManager.getAccountTabFXMLContent());
    	System.out.println("pressed account");
    }
    
    private void loadOnMainDisplay(LoadableFXMLContent content) {
        if (currentFXMLContent != content) {
            if (currentFXMLContent != null) {
                currentFXMLContent.hide();
            }

            content.openOn(contentPane); 
            content.stickToWholeAnchorPane();
            currentFXMLContent = content;
        }
    }

    private void onMainDisplayEnable() {
        manager.getStage().setWidth(WINDOW_WIDTH);
        manager.getStage().setHeight(WINDOW_HEIGHT);
        manager.getStage().centerOnScreen();
        manager.getStage().setResizable(true);

        mainDisplayController.setManager(this); 
        toolbarController.setManager(this);
        
        toolbarFXMLContent.openOn(mainDisplayController.toolbarPane);
        toolbarFXMLContent.stickToWholeAnchorPane();
        contentPane = mainDisplayController.contentPane;
        
        loadMainMenu();
	}
}
