package app.managers;

import app.controller.MainDisplayController;
import app.util.Utilities;
import javafx.scene.Parent;

public class MainDisplayManager {
    
    private final String MAIN_DISPLAY_FXML = "MainDisplay";

    private Utilities.FXMLData mainDisplayFXML;
    private Parent mainDisplayRoot;
    private MainDisplayController mainDisplayController;

    private BookCollectionItemManager bookCollectionManager;

    public MainDisplayManager(Utilities.FXMLData data) {
        mainDisplayFXML = data;
        if (mainDisplayFXML.controller instanceof MainDisplayController c) {
            mainDisplayController = c;
        } else {
            throw new RuntimeException("MainDisplay has wrong controller");
        }

        mainDisplayRoot = mainDisplayFXML.root;

        bookCollectionManager = new BookCollectionItemManager();
    }

    public BookCollectionItemManager getBookCollectionManager() {
        return bookCollectionManager;
    }

    public void loadUserLibrary() {
        bookCollectionManager.loadOnParent(mainDisplayController.contentPane);
    }
}
