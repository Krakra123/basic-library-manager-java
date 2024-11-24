package app.managers;

import app.util.Utilities;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

@SuppressWarnings({"FieldMayBeFinal", "exports"})
public class AppManager {

    private static AppManager instance;
    public static AppManager getInstance() {
        if (instance == null) {
            instance = new AppManager();
        }
        return instance;
    }
    
    private static final String WINDOW_FXML = "Blank";
    private static final String TITLE = "Library Manager";

    private Stage curStage;
    public final Stage getStage() {
        return curStage;
    }

    private Utilities.FXMLData windowData;
    private AnchorPane windowRootPane;
    public AnchorPane getRootPane() {
        return windowRootPane;
    }
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

    private UserManager userManager;
    public final UserManager getUserManager() {
        return userManager;
    }

    private DialogsManager dialogsManager;
    public final DialogsManager getDialogsManager() {
        return dialogsManager;
    }

    private AppManager() {}

    public void setUp(Stage stage) {
        curStage = stage;

        mainDisplayManager = new MainDisplayManager(this);
        loginManager = new LogInManager(this);

        userManager = new UserManager(this);

        dialogsManager = new DialogsManager(this);

        windowData = Utilities.loadFXMLWindow(WINDOW_FXML, TITLE, stage);
        windowRootPane = windowData.getRoot(AnchorPane.class);
    }

    public final void loadOnWindow(LoadableFXMLContent content) {
        if (curContent != null) curContent.hide();
        clearWindow();

        curContent = content;
        content.openOn(windowRootPane);
        content.stickToWholeAnchorPane();
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

    public void disable() {
        windowRootPane.setDisable(true);
    }
    public void enable() {
        windowRootPane.setDisable(false);
    }
}
