package app.managers;

import app.data.Account;
import app.data.Book;
import app.data.BookCollection;
import app.util.AccountsManager;

public class UserManager extends BaseManager {

    private Account currentUser;
    public Account getCurrentUser() {
        return currentUser;
    }
    public void setCurrentUser(Account currentUser) {
        this.currentUser = currentUser;
        this.currentCollection = AccountsManager.getBookCollection(currentUser, "A"); // FIXME
    }

    private BookCollection currentCollection;
    public BookCollection getCurrentCollection() {
        return currentCollection;
    }

    public UserManager(AppManager manager) {
        super(manager);
    }

    public BookCollection getCollection() {
        return AccountsManager.getBookCollection(currentUser, "A"); // FIXME
    }

    public boolean checkBorrowedBook(Book book) {
        return currentCollection.contains(book);
    }

    public void borrowBook(Book book) {
        if (checkBorrowedBook(book)) return;
        AccountsManager.addBookToAccount(currentUser, "A", book); // FIXME
        currentCollection.add(book);
    }
}
