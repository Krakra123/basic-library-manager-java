package app.managers;

import app.controller.BookCollectionListController;
import app.controller.BookItemDisplayController;
import app.data.Book;
import app.data.BookCollection;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;

@SuppressWarnings({"FieldMayBeFinal", "exports"})
public class BookLibraryManager extends BaseManager {
    
    private static final String BOOK_COLLECTION_LIST_FXML = "BookCollectionList";
    private static final String BOOK_ITEM_DISPLAY_FXML = "BookItemDisplay";

    private static final int BOOK_NUMBER_DISPLAY_PER_ROW = 5;

    private LoadableFXMLContent bookCollectionListPaneFXMLContent;
    private BookCollectionListController bookCollectionListController;

    private BookCollection bookCollectionData;

    public BookLibraryManager(AppManager manager) {
        super(manager);

        bookCollectionListPaneFXMLContent = new LoadableFXMLContent(BOOK_COLLECTION_LIST_FXML);
        bookCollectionListController = bookCollectionListPaneFXMLContent.getData().getController(BookCollectionListController.class);
    }

    public void openCollectionListPaneOn(StackPane pane) {
        bookCollectionListPaneFXMLContent.loadOn(pane);

        updateCollectionListLayout(BOOK_NUMBER_DISPLAY_PER_ROW);
    }

    public void updateCollectionList(BookCollection collection) {
        bookCollectionData = collection;
    
        updateCollectionDisplaying();
    }

    private void updateCollectionDisplaying() {
        // TODO update for better performence
        int x = 0, y = 0;
        for (Book book : bookCollectionData.get()) {
            LoadableFXMLContent bookItemDisplayFXMLContent = new LoadableFXMLContent(BOOK_ITEM_DISPLAY_FXML);

            bookCollectionListController.listPane.add( bookItemDisplayFXMLContent.getData().root, x, y);

            bookItemDisplayFXMLContent.getData().getController(BookItemDisplayController.class).update(book);
        
            x++;
            if (x >= BOOK_NUMBER_DISPLAY_PER_ROW) {
                x = 0;
                y++;
            }
        }
    }

    private void updateCollectionListLayout(int number) {
        GridPane grid = bookCollectionListController.listPane;
        grid.getColumnConstraints().clear();

        double percentWidth = 100.0 / number;

        for (int i = 0; i < number; i++) {
            ColumnConstraints colConstraints = new ColumnConstraints();
            colConstraints.setPercentWidth(percentWidth);
            colConstraints.setHgrow(Priority.ALWAYS);
            grid.getColumnConstraints().add(colConstraints);
        }
    }
}
