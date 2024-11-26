package app.controller;

import java.io.IOException;

import app.managers.DialogHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

@SuppressWarnings("exports")
public class ConfirmDialogController {

    protected DialogHandler manager;
    public void setManager(DialogHandler manager) {
        this.manager = manager;
    }
    
    @FXML
    protected Label title;

    @FXML
    protected Label detail;

    public void setText(String t, String d) {
        title.setText(t);
        detail.setText(d);
    }

    public void confirm(ActionEvent event) throws IOException {
        manager.confirm();
    }

    public void cancel(ActionEvent event) throws IOException {
        manager.cancel();
    }
}