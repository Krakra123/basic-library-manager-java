package app.managers;

import app.data.BookCollection;
import app.util.Utilities;
import javafx.scene.Parent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

@SuppressWarnings({"FieldMayBeFinal", "exports"})
public class AppManager {
    
    private static final String WINDOW_FXML = "Blank";
    private static final String TITLE = "Library Manager";

    private Stage curStage;

    private Utilities.FXMLData windowData;
    private StackPane windowRootPane;

    private LoadableFXMLContent curContent;

    private MainDisplayManager mainDisplayManager;
    private LogInManager loginManager;

    private BookCollection collection;

    public AppManager(Stage stage) {
        curStage = stage;
        mainDisplayManager = new MainDisplayManager(this);
        loginManager = new LogInManager(this);

        windowData = Utilities.loadFXMLWindow(WINDOW_FXML, TITLE, stage);
        windowRootPane =  windowData.getRoot(StackPane.class);

        collection = new BookCollection(); 
        // DOSOMETHING
    }

    public BookCollection getCollection() { return collection; }

    public final Stage getStage() {
        return curStage;
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

    public final void loadContent(LoadableFXMLContent content) {
        if (curContent != null) curContent.destroy();
        clearWindow();

        curContent = content;
        content.loadOn(windowRootPane);
    }

    private void clearWindow() {
        windowRootPane.getChildren().clear();
    }
}
