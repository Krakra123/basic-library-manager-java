package app.managers;

import app.controller.MenuUIController;
import app.data.BookCollection;
import app.managers.BookCollectionHandler.GroupByType;
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

    public void search(String search, GroupByType groupBy) {

        Task<BookCollection> task = new Task<>() {
            @Override
            protected BookCollection call() throws Exception {
                return BookAPI.getBookCollection(search, 10);
            }
        };

        task.setOnSucceeded(event -> {
            updateBookCollectionDisplay(task.getValue(), groupBy);
            mainMenuUIController.unlockSearch();
        });

        task.setOnRunning(event -> {
            mainMenuUIController.lockSearch();
        });

        task.setOnFailed(event -> {
            mainMenuUIController.unlockSearch();
        });

        Thread thread = new Thread(task);
        thread.setDaemon(true);
        thread.start();
    }

    private void updateBookCollectionDisplay(BookCollection collection, GroupByType groupBy) {
        bookCollectionDisplay.update(collection, groupBy);
    }

    private void onEnable() {
        bookCollectionDisplay.openOn(mainMenuUIController.contentPane);
        mainMenuUIController.setManager(this);
    }

    private void onDisable() {
        bookCollectionDisplay.hide();
    }
}
