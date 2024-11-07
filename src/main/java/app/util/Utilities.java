package app.util;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import app.App;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Utilities {

    public static class FXMLData {
        public Parent root;
        public Object controller;

        public FXMLData() {
            this.root = null;
            this.controller = null;
        }

        public FXMLData(Parent root, Object controller) {
            this.root = root;
            this.controller = controller;
        }
    }
    
    public static final String ICON_IMAGE_DIR = "/resources/icon.png";
    private static final SimpleDateFormat DATE_TIME_FORMAT = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss a");
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd-MM-yyyy");

    public static void setStageIcon(Stage stage) {
        stage.getIcons().add(new Image(ICON_IMAGE_DIR));
    }

    public static FXMLData loadFXML(String fxml) {
        FXMLData data = new FXMLData();
        try {
            FXMLLoader loader = new FXMLLoader(App.class.getResource("/fxml/" + fxml + ".fxml"));
            data.root = loader.load();
            data.controller = loader.getController();
        } catch (IOException | IllegalStateException e) {
            System.err.println("Failed to load FXML: /fxml/" + fxml + ".fxml; Message: " + e.getMessage());
        }
        return data;
    }
    
    public static FXMLData loadWindow(String fxml, String title, Stage parentStage, Stage outStage) {
        FXMLData data = new FXMLData();
        try {
            FXMLLoader loader = new FXMLLoader(App.class.getResource("/fxml/" + fxml + ".fxml"));
            data.root = loader.load();
            data.controller = loader.getController();
            if (parentStage != null) {
                outStage = parentStage;
            } else {
                outStage = new Stage(StageStyle.DECORATED);
            }
            outStage.setTitle(title);
            outStage.setScene(new Scene(data.root));
            outStage.show();
            // setStageIcon(stage);
        } catch (IOException | IllegalStateException e) {
            System.err.println("Failed to load FXML: /fxml/" + fxml + ".fxml; Message: " + e.getMessage());
        }
        return data;
    }

    public static String formatDateTimeString(Date date) {
        return DATE_TIME_FORMAT.format(date);
    }

    public static String formatDateTimeString(Long time) {
        return DATE_TIME_FORMAT.format(new Date(time));
    }

    public static String getDateString(Date date) {
        return DATE_FORMAT.format(date);
    }
}
