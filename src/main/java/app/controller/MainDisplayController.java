package app.controller;

import java.io.IOException;

import app.data.BookCollection;
import app.util.Utilities;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.VBox;

public class MainDisplayController {
    
    @FXML
    private VBox contentPane;

    @FXML
    private void handleMenuButton() {

    }

    @FXML
    private void handleLibraryButton() {

    }

    @FXML
    private void handleSettingButton() {
        
    }

    @FXML
    private void handleInfoButton() {

    }

    public void loadCollection(BookCollection collection) {
        Utilities.FXMLData collectionFXML = Utilities.loadFXML("CollectionItems");
        if (collectionFXML.root instanceof VBox root) {
            contentPane.getChildren().clear();
            contentPane.getChildren().add(root);
        } else {
            throw new RuntimeException("CollectionItems's root must be a VBox");
        }

        if (collectionFXML.controller instanceof CollectionItemsController controller) {
            controller.update(collection);
        } else {
            throw new RuntimeException("CollectionItems has wrong controller");
        }
    }
}
