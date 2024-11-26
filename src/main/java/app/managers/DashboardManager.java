package app.managers;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import app.controller.DashboardsController;
import app.data.Book;
import app.data.BookCollection;
import app.util.AccountsManager;

public class DashboardManager extends BaseManager {
    
	private static final String DASHBOARD_FXML = "DashboardsUI";

    private LoadableFXMLContent dashboardFXMLContent;
    public LoadableFXMLContent getDashboardFXMLContent() {
        return dashboardFXMLContent;
    }
    private DashboardsController dashboardController;
    public DashboardsController getDashboardController() {
        return dashboardController;
    }

    public DashboardManager(AppManager appManager) {
        super(appManager);

        dashboardFXMLContent = new LoadableFXMLContent(DASHBOARD_FXML);
		dashboardController = dashboardFXMLContent.getData().getController(DashboardsController.class);
		
		dashboardFXMLContent.setEnableCallback(() -> { onEnable(); });
    }

    public void update() {
        BookCollection collection = AccountsManager.getBookCollection(manager.getUserManager().getCurrentUser());
        dashboardController.updateTotalBook(collection.items.size());

        String rcat = "";
        int max = -1;
        Map<String, Integer> catMap = new TreeMap<>();
        for (Book book : collection.items) {
            List<String> cats = book.volumeInfo.categories;
            if (cats.isEmpty()) continue;
            
            String key = cats.get(0);
            if (!catMap.containsKey(key)) {
                catMap.put(key, 0);
            }

            catMap.put(key, catMap.get(key) + 1);

            if (catMap.get(key) > max) {
                max = catMap.get(key);
                rcat = key;
            }
        }

        dashboardController.updateCat(rcat);

        // TODO 
    }

    private void onEnable() {
        dashboardController.manager = this;

        update();
    }
}
