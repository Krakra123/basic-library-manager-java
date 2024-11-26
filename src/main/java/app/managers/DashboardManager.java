package app.managers;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import app.controller.DashboardsController;
import app.data.Book;
import app.data.BookCollection;
import app.managers.BookCollectionHandler.GroupByType;
import app.managers.BookCollectionHandler.SortByType;
import app.util.AccountsManager;

@SuppressWarnings({"FieldMayBeFinal"})
public class DashboardManager extends BaseManager {
    
	private static final String DASHBOARD_FXML = "DashboardsUI";

    private LoadableFXMLContent dashboardFXMLContent;
    public LoadableFXMLContent getDashboardFXMLContent() {
        return dashboardFXMLContent;
    }
    private DashboardsController dashboardController;
    public DashboardsController getDashboardController() {
        return dashboardController;
    }

    private BookCollectionHandler bookCollectionDisplay;

    private String favCat = "";
    private BookCollection recommends = new BookCollection();

    @SuppressWarnings("CallToPrintStackTrace")
    public DashboardManager(AppManager appManager) {
        super(appManager);

        dashboardFXMLContent = new LoadableFXMLContent(DASHBOARD_FXML);
		dashboardController = dashboardFXMLContent.getData().getController(DashboardsController.class);
		
        bookCollectionDisplay = new BookCollectionHandler(false);

		dashboardFXMLContent.setEnableCallback(() -> { onEnable(); });
        dashboardFXMLContent.setDisableCallback(() -> { onDisable(); });

        bookCollectionDisplay.setSaveCallback(() -> { saveBookToAccount(bookCollectionDisplay.getCurrentViewingBook()); });
        bookCollectionDisplay.setUnSaveCallback(() -> { unsaveBook(bookCollectionDisplay.getCurrentViewingBook()); });
        bookCollectionDisplay.setOpenCallback(() -> {
            Book book = bookCollectionDisplay.getCurrentViewingBook();
            manager.getDialogsManager().showConfirmDialog("Open", "Open this link: " + book.accessInfo.webReaderLink + "?", () -> { 
                if (Desktop.isDesktopSupported()) {
                    Desktop desktop = Desktop.getDesktop();
                    try {
                        // Create URI object and open the URL in the default browser
                        URI uri = new URI(book.accessInfo.webReaderLink);
                        desktop.browse(uri);
                    } catch (IOException | URISyntaxException e) {
                        e.printStackTrace();
                    }
                }
            });
        });
    }

    public void update() {
        BookCollection collection = AccountsManager.getBookCollection(manager.getUserManager().getCurrentUser());
        dashboardController.updateTotalBook(collection.items.size());

        favCat = "";
        int max = -1;
        Map<String, Integer> catMap = new TreeMap<>();
        for (Book book : collection.items) {
            List<String> cats = book.volumeInfo.categories;
            if (cats.isEmpty()) continue;
            
            String key = cats.get(0);
            if (!catMap.containsKey(key)) {
                catMap.put(key, 0);
            }

            catMap.put(key, catMap.get(key) + 1);

            if (catMap.get(key) > max) {
                max = catMap.get(key);
                favCat = key;
            }
        }

        dashboardController.updateCat(favCat);
    }

    public void apply() {
        updateBookCollectionDisplay(recommends, GroupByType.NONE, SortByType.ASCENDING);
    }

    public void updateBookCollectionDisplay(BookCollection collection, GroupByType groupBy, SortByType sortBy) {
        bookCollectionDisplay.update(collection, groupBy, sortBy);
    }

    public void saveBookToAccount(Book book) {
        manager.getDialogsManager().showConfirmDialog("Confirm", "Saving this book to your library", () -> { 
            manager.getUserManager().borrowBook(book); 
            bookCollectionDisplay.getDetailsController().update(book);
            apply();
        });
    }

    public void unsaveBook(Book book) {
        manager.getDialogsManager().showConfirmDialog("Confirm", "Remove this book to your library", () -> { 
            manager.getUserManager().returnBook(book);
            bookCollectionDisplay.getDetailsController().update(book);
            apply();
        });
    }

    private void onEnable() {
        bookCollectionDisplay.openOn(dashboardController.bookListPane);
        dashboardController.manager = this;
        update();
        setUpRecommend();
        apply();
    }

    private void setUpRecommend() {
        if (favCat.isEmpty()) return;
        recommends = new BookCollection();
        for (Book book : manager.getUserManager().getCurrentCollection().items) {
            if (book.volumeInfo.categories.contains(favCat)) {
                recommends.add(book);
            }
        }
    }

    private void onDisable() {
        bookCollectionDisplay.hide();
    }
}
