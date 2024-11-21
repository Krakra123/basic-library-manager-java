package app.managers;

public class BaseManager {
    
    protected final AppManager manager;

    public BaseManager(AppManager appManager) {
        manager = appManager;
    } 

    public final AppManager getManager() {
        return manager;
    }
}
