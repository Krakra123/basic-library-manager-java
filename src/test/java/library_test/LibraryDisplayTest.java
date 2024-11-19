package library_test;

import java.io.IOException;
import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;

import app.data.Book;
import app.data.BookCollection;
import app.managers.AppManager;
import app.managers.MenuManager.GroupByType;
import javafx.stage.Stage;

public class LibraryDisplayTest extends ApplicationTest {
    
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
        collection.add(new Book("", "bbb", "111", LocalDate.now()));
        collection.add(new Book("", "ccc", "222", LocalDate.now()));
        collection.add(new Book("", "ddd", "333", LocalDate.now()));
        collection.add(new Book("", "ddd", "333", LocalDate.now()));
        collection.add(new Book("", "ddd", "333", LocalDate.now()));
        collection.add(new Book("", "ddd", "333", LocalDate.now()));
        collection.add(new Book("", "ddd", "333", LocalDate.now()));
        collection.add(new Book("", "ddd", "333", LocalDate.now()));
        collection.add(new Book("", "eee", "444", LocalDate.now()));
        collection.add(new Book("", "fff", "555", LocalDate.now()));

        appManager.openMainDisplayWindow();

        appManager.getMainDisplayManager().getMenuManager().updateBookCollectionDisplay(collection, GroupByType.TITLE);
    }

    @Test
    public void RunFor10sec() {
        try {
            Thread.sleep(10000);
        } catch (Exception e) {
        }
    }
}
