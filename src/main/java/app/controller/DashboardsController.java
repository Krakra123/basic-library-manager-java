package app.controller;

import app.managers.DashboardManager;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.control.Label;

@SuppressWarnings("exports")
public class DashboardsController {
    
    public DashboardManager manager;

    @FXML
    public Label book;

    @FXML
    public Label cat;

    @FXML
    public LineChart<Integer, Integer> chart;

    public void updateTotalBook(int x) {
        book.setText(Integer.toString(x));
    }

    public void updateCat(String x) {
        cat.setText(x);
    }

    
}
