package library_test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit5.ApplicationTest;

import app.controller.BookCollectionController;
import app.controller.MainPaneController;
import app.data.Book;
import app.library.BookCollection;
import app.util.Utilities;
import javafx.stage.Stage;

public class LibraryDisplayTest extends ApplicationTest {
    
    private Stage testStage;
    private MainPaneController mainPaneController;

    private BookCollection collection = new BookCollection();
	
	@Override
    public void start(Stage stage) throws IOException {
        Object o = Utilities.loadWindow("MainMenuPane", "Menu", stage, testStage);
        mainPaneController = (MainPaneController)o;
    
        collection.add(new Book("a", "b"));
        collection.add(new Book("e", "f"));
        collection.add(new Book("u", "v"));
        collection.add(new Book("u", "v"));
        collection.add(new Book("u", "v"));
        collection.add(new Book("u", "v"));

        try {
            mainPaneController.loadCollection(collection);
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    @Test
    public void RunFor10sec() {

        try {
            Thread.sleep(10000);
        } catch (Exception e) {
        }
    }
}
