package app.managers;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import app.controller.MenuUIController;
import app.data.Book;
import app.data.BookCollection;
import app.managers.BookCollectionHandler.GroupByType;
import app.managers.BookCollectionHandler.SortByType;
import app.util.BookAPI;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.scene.input.KeyCode;

@SuppressWarnings({"FieldMayBeFinal", "unused"})
public class MenuManager extends BaseManager {

    private static final String MAIN_MENU_FXML = "MenuUI";

    private LoadableFXMLContent mainMenuFXMLContent;
    public LoadableFXMLContent getMainMenuFXMLContent() {
        return mainMenuFXMLContent;
    }
    private MenuUIController mainMenuUIController;

    private BookCollectionHandler bookCollectionDisplay;

    @SuppressWarnings("CallToPrintStackTrace")
    public MenuManager(AppManager manager) {
        super(manager);

        mainMenuFXMLContent = new LoadableFXMLContent(MAIN_MENU_FXML);
        mainMenuUIController = mainMenuFXMLContent.getData().getController(MenuUIController.class);

        bookCollectionDisplay = new BookCollectionHandler();

        mainMenuFXMLContent.setEnableCallback(() -> { onEnable(); });
        mainMenuFXMLContent.setDisableCallback(() -> { onDisable(); });

        bookCollectionDisplay.setSaveCallback(() -> { saveBookToAccount(bookCollectionDisplay.getCurrentViewingBook()); });
        bookCollectionDisplay.setUnSaveCallback(() -> { unsaveBook(bookCollectionDisplay.getCurrentViewingBook()); });
        bookCollectionDisplay.setOpenCallback(() -> {
            Book book = bookCollectionDisplay.getCurrentViewingBook();
            manager.getDialogsManager().showConfirmDialog("Open", "Open this link: " + book.accessInfo.webReaderLink + "?", () -> { 
                if (Desktop.isDesktopSupported()) {
                    Desktop desktop = Desktop.getDesktop();
                    try {
                        URI uri = new URI(book.accessInfo.webReaderLink);
                        desktop.browse(uri);
                    } catch (IOException | URISyntaxException e) {
                        e.printStackTrace();
                    }
                }
            });
        });

        InputManager.handleKeyShortcut(KeyCode.ENTER, StateManager.State.MENU, () -> {
            mainMenuUIController.search();
        });
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

    public void saveBookToAccount(Book book) {
        manager.getDialogsManager().showConfirmDialog("Confirm", "Saving this book to your library", () -> { 
            manager.getUserManager().borrowBook(book); 
            bookCollectionDisplay.getDetailsController().update(book);
        });
    }

    public void unsaveBook(Book book) {
        manager.getDialogsManager().showConfirmDialog("Confirm", "Remove this book to your library", () -> { 
            manager.getUserManager().returnBook(book);
            bookCollectionDisplay.getDetailsController().update(book);
        });
    }

    private void updateBookCollectionDisplay(BookCollection collection, GroupByType groupBy, SortByType sortBy) {
        bookCollectionDisplay.update(collection, groupBy, sortBy);
    }

    private void onEnable() {
        bookCollectionDisplay.openOn(mainMenuUIController.contentPane);
        mainMenuUIController.setManager(this);
        StateManager.setState(StateManager.State.MENU);
    }

    private void onDisable() {
        bookCollectionDisplay.hide();
    }
}
