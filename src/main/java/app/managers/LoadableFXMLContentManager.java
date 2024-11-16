package app.managers;

import app.util.Utilities;
import javafx.scene.layout.VBox;

@SuppressWarnings("exports")
public abstract class LoadableFXMLContentManager {
    
    protected Utilities.FXMLData fxmlData;
    protected boolean loaded = false;
    protected VBox curParent;

    public LoadableFXMLContentManager(String fxml) {
        fxmlData = Utilities.loadFXML(fxml);
        fxmlData.root.setDisable(true);
    }

    public abstract void onEnable();
    public abstract void onDisable();

    public void loadOn(VBox root) {
        if (loaded) {
            System.out.println("FXML already loaded.");
            return;
        }

        loaded = true;
        
        fxmlData.root.setDisable(false);
        root.getChildren().add(fxmlData.root);
        curParent = root;

        onEnable();
    }

    public void remove() {
        if (!loaded) {
            System.out.println("FXML have not yet loaded.");
            return;
        }

        loaded = false;

        fxmlData.root.setDisable(true);
        curParent.getChildren().remove(fxmlData.root);

        onDisable();
    }

    public final Utilities.FXMLData getFXMLData() {
        return fxmlData;
    }
}
