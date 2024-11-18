package app.managers;

import app.controller.BookCollectionListController;
import app.controller.UserLibraryUIController;
import javafx.scene.layout.StackPane;

@SuppressWarnings({"FieldMayBeFinal", "exports"})
public class UserLibraryManager extends BaseManager {
    
    private static final String USER_LIBRARY_UI = "UserLibraryUI";
    private static final String BOOK_COLLECTION_LIST_FXML = "BookCollectionList";

    private LoadableFXMLContent userLibraryUIFXMLContent;
    private UserLibraryUIController userLibraryUIController;

    private LoadableFXMLContent bookCollectionListPaneFXMLContent;
    private BookCollectionListController bookCollectionListController;

    public UserLibraryManager(AppManager manager) {
        super(manager);

        userLibraryUIFXMLContent = new LoadableFXMLContent(USER_LIBRARY_UI);
        userLibraryUIController = userLibraryUIFXMLContent.getData().getController(UserLibraryUIController.class);
        
        bookCollectionListPaneFXMLContent = new LoadableFXMLContent(BOOK_COLLECTION_LIST_FXML);
        bookCollectionListController = bookCollectionListPaneFXMLContent.getData().getController(BookCollectionListController.class);

        userLibraryUIFXMLContent.setEnableCallback(() -> { onEnable(); });
        userLibraryUIFXMLContent.setDisableCallback(() -> { onDisable(); });
    }

    private void onEnable() {
        bookCollectionListPaneFXMLContent = new LoadableFXMLContent(BOOK_COLLECTION_LIST_FXML);
        bookCollectionListPaneFXMLContent.openOn(userLibraryUIController.bookListPane);
    }

    private void onDisable() {
        bookCollectionListPaneFXMLContent.hide();
    }

    public void openUserLibraryOn(StackPane pane) {
        userLibraryUIFXMLContent.openOn(pane);
    }

    public LoadableFXMLContent getUserLibraryUIFXMLContent() {
        return userLibraryUIFXMLContent;
    }

    public LoadableFXMLContent getBookCollectionListPaneFXMLContent() {
        return bookCollectionListPaneFXMLContent;
    }
}
