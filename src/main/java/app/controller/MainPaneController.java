package app.controller;

import java.io.IOException;

import app.App;
import app.library.BookCollection;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.VBox;

public class MainPaneController {
    
    @FXML
    private VBox contentPane;

    @FXML
    private void handleMenuButton() {
        loadContent("MenuLayout");
    }

    @FXML
    private void handleLibraryButton() {
        loadContent("LibraryLayout");
    }

    @FXML
    private void handleSettingButton() {
        
    }

    @FXML
    private void handleInfoButton() {
        
    }

    private void loadContent(String fxml) {
        try {
            Node content = FXMLLoader.load(getClass().getResource(fxml + ".fxml"));
            contentPane.getChildren().clear();
            contentPane.getChildren().add(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadCollection(BookCollection collection) throws IOException {
        try {
            FXMLLoader loader = new FXMLLoader(App.class.getResource("/fxml/BookCollection.fxml"));
            VBox box = loader.load();
            BookCollectionController c = (BookCollectionController)loader.getController();

            contentPane.getChildren().add(box);
            c.update(collection);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
