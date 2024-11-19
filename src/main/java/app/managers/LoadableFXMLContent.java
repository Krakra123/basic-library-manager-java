package app.managers;

import app.interfaces.ICallback;
import app.util.Utilities;
import javafx.scene.layout.Pane;

@SuppressWarnings("exports")
public class LoadableFXMLContent {
    
    private final Utilities.FXMLData fxmlData;
    public Utilities.FXMLData getData() {
        return fxmlData;
    }
    private boolean loaded = false;
    private Pane curPane;
    public Pane getCurrentPane() {
        return curPane;
    }

    private ICallback enableCallback;
    private ICallback disableCallback;

    public LoadableFXMLContent(String fxml) {
        fxmlData = Utilities.loadFXML(fxml);
        fxmlData.root.setDisable(true);
    }

    public void setEnableCallback(ICallback callback) {
        enableCallback = callback;
    }
    public void setDisableCallback(ICallback callback) {
        disableCallback = callback;
    }

    public void openOn(Pane pane) {
        if (loaded) {
            System.out.println("FXML already loaded.");
            return;
        }

        loaded = true;
        
        fxmlData.root.setDisable(false);
        pane.getChildren().add(fxmlData.root);
        curPane = pane;

        if (enableCallback != null) {
            enableCallback.Call();
        }
    }

    public void hide() {
        if (!loaded) {
            System.out.println("FXML have not yet loaded.");
            return;
        }

        loaded = false;

        fxmlData.root.setDisable(true);
        curPane.getChildren().remove(fxmlData.root);

        if (disableCallback != null) {
            disableCallback.Call();
        }
    }
}
