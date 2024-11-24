package app.managers;

import app.controller.UserLibraryUIController;
import app.data.BookCollection;
import app.managers.BookCollectionHandler.GroupByType;
import app.managers.BookCollectionHandler.SortByType;

@SuppressWarnings({"FieldMayBeFinal"})
public class UserLibraryManager extends BaseManager {
    
    private static final String USER_LIBRARY_UI = "UserLibraryUI";

    private LoadableFXMLContent userLibraryFXMLContent;
    public LoadableFXMLContent getUserLibraryFXMLContent() {
        return userLibraryFXMLContent;
    }
    private UserLibraryUIController userLibraryUIController;

    private BookCollectionHandler bookCollectionDisplay;

    private GroupByType groupBy = GroupByType.NONE;
    private SortByType sortBy = SortByType.ASCENDING;

    public UserLibraryManager(AppManager manager) {
        super(manager);

        userLibraryFXMLContent = new LoadableFXMLContent(USER_LIBRARY_UI);
        userLibraryUIController = userLibraryFXMLContent.getData().getController(UserLibraryUIController.class);
        
        bookCollectionDisplay = new BookCollectionHandler();

        userLibraryFXMLContent.setEnableCallback(() -> { onEnable(); });
        userLibraryFXMLContent.setDisableCallback(() -> { onDisable(); });
    }

    public void apply(GroupByType groupBy, SortByType sortBy) {
        this.groupBy = groupBy;
        this.sortBy = sortBy;
        updateBookCollectionDisplay(manager.getUserManager().getCollection(), groupBy, sortBy);
    }

    public void updateBookCollectionDisplay(BookCollection collection, GroupByType groupBy, SortByType sortBy) {
        bookCollectionDisplay.update(collection, groupBy, sortBy);
    }

    private void onEnable() {
        bookCollectionDisplay.openOn(userLibraryUIController.bookListPane);

        userLibraryUIController.setManager(this);
        updateBookCollectionDisplay(manager.getUserManager().getCollection(), groupBy, sortBy);
    }

    private void onDisable() {
        bookCollectionDisplay.hide();
    }
}
