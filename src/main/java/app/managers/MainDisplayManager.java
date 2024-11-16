package app.managers;

import app.controller.MainDisplayController;

@SuppressWarnings({"FieldMayBeFinal"})
public class MainDisplayManager {
    
    private static final int WINDOW_WIDTH = 1600;
    private static final int WINDOW_HEIGHT = 900;

    private AppManager appManager;

    private LoadableFXMLContent mainDisplayContent;

    private MainDisplayController mainDisplayController;

    public MainDisplayManager(AppManager manager) {
        appManager = manager;

        mainDisplayContent = new LoadableFXMLContent("MainDisplay");

        mainDisplayController = mainDisplayContent.getData().getController(MainDisplayController.class);

		mainDisplayContent.setEnableCallback(() -> { onMainDisplayEnable(); });
    }

    public LoadableFXMLContent getMainDisplayContent() {
        return mainDisplayContent;
    }
    
	private void onMainDisplayEnable() {
        appManager.getStage().setWidth(WINDOW_WIDTH);
        appManager.getStage().setHeight(WINDOW_HEIGHT + 40);
        appManager.getStage().centerOnScreen();
	}
}
