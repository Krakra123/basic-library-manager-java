module app {
    requires javafx.controls;
    requires javafx.fxml;

    exports app;
    exports app.util;
    exports app.controller;
    exports app.interfaces;

    opens app to javafx.fxml;
    opens app.controller to javafx.fxml;
    opens app.util to javafx.fxml;
}
