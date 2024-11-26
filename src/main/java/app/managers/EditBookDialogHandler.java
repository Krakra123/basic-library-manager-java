package app.managers;

import app.controller.SetBookDialogController;
import app.data.Book;
import app.util.BookAPI;

public class EditBookDialogHandler extends DialogHandler {
    
    public EditBookDialogHandler(AppManager appManager, String FXML) {
        super(appManager, FXML);
    }

    public Book getBook() {
        return fxmlData.getController(SetBookDialogController.class).getBook();
    }

    @Override
    public void confirm() {
        BookAPI.editBook(getBook().id, getBook());
        super.confirm();
    }
}
