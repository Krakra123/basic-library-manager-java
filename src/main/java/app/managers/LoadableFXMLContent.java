package app.managers;

import app.interfaces.ICallback;
import app.util.Utilities;
import javafx.scene.layout.AnchorPane;

@SuppressWarnings("exports")
public class LoadableFXMLContent {
    
    private final Utilities.FXMLData fxmlData;
    public Utilities.FXMLData getData() {
        return fxmlData;
    }
    private boolean loaded = false;
    private AnchorPane anchorPane;
    public AnchorPane getAnchorPane() {
        return anchorPane;
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

    public void openOn(AnchorPane pane) {
        if (loaded) {
            System.out.println("FXML already loaded.");
            return;
        }
        loaded = true;
        
        fxmlData.root.setDisable(false);
        pane.getChildren().add(fxmlData.root);
        anchorPane = pane;

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

        if (anchorPane != null) {
            fxmlData.root.setDisable(true);
            anchorPane.getChildren().remove(fxmlData.root);
        }
        else {
            System.out.println("FXML have no root pane");
        }

        if (disableCallback != null) {
            disableCallback.Call();
        }
    }

    public void setEnable() {
        if (loaded) {
            System.out.println("FXML already loaded.");
            return;
        }
        loaded = true;

        fxmlData.root.setDisable(false);

        if (enableCallback != null) {
            enableCallback.Call();
        }
    }

    public void stickToTopAnchorPane() {
        AnchorPane.setTopAnchor(fxmlData.root, 0.0);
    }
    public void stickToBottomAnchorPane() {
        AnchorPane.setBottomAnchor(fxmlData.root, 0.0);
    }
    public void stickToRightAnchorPane() {
        AnchorPane.setRightAnchor(fxmlData.root, 0.0);
    }
    public void stickToLeftAnchorPane() {
        AnchorPane.setLeftAnchor(fxmlData.root, 0.0);
    }

    public void stickToHorizontalAnchorPane() {
        AnchorPane.setRightAnchor(fxmlData.root, 0.0);
        AnchorPane.setLeftAnchor(fxmlData.root, 0.0);
    }
    public void stickToVerticalAnchorPane() {
        AnchorPane.setTopAnchor(fxmlData.root, 0.0);
        AnchorPane.setBottomAnchor(fxmlData.root, 0.0);
    }
    public void stickToWholeAnchorPane() {
        AnchorPane.setTopAnchor(fxmlData.root, 0.0);
        AnchorPane.setBottomAnchor(fxmlData.root, 0.0);
        AnchorPane.setRightAnchor(fxmlData.root, 0.0);
        AnchorPane.setLeftAnchor(fxmlData.root, 0.0);
    }
}
