package app.managers;

import app.controller.UserLibraryUIController;
import app.data.BookCollection;
import app.managers.BookCollectionHandler.GroupByType;

@SuppressWarnings({"FieldMayBeFinal"})
public class UserLibraryManager extends BaseManager {
    
    private static final String USER_LIBRARY_UI = "UserLibraryUI";
    private static final int BOOK_NUMBER_DISPLAY_PER_ROW = 5;

    private LoadableFXMLContent userLibraryUIFXMLContent;
    public LoadableFXMLContent getUserLibraryUIFXMLContent() {
        return userLibraryUIFXMLContent;
    }
    private UserLibraryUIController userLibraryUIController;

    private BookCollectionHandler bookCollectionDisplay;

    public UserLibraryManager(AppManager manager) {
        super(manager);

        userLibraryUIFXMLContent = new LoadableFXMLContent(USER_LIBRARY_UI);
        userLibraryUIController = userLibraryUIFXMLContent.getData().getController(UserLibraryUIController.class);
        
        bookCollectionDisplay = new BookCollectionHandler();

        userLibraryUIFXMLContent.setEnableCallback(() -> { onEnable(); });
        userLibraryUIFXMLContent.setDisableCallback(() -> { onDisable(); });
    }

    public void updateBookCollectionDisplay(BookCollection collection, GroupByType groupBy) {
        bookCollectionDisplay.update(collection, BOOK_NUMBER_DISPLAY_PER_ROW, groupBy);
    }

    private void onEnable() {
        bookCollectionDisplay.openOn(userLibraryUIController.bookListPane);
    }

    private void onDisable() {
        bookCollectionDisplay.hide();
    }
}
