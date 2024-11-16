package app.managers;

import app.controller.MainDisplayController;

@SuppressWarnings({"FieldMayBeFinal"})
public class MainDisplayManager extends LoadableFXMLContentManager {
    
    private static final int WINDOW_WIDTH = 800;
    private static final int WINDOW_HEIGHT = 600;

    private AppManager appManager;

    private MainDisplayController mainDisplayController;

    public MainDisplayManager(AppManager manager) {
        super("MainDisplay");
        appManager = manager;

        mainDisplayController = fxmlData.getController(MainDisplayController.class);
    }
    
    @Override
	public void onEnable() {
        appManager.getStage().setWidth(WINDOW_WIDTH);
        appManager.getStage().setHeight(WINDOW_HEIGHT);
        appManager.getStage().centerOnScreen();
	}

	@Override
	public void onDisable() {

	}
}
