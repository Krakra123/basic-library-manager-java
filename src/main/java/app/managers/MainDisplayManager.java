package app.managers;

import app.controller.MainDisplayController;

public class MainDisplayManager extends LoadableFXMLContentManager {
    
    @SuppressWarnings("FieldMayBeFinal")
    private AppManager appManager;

    @SuppressWarnings({"unused", "FieldMayBeFinal"})
    private MainDisplayController mainDisplayController;

    public MainDisplayManager(AppManager manager) {
        super("MainDisplay");
        appManager = manager;

        mainDisplayController = fxmlData.getController(MainDisplayController.class);
    }
    
    @Override
	public void onEnable() {

	}

	@Override
	public void onDisable() {

	}
}
