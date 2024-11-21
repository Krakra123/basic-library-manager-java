module app {
	requires java.base;
    requires java.net.http;
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
	requires resteasy.client;
	requires org.json;

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
