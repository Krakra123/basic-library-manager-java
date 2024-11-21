package app.managers;

import java.util.List;
import java.util.SortedMap;

import app.controller.BookCollectionListController;
import app.controller.BookDetailsDisplayController;
import app.controller.BookItemDisplayController;
import app.controller.BookItemGroupDisplayController;
import app.data.Book;
import app.data.BookCollection;
import javafx.application.Platform;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;

@SuppressWarnings({"exports", "FieldMayBeFinal"})
public class BookCollectionHandler {

    public static enum GroupByType {
        NONE,
        TITLE,
        AUTHOR
    }

    private static final String BOOK_COLLECTION_LIST_FXML = "BookCollectionList";
    private static final String BOOK_ITEM_GROUP_DISPLAY_FXML = "BookItemGroupDisplay";
    private static final String BOOK_ITEM_DISPLAY_FXML = "BookItemDisplay";
    private static final String BOOK_DETAILS_DISPLAY_FXML = "BookDetailsDisplay";
    private static final String BLANK_FXML = "Blank";
    
    private LoadableFXMLContent bookCollectionListPaneFXMLContent;
    public LoadableFXMLContent getBookCollectionListPaneFXMLContent() {
        return bookCollectionListPaneFXMLContent;
    }
    private BookCollectionListController bookCollectionListController;

    private LoadableFXMLContent bookDetailsDisplayFXMLContent;
    public LoadableFXMLContent getBookDetailsDisplayFXMLContent() {
        return bookDetailsDisplayFXMLContent;
    }
    private BookDetailsDisplayController bookDetailsDisplayController;

    private BookCollection bookCollectionData;

    public BookCollectionHandler() {
        bookCollectionListPaneFXMLContent = new LoadableFXMLContent(BOOK_COLLECTION_LIST_FXML);
        bookCollectionListController = bookCollectionListPaneFXMLContent.getData().getController(BookCollectionListController.class);

        bookDetailsDisplayFXMLContent = new LoadableFXMLContent(BOOK_DETAILS_DISPLAY_FXML);
        bookDetailsDisplayController = bookDetailsDisplayFXMLContent.getData().getController(BookDetailsDisplayController.class);
        bookDetailsDisplayController.reset();

        bookDetailsDisplayFXMLContent.openOn(bookCollectionListController.detailsPane);
        bookDetailsDisplayFXMLContent.stickToWholeAnchorPane();

        bookCollectionListPaneFXMLContent.setEnableCallback(() -> { onEnable(); });
    }

    public void update(BookCollection collection, GroupByType groupBy) {
        clear();
        bookCollectionData = collection;
        updateCollectionDisplaying(collection.getBookGroups(groupBy));
    }

    private void updateCollectionDisplaying(SortedMap<String, List<Book>> bookGroups) {
        for (SortedMap.Entry<String, List<Book>> bookGroup : bookGroups.entrySet()) {
            LoadableFXMLContent bookItemGroupDisplayFXMLContent = new LoadableFXMLContent(BOOK_ITEM_GROUP_DISPLAY_FXML);
            BookItemGroupDisplayController bookItemGroupDisplayController = bookItemGroupDisplayFXMLContent.getData().getController(BookItemGroupDisplayController.class);
            
            Platform.runLater(() -> {
                LoadableFXMLContent blankFXMLContent = new LoadableFXMLContent(BLANK_FXML);
                bookCollectionListController.listPane.getChildren().add(blankFXMLContent.getData().root);
                blankFXMLContent.setEnable();

                bookItemGroupDisplayFXMLContent.openOn(blankFXMLContent.getData().getRoot(AnchorPane.class));
                bookItemGroupDisplayFXMLContent.stickToHorizontalAnchorPane();

                bookItemGroupDisplayController.groupLabel.setText(bookGroup.getKey().toUpperCase());
            });

            placeBookListItemOnGrid(bookGroup.getValue(), bookItemGroupDisplayController.contentPane);
        }
    }

    private void placeBookListItemOnGrid(List<Book> bookList, FlowPane pane) {
        for (Book book : bookList) {
            LoadableFXMLContent bookItemDisplayFXMLContent = new LoadableFXMLContent(BOOK_ITEM_DISPLAY_FXML);
            BookItemDisplayController bookItemDisplayController = bookItemDisplayFXMLContent.getData().getController(BookItemDisplayController.class);

            Platform.runLater(() -> {
                LoadableFXMLContent blankFXMLContent = new LoadableFXMLContent(BLANK_FXML);
                pane.getChildren().add(blankFXMLContent.getData().root);
                blankFXMLContent.setEnable();

                bookItemDisplayFXMLContent.openOn(blankFXMLContent.getData().getRoot(AnchorPane.class));
                bookItemDisplayController.update(book);
                bookItemDisplayController.setManager(this);
            });
        }
    }

    public void viewDetails(Book book) {
        if (!bookCollectionData.contains(book)) {
            System.err.println("There's no such book to view details");
        }

        bookDetailsDisplayController.update(book);
    }

    public void openOn(AnchorPane pane) {
        bookCollectionListPaneFXMLContent.openOn(pane);
    }

    public void hide() {
        bookCollectionListPaneFXMLContent.hide();
    }

    public void clear() {
        Platform.runLater(() -> {
            bookCollectionListController.listPane.getChildren().clear();
        });
    }

    private void onEnable() {
        bookCollectionListPaneFXMLContent.stickToWholeAnchorPane();
    }
}
