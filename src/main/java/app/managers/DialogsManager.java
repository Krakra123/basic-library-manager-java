package app.managers;

import app.controller.SavingBookDialogController;
import app.interfaces.ICallback;

public class DialogsManager extends BaseManager {
    
    private static final String SAVING_BOOK_DIALOG_FXML = "SavingBookDialog";

    public DialogsManager(AppManager manager) {
        super(manager);
    }

    public void showSavingBookDialog(ICallback savingBookCallback) {
        DialogHandler dialog = new DialogHandler(manager, SAVING_BOOK_DIALOG_FXML);
        dialog.show(savingBookCallback);
        dialog.fxmlData.getController(SavingBookDialogController.class).setManager(dialog);
    }
}
