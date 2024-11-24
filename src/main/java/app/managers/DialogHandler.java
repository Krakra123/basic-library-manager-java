package app.managers;

import app.interfaces.ICallback;
import app.util.Utilities;
import javafx.stage.Stage;

@SuppressWarnings("FieldMayBeFinal")
public class DialogHandler {

    private final AppManager appManager;

    private String FXML;
    private Stage stage;
    public Utilities.FXMLData fxmlData;
    
    private ICallback confirmCallback;

    public DialogHandler(AppManager appManager, String FXML) {
        this.appManager = appManager;
        this.FXML = FXML;
        stage = new Stage();
    }

    @SuppressWarnings("unused")
    public void show(ICallback confirmCallback) {
        fxmlData = Utilities.loadFXMLWindow(FXML, "", stage);
        stage.setResizable(false);
        stage.setOnCloseRequest(event -> { onClose(); });

        this.confirmCallback = confirmCallback;

        onShow();
    }

    public void confirm() {
        confirmCallback.Call();
        stage.close();
        onClose();
    }
    public void cancel() {
        stage.close();
        onClose();
    }
    
    public void onShow() {
        appManager.disable();
    }
    public void onClose() {
        appManager.enable();
        confirmCallback = null;
    }
}
