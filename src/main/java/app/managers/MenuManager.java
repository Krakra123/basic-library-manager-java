package app.managers;

import app.controller.MenuUIController;
import app.data.BookCollection;
import app.managers.BookCollectionHandler.GroupByType;
import app.managers.BookCollectionHandler.SortByType;
import app.util.BookAPI;
import javafx.application.Platform;
import javafx.concurrent.Task;

@SuppressWarnings({"FieldMayBeFinal", "unused"})
public class MenuManager extends BaseManager {

    private static final String MAIN_MENU_FXML = "MenuUI";

    private LoadableFXMLContent mainMenuFXMLContent;
    public LoadableFXMLContent getMainMenuFXMLContent() {
        return mainMenuFXMLContent;
    }
    private MenuUIController mainMenuUIController;

    private BookCollectionHandler bookCollectionDisplay;

    public MenuManager(AppManager manager) {
        super(manager);

        mainMenuFXMLContent = new LoadableFXMLContent(MAIN_MENU_FXML);
        mainMenuUIController = mainMenuFXMLContent.getData().getController(MenuUIController.class);

        bookCollectionDisplay = new BookCollectionHandler();

        mainMenuFXMLContent.setEnableCallback(() -> { onEnable(); });
        mainMenuFXMLContent.setDisableCallback(() -> { onDisable(); });
    }

    public void search(String search, GroupByType groupBy, SortByType sortBy) {

        Task<BookCollection> task = new Task<>() {
            @Override
            protected BookCollection call() throws Exception {
                BookCollection collection = BookAPI.getBookCollection(search, 30);
                return collection;
            }
        };

        task.setOnSucceeded(event -> {
            Platform.runLater(() -> {
                updateBookCollectionDisplay(task.getValue(), groupBy, sortBy);
                
                mainMenuUIController.unlockSearch();
                bookCollectionDisplay.unlock();
            });
        });

        task.setOnRunning(event -> {
            Platform.runLater(() -> {
                mainMenuUIController.lockSearch();
                bookCollectionDisplay.lock();
            });
        });

        task.setOnFailed(event -> {
            Platform.runLater(() -> {
                mainMenuUIController.unlockSearch();
                bookCollectionDisplay.unlock();
            });
        });

        Thread thread = new Thread(task);
        thread.start();
    }

    private void updateBookCollectionDisplay(BookCollection collection, GroupByType groupBy, SortByType sortBy) {
        bookCollectionDisplay.update(collection, groupBy, sortBy);
    }

    private void onEnable() {
        bookCollectionDisplay.openOn(mainMenuUIController.contentPane);
        mainMenuUIController.setManager(this);
    }

    private void onDisable() {
        bookCollectionDisplay.hide();
    }
}
