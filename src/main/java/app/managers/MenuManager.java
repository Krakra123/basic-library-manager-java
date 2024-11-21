package app.managers;

import app.controller.MenuUIController;
import app.data.BookCollection;
import app.managers.BookCollectionHandler.GroupByType;

@SuppressWarnings({"FieldMayBeFinal"})
public class MenuManager extends BaseManager {

    private static final String MAIN_MENU_FXML = "MenuUI";
    private static final int BOOK_NUMBER_DISPLAY_PER_ROW = 5;

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

    public void updateBookCollectionDisplay(BookCollection collection, GroupByType groupBy) {
        bookCollectionDisplay.update(collection, groupBy);
    }

    private void onEnable() {
        bookCollectionDisplay.openOn(mainMenuUIController.contentPane);
    }

    private void onDisable() {
        bookCollectionDisplay.hide();
    }
}
