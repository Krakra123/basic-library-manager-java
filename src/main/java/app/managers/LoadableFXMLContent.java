package app.managers;

import app.interfaces.ICallback;
import app.util.Utilities;
import javafx.scene.layout.Pane;

@SuppressWarnings("exports")
public class LoadableFXMLContent {
    
    private final Utilities.FXMLData fxmlData;
    private boolean loaded = false;
    private Pane curPane;

    private ICallback enableCallback;
    private ICallback disableCallback;

    public LoadableFXMLContent(String fxml) {
        fxmlData = Utilities.loadFXML(fxml);
        fxmlData.root.setDisable(true);
    }

    public Utilities.FXMLData getData() {
        return fxmlData;
    }

    public void setEnableCallback(ICallback callback) {
        enableCallback = callback;
    }
    public void setDisableCallback(ICallback callback) {
        disableCallback = callback;
    }

    public void loadOn(Pane pane) {
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

    public void destroy() {
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

    public Pane getCurrentPane() {
        return curPane;
    }

    public final Utilities.FXMLData getFXMLData() {
        return fxmlData;
    }
}
