package app.managers;

import java.util.List;
import java.util.SortedMap;

import app.controller.BookCollectionListController;
import app.controller.BookItemDisplayController;
import app.controller.BookItemGroupDisplayController;
import app.data.Book;
import app.data.BookCollection;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

@SuppressWarnings({"exports", "FieldMayBeFinal"})
public class BookCollectionHandler {

    public static enum GroupByType {
        TITLE,
        AUTHOR
    }

    private static final String BOOK_COLLECTION_LIST_FXML = "BookCollectionList";
    private static final String BOOK_ITEM_GROUP_DISPLAY_FXML = "BookItemGroupDisplay";
    private static final String BOOK_ITEM_DISPLAY_FXML = "BookItemDisplay";
    
    private LoadableFXMLContent bookCollectionListPaneFXMLContent;
    public LoadableFXMLContent getBookCollectionListPaneFXMLContent() {
        return bookCollectionListPaneFXMLContent;
    }
    private BookCollectionListController bookCollectionListController;

    private BookCollection bookCollectionData;

    public BookCollectionHandler() {
        bookCollectionListPaneFXMLContent = new LoadableFXMLContent(BOOK_COLLECTION_LIST_FXML);
        bookCollectionListController = bookCollectionListPaneFXMLContent.getData().getController(BookCollectionListController.class);

        bookCollectionListPaneFXMLContent.setEnableCallback(() -> { onEnable(); });
    }

    // Optimize
    public void update(BookCollection collection, int numberPerRow, GroupByType groupBy) {
        bookCollectionData = collection;
    
        SortedMap<String, List<Book>> bookGroups = bookCollectionData.getBookGroupsByTitleLetter();
        switch (groupBy) {
            case GroupByType.TITLE -> {
                bookGroups = bookCollectionData.getBookGroupsByTitleLetter();
            }
            case GroupByType.AUTHOR -> {
                bookGroups = bookCollectionData.getBookGroupsByAuthorLetter();
                System.out.println("AA");
            }
            default -> throw new AssertionError();
        }
        updateCollectionDisplaying(numberPerRow, bookGroups);
    }

    private void updateCollectionDisplaying(int numberPerRow, SortedMap<String, List<Book>> bookGroups) {
        for (SortedMap.Entry<String, List<Book>> bookGroup : bookGroups.entrySet()) {
            LoadableFXMLContent bookItemGroupDisplayFXMLContent = new LoadableFXMLContent(BOOK_ITEM_GROUP_DISPLAY_FXML);
            BookItemGroupDisplayController bookItemGroupDisplayController = bookItemGroupDisplayFXMLContent.getData().getController(BookItemGroupDisplayController.class);
            bookItemGroupDisplayFXMLContent.openOn(bookCollectionListController.contentPane);

            bookItemGroupDisplayController.groupLabel.setText(bookGroup.getKey());

            placeBookListItemOnGrid(bookGroup.getValue(), bookItemGroupDisplayController.contentPane, numberPerRow);
        }
    }

    private void placeBookListItemOnGrid(List<Book> bookList, GridPane grid, int numberPerRow) {
        int x = 0, y = 0;
        for (Book book : bookList) {
            LoadableFXMLContent bookItemDisplayFXMLContent = new LoadableFXMLContent(BOOK_ITEM_DISPLAY_FXML);
            BookItemDisplayController bookItemDisplayController = bookItemDisplayFXMLContent.getData().getController(BookItemDisplayController.class);

            grid.add(bookItemDisplayFXMLContent.getData().root, x, y);
            bookItemDisplayController.update(book);
        
            x++;
            if (x >= numberPerRow) {
                x = 0;
                y++;
            }
        }
    }

    public void openOn(Pane pane) {
        bookCollectionListPaneFXMLContent.openOn(pane);
    }

    public void hide() {
        bookCollectionListPaneFXMLContent.hide();
    }

    private void onEnable() {

    }
}
