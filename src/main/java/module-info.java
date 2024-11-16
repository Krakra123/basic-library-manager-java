module app {
    requires javafx.controls;
    requires javafx.fxml;
	requires spring.context;
	requires spring.web;
	requires java.base;
	requires spring.boot;
	requires spring.boot.autoconfigure;
	requires com.fasterxml.jackson.core;
	requires jakarta.ws.rs;
	requires com.fasterxml.jackson.databind;

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
