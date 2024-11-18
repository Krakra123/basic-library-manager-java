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

@SuppressWarnings({"exports"})
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

        public <T> T getController(Class<T> classT) {
            if (controller == null) {
                throw new RuntimeException("Controller null.");
            }
            if (classT.isInstance(controller)) {
                return classT.cast(controller);
            } else {
                throw new RuntimeException("Wrong cast: cannot cast controller to target type.");
            }
        }

        public <T> T getRoot(Class<T> classT) {
            if (root == null) {
                throw new RuntimeException("Root null.");
            }
            if (classT.isInstance(root)) {
                return classT.cast(root);
            } else {
                throw new RuntimeException("Wrong cast: cannot cast root to target type.");
            }
        }
    }
    
    private static final SimpleDateFormat DATE_TIME_FORMAT = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss a");
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd-MM-yyyy");

    public static FXMLData loadFXML(String fxml) {
        FXMLData data = new FXMLData();
        try {
            FXMLLoader loader = new FXMLLoader(App.class.getResource("/fxml/" + fxml + ".fxml"));
            data.root = loader.load();
            data.controller = loader.getController();
        } catch (IOException | RuntimeException e) {
            System.err.println("Failed to load FXML: /fxml/" + fxml + ".fxml.");
            e.printStackTrace();
        }
        return data;
    }
    
    public static FXMLData loadFXMLWindow(String fxml, String title, Stage stage) {
        FXMLData data = new FXMLData();
        try {
            FXMLLoader loader = new FXMLLoader(App.class.getResource("/fxml/" + fxml + ".fxml"));
            data.root = loader.load();
            data.controller = loader.getController();
            if (stage == null) {
                stage = new Stage(StageStyle.DECORATED);
            }
            stage.setTitle(title);
            stage.setScene(new Scene(data.root));
            stage.show();
        } catch (IOException | RuntimeException e) {
            System.err.println("Failed to load FXML: /fxml/" + fxml + ".fxml.");
            e.printStackTrace();
        }
        return data;
    }

    public static void setStageIcon(Stage stage, String iconDir) {
        stage.getIcons().add(new Image(iconDir));
    }

    // public static void logParentHierarchy(Parent parent) {
    //     logParentHierarchy(parent, 0);
    // }

    // private static void logParentHierarchy(Parent parent, int level) {
    //     String indentation = "  ".repeat(level);
    //     System.out.println(indentation + parent.getClass().getSimpleName());

    //     for (Node node : parent.getChildrenUnmodifiable()) {
    //         if (node instanceof Parent p) {
    //             logParentHierarchy(p, level + 1);
    //         } else {
    //             System.out.println(indentation + "  " + node.getClass().getSimpleName());
    //         }
    //     }
    // }

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
