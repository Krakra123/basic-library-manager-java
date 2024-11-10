package app.managers;

import app.util.Utilities;

public class LoadableContentManager {
    
    protected Utilities.FXMLData fxmlData;

    public LoadableContentManager(String fxml) {
        fxmlData = Utilities.loadFXML(fxml);
    }
}
