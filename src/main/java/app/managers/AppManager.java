package app.managers;

import app.util.Utilities;
import javafx.scene.Parent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

@SuppressWarnings({"FieldMayBeFinal", "exports"})
public class AppManager {
    
    private static final String WINDOW_FXML = "Blank";
    private static final String TITLE = "Library Manager";

    private Utilities.FXMLData windowData;
    private VBox windowRootVBox;

    private LoadableFXMLContentManager curContent;

    private MainDisplayManager mainDisplayManager;
    private LogInManager loginManager;

    public AppManager(Stage stage) {
        mainDisplayManager = new MainDisplayManager(this);
        loginManager = new LogInManager(this);

        windowData = Utilities.loadFXMLWindow(WINDOW_FXML, TITLE, stage);
        windowRootVBox =  windowData.getRoot(VBox.class);
    }

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
