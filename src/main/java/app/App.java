package app;

import java.io.IOException;
import java.time.LocalDate;

import app.data.Book;
import app.data.BookCollection;
import app.data.Account;
import app.managers.AccountsManager;
import app.managers.AppManager;
import app.managers.BookCollectionHandler.GroupByType;
import app.managers.ShortcutManager;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

@SuppressWarnings("exports")
public class App extends Application {

	private Stage primaryStage;
    private AppManager appManager;

    @Override
    public void start(Stage stage) throws IOException {

        primaryStage = stage;
        appManager = new AppManager(primaryStage);
        
        initTempData();

        appManager.openLoginWindow();
        Scene scene = appManager.getWindowData().scene;
        ShortcutManager.updateScene(scene);

        ShortcutManager.handleCombinationShortcut(KeyCombination.CONTROL_DOWN, KeyCode.W, ()->{stage.close();});

        ShortcutManager.handleKeyShortcut(KeyCode.ENTER, ()-> {appManager.getLoginManager().tryLogin();});


        // appManager.openMainDisplayWindow();

        // appManager.getMainDisplayManager().getMenuManager().updateBookCollectionDisplay(collection, GroupByType.TITLE);

        // appManager.getMainDisplayManager().getUserLibraryManager().updateBookCollectionDisplay(collection, GroupByType.AUTHOR);
    }
    
    public static void main(String[] args) {
        launch(args);
    }

    BookCollection collection;
    private void initTempData() {
        collection = new BookCollection();
        collection.add(new Book("", "adwaa", "000", LocalDate.now()));
        collection.add(new Book("", "qwwqddd", "3wqe33", LocalDate.now()));
        collection.add(new Book("", "ddqwdd", "333", LocalDate.now()));
        collection.add(new Book("", "ddd", "333", LocalDate.now()));
        collection.add(new Book("", "bddqbb", "1eqw11", LocalDate.now()));
        collection.add(new Book("", "freff", "55e5", LocalDate.now()));
        collection.add(new Book("", "fff", "55c5", LocalDate.now()));
        collection.add(new Book("", "ewqeaaa", "4000", LocalDate.now()));
        collection.add(new Book("", "aaa", "0040", LocalDate.now()));
        collection.add(new Book("", "qwaaa", "00ew0", LocalDate.now()));
        collection.add(new Book("", "crqwcc", "2e22", LocalDate.now()));
        collection.add(new Book("", "deqwdd", "333", LocalDate.now()));
        collection.add(new Book("", "ddd", "333", LocalDate.now()));
        collection.add(new Book("", "ffqwf", "55qw5", LocalDate.now()));
        collection.add(new Book("", "ffrf", "55q5", LocalDate.now()));
        collection.add(new Book("", "atraa", "000", LocalDate.now()));
        collection.add(new Book("", "ddwqtd", "33q3", LocalDate.now()));
        collection.add(new Book("", "dewdd", "333", LocalDate.now()));
        collection.add(new Book("", "cwqcc", "22wq2", LocalDate.now()));
        collection.add(new Book("", "ddd", "333xwq", LocalDate.now()));
        collection.add(new Book("", "ddd", "dw333", LocalDate.now()));
        collection.add(new Book("", "eeqwde", "444ew", LocalDate.now()));
        collection.add(new Book("", "fff", "5e55", LocalDate.now()));
    }
}