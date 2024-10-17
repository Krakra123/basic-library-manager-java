package app.controller;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.StackPane;

public class MainPaneController {
    
    @FXML
    private StackPane contentPane;

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
}
