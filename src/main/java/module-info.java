module app {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;

    opens app to javafx.fxml;
    exports app;
}
