package app.managers;

import app.interfaces.ICallback;
import app.util.Utilities;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

@SuppressWarnings("exports")
public class LoadableFXMLContent {

    private final Utilities.FXMLData fxmlData;
    public Utilities.FXMLData getData() {
        return fxmlData;
    }
    private boolean loaded = false;
    private Pane pane;
    public Pane getPane() {
        return pane;
    }
    public void setPane(Pane pane) {
        this.pane = pane;
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
        this.pane = pane;

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

        if (pane != null) {
            fxmlData.root.setDisable(true);
            pane.getChildren().remove(fxmlData.root);
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
