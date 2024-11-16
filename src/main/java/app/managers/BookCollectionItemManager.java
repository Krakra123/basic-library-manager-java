package app.managers;

import app.controller.BookCollectionController;
import app.controller.ItemBookController;
import app.controller.ItemBookListController;
import app.data.Book;
import app.data.BookCollection;
import app.util.Utilities;
import javafx.scene.layout.VBox;

public class BookCollectionItemManager extends LoadableFXMLContentManager {
    
    // private final String COLLECTION_FXML = "BookCollection";
    private final String ITEM_BOOK_FXML = "ItemBookList"; // FIXME Change to genral type of book
    
    private VBox collectionRoot;
    private BookCollectionController collectionController;
    private VBox targetRoot;

    private BookCollection collectionData;

    public BookCollectionItemManager() {
        super("BookCollection");

        if (fxmlData.controller instanceof BookCollectionController c) {
            collectionController = c;
        } else {
            throw new RuntimeException("BookCollection has wrong controller");
        }

        if (fxmlData.root instanceof VBox r) {
            collectionRoot = r;
        } else {
            throw new RuntimeException("BookCollection's root must be a VBox");
        }
    }

    public void loadOnParent(VBox target) {
        targetRoot = target;
        targetRoot.getChildren().clear();
        targetRoot.getChildren().add(fxmlData.root);
        System.out.println(targetRoot + " " + fxmlData.root);
    }

    // TODO add more method to optimize the updating
    public void updateCollection(BookCollection collection) {
        collectionData = collection;

        clearCollectionDisplay();
        for (Book book : collectionData.get()) {
            addItemToCollectionDisplay(book);
        }
    }

    private void addItemToCollectionDisplay(Book book) {
        Utilities.FXMLData itemBookFXML = Utilities.loadFXML(ITEM_BOOK_FXML);
        if (itemBookFXML.controller instanceof ItemBookController itemController) {
            // FIXME
            ItemBookListController t = (ItemBookListController)itemController;
            t.load(book);
            t.update();
        } else {
            throw new RuntimeException("ItemBookDisplay has wrong controller");
        }
        
        if (itemBookFXML.root instanceof VBox itemRoot) {
            collectionController.addViews(itemRoot);
        } else {
            throw new RuntimeException("ItemBookDisplay's root must be a VBox");
        }
    }

    private void clearCollectionDisplay() {
        collectionController.clearViews();
    }

    @Override
    public void onEnable() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'onEnable'");
    }

    @Override
    public void onDisable() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'onDisable'");
    }
}
