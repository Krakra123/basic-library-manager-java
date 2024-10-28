module app {
    requires javafx.controls;
    requires javafx.fxml;

    exports app;
    exports app.util;
    exports app.controller;
    opens login_register to javafx.fxml;

    opens app to javafx.fxml;
    opens app.controller to javafx.fxml;
    opens app.util to javafx.fxml;
    exports login_register;
    exports user_account;
}
