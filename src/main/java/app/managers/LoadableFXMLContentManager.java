package app.managers;

import app.util.Utilities;

public abstract class LoadableFXMLContentManager {
    
    protected Utilities.FXMLData fxmlData;

    public LoadableFXMLContentManager(String fxml) {
        fxmlData = Utilities.loadFXML(fxml);
    }

    public abstract void onEnable();
    public abstract void onDisable();

    public final Utilities.FXMLData getFXMLData() {
        return fxmlData;
    }

    // public final void enableFXML() {
    //     fxmlData.root.setDisable(false);
    //     fxmlData.root.setVisible(true);
    // }
    // public final void disableFXML() {
    //     fxmlData.root.setDisable(true);
    //     fxmlData.root.setVisible(false);
    // }
}
