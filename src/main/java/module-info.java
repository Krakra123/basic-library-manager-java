module app {
    requires javafx.controls;
    requires javafx.fxml;

    opens app to javafx.fxml;
    opens app.controller to javafx.fxml;
    opens app.managers to javafx.fxml;
    opens app.util to javafx.fxml;
    
    exports app;
    exports app.controller;
    exports app.data;
    exports app.interfaces;
    exports app.managers;
    exports app.util;
}
