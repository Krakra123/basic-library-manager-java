package app.managers;

import javafx.scene.layout.StackPane;

@SuppressWarnings({"FieldMayBeFinal", "exports"})
public class BookLibraryManager extends BaseManager {
    
    private static final String BOOK_COLLECTION_LIST_FXML = "BookCollectionList";

    private LoadableFXMLContent bookCollectionDisplayPaneFXMLContent;

    public BookLibraryManager(AppManager manager) {
        super(manager);

        bookCollectionDisplayPaneFXMLContent = new LoadableFXMLContent(BOOK_COLLECTION_LIST_FXML);
    }

    public void loadOn(StackPane pane) {
        bookCollectionDisplayPaneFXMLContent.loadOn(pane);
    }
}
