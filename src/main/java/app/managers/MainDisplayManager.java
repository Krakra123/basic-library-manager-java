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

    private MainDisplayController mainDisplayController;

    private BookLibraryManager bookLibraryManager;

    public MainDisplayManager(AppManager manager) {
        super(manager);

        mainDisplayFXMLContent = new LoadableFXMLContent(MAINDISPLAY_FXML);

        mainDisplayController = mainDisplayFXMLContent.getData().getController(MainDisplayController.class);

		mainDisplayFXMLContent.setEnableCallback(() -> { onMainDisplayEnable(); });

        bookLibraryManager = new BookLibraryManager(manager);
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
        bookLibraryManager.loadOn(contentPane);
    }
}
