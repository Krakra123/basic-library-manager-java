package app.managers;

import app.controller.ConfirmDialogController;
import app.controller.SavingBookDialogController;
import app.interfaces.ICallback;

public class DialogsManager extends BaseManager {
    
    private static final String CONFIRM_DIALOG_FXML = "ConfirmDialog";
    private static final String SAVING_BOOK_DIALOG_FXML = "SavingBookDialog";

    public DialogsManager(AppManager manager) {
        super(manager);
    }

    public void showConfirmDialog(ICallback confirmCallback, String title, String detail) {
        DialogHandler dialog = new DialogHandler(manager, CONFIRM_DIALOG_FXML);
        dialog.show(confirmCallback);
        dialog.fxmlData.getController(ConfirmDialogController.class).setManager(dialog);
        dialog.fxmlData.getController(ConfirmDialogController.class).setText(title, detail);
    }

    public void showSavingBookDialog(ICallback savingBookCallback) {
        DialogHandler dialog = new DialogHandler(manager, SAVING_BOOK_DIALOG_FXML);
        dialog.show(savingBookCallback);
        dialog.fxmlData.getController(SavingBookDialogController.class).setManager(dialog);
    }
}
