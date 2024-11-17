package app.managers;

import app.controller.MainDisplayController;
import javafx.scene.layout.StackPane;

@SuppressWarnings({"FieldMayBeFinal", "exports"})
public class MainDisplayManager {
    
    private static final String MAINDISPLAY_FXML = "MainDisplay";
    private static final int WINDOW_WIDTH = 1600;
    private static final int WINDOW_HEIGHT = 900;

    private AppManager appManager;

    private LoadableFXMLContent mainDisplayFXMLContent;

    private StackPane contentPane;

    private MainDisplayController mainDisplayController;

    public MainDisplayManager(AppManager manager) {
        appManager = manager;

        mainDisplayFXMLContent = new LoadableFXMLContent(MAINDISPLAY_FXML);

        mainDisplayController = mainDisplayFXMLContent.getData().getController(MainDisplayController.class);

		mainDisplayFXMLContent.setEnableCallback(() -> { onMainDisplayEnable(); });
    }

    public LoadableFXMLContent getMainDisplayContent() {
        return mainDisplayFXMLContent;
    }
    
	private void onMainDisplayEnable() {
        appManager.getStage().setWidth(WINDOW_WIDTH);
        appManager.getStage().setHeight(WINDOW_HEIGHT + 40);
        appManager.getStage().centerOnScreen();

        contentPane = mainDisplayController.contentPane;
	}

    public StackPane getContentPane() {
        return contentPane;
    }


}
