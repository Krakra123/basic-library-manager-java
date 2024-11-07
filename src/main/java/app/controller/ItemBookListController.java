package app.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class ItemBookListController extends ItemBookController {
    
    @FXML
    private Label name;

    @FXML
    private Label publisher;

    @Override
    public void update() {
        name.setText(data.name);
        publisher.setText(data.publisher);
    }
}
