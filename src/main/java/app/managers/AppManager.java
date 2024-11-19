package app.managers;

import app.util.Utilities;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

@SuppressWarnings({"FieldMayBeFinal", "exports"})
public class AppManager {
    
    private static final String WINDOW_FXML = "Blank";
    private static final String TITLE = "Library Manager";

    private Stage curStage;
    public final Stage getStage() {
        return curStage;
    }

    private Utilities.FXMLData windowData;
    private Pane windowRootPane;
    public final Parent getRoot() {
        return windowData.root;
    }

    private LoadableFXMLContent curContent;

    private MainDisplayManager mainDisplayManager;
    public final MainDisplayManager getMainDisplayManager() {
        return mainDisplayManager;
    }
    private LogInManager loginManager;
    public final LogInManager getLoginManager() {
        return loginManager;
    }

    public AppManager(Stage stage) {
        curStage = stage;
        mainDisplayManager = new MainDisplayManager(this);
        loginManager = new LogInManager(this);

        windowData = Utilities.loadFXMLWindow(WINDOW_FXML, TITLE, stage);
        windowRootPane = windowData.getRoot(Pane.class);
    }

    public final void loadOnWindow(LoadableFXMLContent content) {
        if (curContent != null) curContent.hide();
        clearWindow();

        curContent = content;
        content.openOn(windowRootPane);
    }

    public final void openMainDisplayWindow() {
        loadOnWindow(mainDisplayManager.getMainDisplayFXMLContent());
    }

    public final void openLoginWindow() {
        loadOnWindow(loginManager.getLoginPageContent());
    }

    public final void openRegisterWindow() {
        loadOnWindow(loginManager.getRegisterPageContent());
    }

    private void clearWindow() {
        windowRootPane.getChildren().clear();
    }
}
