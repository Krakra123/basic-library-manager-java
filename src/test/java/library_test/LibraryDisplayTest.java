package library_test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit5.ApplicationTest;

import app.controller.CollectionItemsController;
import app.controller.MainDisplayController;
import app.data.Book;
import app.data.BookCollection;
import app.util.Utilities;
import javafx.stage.Stage;

public class LibraryDisplayTest extends ApplicationTest {
    
    private Stage testStage;

	@Override
    public void start(Stage stage) throws IOException {
        System.out.println("dsaaaaaADASDASDAS");
        Utilities.FXMLData data = Utilities.loadWindow("MainDisplay", "Library", stage, testStage);
    
        BookCollection collection = new BookCollection();
        collection.add(new Book("a", "b"));
        collection.add(new Book("e", "f"));
        collection.add(new Book("u", "v"));
        collection.add(new Book("u", "v"));
        collection.add(new Book("u", "v"));
        collection.add(new Book("u", "v"));

        if (data.controller instanceof MainDisplayController controller) {
            controller.loadCollection(collection);
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
