package app.managers;

public class BookLibraryManager {
    
    private static final String BOOK_COLLECTION_LIST_FXML = "BookCollectionList";

    private LoadableFXMLContent bookCollectionListFXMLContent;

    public BookLibraryManager() {
        bookCollectionListFXMLContent = new LoadableFXMLContent(BOOK_COLLECTION_LIST_FXML);
    }
}
