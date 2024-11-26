package app.managers;

import app.interfaces.ICallback;
import app.managers.StateManager.State;
import app.util.Utilities;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

@SuppressWarnings("FieldMayBeFinal")
public class DialogHandler {

    protected final AppManager appManager;

    protected String FXML;
    protected Stage stage;
    public Utilities.FXMLData fxmlData;
    
    protected ICallback confirmCallback;

    protected StateManager.State preState;

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

        InputManager.handleKeyShortcut(KeyCode.ENTER, State.DIALOG, () -> {
            confirm();
        });
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
        preState = StateManager.getState();
        StateManager.setState(State.DIALOG);
    }
    public void onClose() {
        appManager.enable();
        confirmCallback = null;
        StateManager.setState(preState);
    }
}
