package app.controller;

import java.io.IOException;
import java.util.List;

import app.managers.DialogHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;

@SuppressWarnings("exports")
public class SavingBookDialogController {

    private DialogHandler manager;
    public void setManager(DialogHandler manager) {
        this.manager = manager;
    } 
    
    @FXML
    public ComboBox<String> collection;

    public void updateCollectionView(List<String> stringList) {
        collection.getItems().addAll(stringList);
    }

    public void confirm(ActionEvent event) throws IOException {
        manager.confirm();
    }

    public void cancel(ActionEvent event) throws IOException {
        manager.cancel();
    }
}
