package app.managers;

import app.data.Book;
import app.data.BookCollection;
import app.managers.BookCollectionHandler.GroupByType;
import app.util.Utilities;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
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

    private AccountsManager accountsManager;
    public final AccountsManager getAccountsManager() {
        return accountsManager;
    }

    public AppManager(Stage stage) {
        curStage = stage;

        accountsManager = new AccountsManager();
        accountsManager.read();

        mainDisplayManager = new MainDisplayManager(this);
        loginManager = new LogInManager(this);

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
    
        BookCollection collection = new BookCollection();
        collection.add(BookAPI.getBook("3KgoyQEACAAJ"));
        collection.add(BookAPI.getBook("Zi02DwAAQBAJ"));
        mainDisplayManager.getMenuManager().updateBookCollectionDisplay(collection, GroupByType.TITLE);
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
