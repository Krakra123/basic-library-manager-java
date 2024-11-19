package app.managers;

import app.controller.MenuController;
import app.data.BookCollection;
import app.managers.BookCollectionHandler.GroupByType;

@SuppressWarnings({"FieldMayBeFinal"})
public class MenuManager extends BaseManager {

    private static final String MAIN_MENU_FXML = "Menu";
    private static final int BOOK_NUMBER_DISPLAY_PER_ROW = 5;

    private LoadableFXMLContent mainMenuFXMLContent;
    public LoadableFXMLContent getMainMenuFXMLContent() {
        return mainMenuFXMLContent;
    }
    private MenuController mainMenuController;

    private BookCollectionHandler bookCollectionDisplay;

    public MenuManager(AppManager manager) {
        super(manager);

        mainMenuFXMLContent = new LoadableFXMLContent(MAIN_MENU_FXML);
        mainMenuController = mainMenuFXMLContent.getData().getController(MenuController.class);

        bookCollectionDisplay = new BookCollectionHandler();

        mainMenuFXMLContent.setEnableCallback(() -> { onEnable(); });
        mainMenuFXMLContent.setDisableCallback(() -> { onDisable(); });
    }

    public void updateBookCollectionDisplay(BookCollection collection, GroupByType groupBy) {
        bookCollectionDisplay.update(collection, BOOK_NUMBER_DISPLAY_PER_ROW, groupBy);
    }

    private void onEnable() {
        bookCollectionDisplay.openOn(mainMenuController.contentPane);
    }

    private void onDisable() {
        bookCollectionDisplay.hide();
    }
}
