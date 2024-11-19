package app;

import java.io.IOException;
import java.time.LocalDate;

import app.data.Book;
import app.data.BookCollection;
import app.data.UserAccount;
import app.managers.AppManager;
import app.managers.BookCollectionHandler.GroupByType;
import javafx.application.Application;
import javafx.stage.Stage;

@SuppressWarnings("exports")
public class App extends Application {

	private Stage primaryStage;
    private AppManager appManager;

    @Override
    public void start(Stage stage) throws IOException {

        primaryStage = stage;
        appManager = new AppManager(primaryStage);

        // appManager.getLoginManager().addAccount(new UserAccount("1", "1"));

        // appManager.openLoginWindow();

        BookCollection collection = new BookCollection();
        collection.add(new Book("", "aaa", "000", LocalDate.now()));
        collection.add(new Book("", "ddd", "333", LocalDate.now()));
        collection.add(new Book("", "ddd", "333", LocalDate.now()));
        collection.add(new Book("", "ddd", "333", LocalDate.now()));
        collection.add(new Book("", "bbb", "111", LocalDate.now()));
        collection.add(new Book("", "fff", "555", LocalDate.now()));
        collection.add(new Book("", "fff", "555", LocalDate.now()));
        collection.add(new Book("", "aaa", "000", LocalDate.now()));
        collection.add(new Book("", "aaa", "000", LocalDate.now()));
        collection.add(new Book("", "aaa", "000", LocalDate.now()));
        collection.add(new Book("", "ccc", "222", LocalDate.now()));
        collection.add(new Book("", "ddd", "333", LocalDate.now()));
        collection.add(new Book("", "ddd", "333", LocalDate.now()));
        collection.add(new Book("", "fff", "555", LocalDate.now()));
        collection.add(new Book("", "fff", "555", LocalDate.now()));
        collection.add(new Book("", "aaa", "000", LocalDate.now()));
        collection.add(new Book("", "ddd", "333", LocalDate.now()));
        collection.add(new Book("", "ddd", "333", LocalDate.now()));
        collection.add(new Book("", "ccc", "222", LocalDate.now()));
        collection.add(new Book("", "ccc", "222", LocalDate.now()));
        collection.add(new Book("", "ddd", "333", LocalDate.now()));
        collection.add(new Book("", "ddd", "333", LocalDate.now()));
        collection.add(new Book("", "eee", "444", LocalDate.now()));
        collection.add(new Book("", "fff", "555", LocalDate.now()));

        appManager.openMainDisplayWindow();

        appManager.getMainDisplayManager().getMenuManager().updateBookCollectionDisplay(collection, GroupByType.TITLE);

        appManager.getMainDisplayManager().getUserLibraryManager().updateBookCollectionDisplay(collection, GroupByType.AUTHOR);
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}