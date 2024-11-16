package app.managers;

import app.util.Utilities;
import javafx.scene.Parent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AppManager {
    
    private static final String WINDOW_FXML = "Blank";
    private static final String TITLE = "Library Manager";

    @SuppressWarnings("FieldMayBeFinal")
    private Utilities.FXMLData windowData;
    @SuppressWarnings("FieldMayBeFinal")
    private VBox windowRootVBox;

    private LoadableFXMLContentManager curContent;

    @SuppressWarnings("FieldMayBeFinal")
    private MainDisplayManager mainDisplayManager;
    @SuppressWarnings("FieldMayBeFinal")
    private LogInManager loginManager;

    @SuppressWarnings("exports")
    public AppManager(Stage stage) {
        mainDisplayManager = new MainDisplayManager(this);
        loginManager = new LogInManager(this);

        windowData = Utilities.loadFXMLWindow(WINDOW_FXML, TITLE, stage);
        windowRootVBox =  windowData.getRoot(VBox.class);
    }

    @SuppressWarnings("exports")
    public final Parent getRoot() {
        return windowData.root;
    }

    public final MainDisplayManager getMainDisplayManager() {
        return mainDisplayManager;
    }

    public final LogInManager getLoginManager() {
        return loginManager;
    }

    public final void loadContent(LoadableFXMLContentManager content) {
        if (curContent != null) curContent.onDisable();
        clearWindow();

        curContent = content;
        windowRootVBox.getChildren().add(content.getFXMLData().root);
        content.onEnable();
    }

    private void clearWindow() {
        windowRootVBox.getChildren().clear();
    }
}
