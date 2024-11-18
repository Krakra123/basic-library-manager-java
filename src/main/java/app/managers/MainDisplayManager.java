package app.managers;

import app.controller.MainDisplayController;
import javafx.scene.layout.StackPane;

@SuppressWarnings({"FieldMayBeFinal", "exports"})
public class MainDisplayManager extends BaseManager {
    
    private static final String MAINDISPLAY_FXML = "MainDisplay";
    private static final int WINDOW_WIDTH = 1600;
    private static final int WINDOW_HEIGHT = 900;

    private LoadableFXMLContent mainDisplayFXMLContent;

    private StackPane contentPane;
    private LoadableFXMLContent currentFXMLContent;

    private MainDisplayController mainDisplayController;

    private BookLibraryManager bookLibraryManager;
    private UserLibraryManager userLibraryManager;

    public MainDisplayManager(AppManager manager) {
        super(manager);

        mainDisplayFXMLContent = new LoadableFXMLContent(MAINDISPLAY_FXML);

        mainDisplayController = mainDisplayFXMLContent.getData().getController(MainDisplayController.class);

		mainDisplayFXMLContent.setEnableCallback(() -> { onMainDisplayEnable(); });

        bookLibraryManager = new BookLibraryManager(manager);
        userLibraryManager = new UserLibraryManager(manager);
    }

    public void openMainDisplay() {
        manager.loadContent(mainDisplayFXMLContent);
    }

    public LoadableFXMLContent getMainDisplayContent() {
        return mainDisplayFXMLContent;
    }
    
	private void onMainDisplayEnable() {
        manager.getStage().setWidth(WINDOW_WIDTH);
        manager.getStage().setHeight(WINDOW_HEIGHT + 40);
        manager.getStage().centerOnScreen();

        mainDisplayController.setManager(this); 
        
        contentPane = mainDisplayController.contentPane;
        
        loadMainMenu();
	}

    public StackPane getContentPane() {
        return contentPane;
    }

    public void loadMainMenu() {
        LoadableFXMLContent content = bookLibraryManager.getBookCollectionListPaneFXMLContent();
        
        // FIXME
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

    public BookLibraryManager getBookLibraryManager() {
        return bookLibraryManager;
    }

    public UserLibraryManager getUserLibraryManager() {
        return userLibraryManager;
    }
}
