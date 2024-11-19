package app.managers;

import app.controller.MainDisplayController;
import app.controller.ToolbarController;
import javafx.scene.layout.AnchorPane;

@SuppressWarnings({"FieldMayBeFinal", "exports"})
public class MainDisplayManager extends BaseManager {
    
    private static final String MAIN_DISPLAY_FXML = "Main";
    private static final String TOOLBAR_FXML = "Toolbar";
    private static final int WINDOW_WIDTH = 1600;
    private static final int WINDOW_HEIGHT = 900;

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

    public MainDisplayManager(AppManager manager) {
        super(manager);

        mainDisplayFXMLContent = new LoadableFXMLContent(MAIN_DISPLAY_FXML);
        mainDisplayController = mainDisplayFXMLContent.getData().getController(MainDisplayController.class);

        toolbarFXMLContent = new LoadableFXMLContent(TOOLBAR_FXML);
        toolbarController = toolbarFXMLContent.getData().getController(ToolbarController.class);

        menuManager = new MenuManager(manager);
        userLibraryManager = new UserLibraryManager(manager);

		mainDisplayFXMLContent.setEnableCallback(() -> { onMainDisplayEnable(); });
    }

    public void loadMainMenu() {
        LoadableFXMLContent content = menuManager.getMainMenuFXMLContent();
        
        if (currentFXMLContent != content) {
            if (currentFXMLContent != null) {
                currentFXMLContent.hide();
            }

            content.openOn(contentPane); 
            currentFXMLContent = content;
        }
    }

    public void loadUserLibrary() {
        LoadableFXMLContent content = userLibraryManager.getUserLibraryUIFXMLContent();
        
        if (currentFXMLContent != content) {
            if (currentFXMLContent != null) {
                currentFXMLContent.hide();
            }

            content.openOn(contentPane); 
            currentFXMLContent = content;
        }
    }

    private void onMainDisplayEnable() {
        manager.getStage().setWidth(WINDOW_WIDTH);
        manager.getStage().setHeight(WINDOW_HEIGHT + 40);
        manager.getStage().centerOnScreen();

        
        mainDisplayController.setManager(this); 
        toolbarController.setManager(this);
        
        toolbarFXMLContent.openOn(mainDisplayController.toolbarPane);
        contentPane = mainDisplayController.contentPane;
        
        loadMainMenu();
	}
}
