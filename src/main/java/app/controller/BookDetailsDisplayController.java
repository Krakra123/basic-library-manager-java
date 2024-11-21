package app.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

@SuppressWarnings("exports")
public class BookDetailsDisplayController {
    
    @FXML
    public ImageView image;

    @FXML 
    public Label title;

    @FXML 
    public Label author;

    @FXML 
    public Label publisher;

    @FXML 
    public Label page;

    @FXML 
    public Label description;

    @FXML 
    public Label readLabel;

    @FXML
    public Label date;
}
