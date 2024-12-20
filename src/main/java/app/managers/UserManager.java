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
        this.currentCollection = AccountsManager.getBookCollection(currentUser);
        currentCollection = AccountsManager.getBookCollection(currentUser);
    }

    private BookCollection currentCollection;
    public BookCollection getCurrentCollection() {
        return currentCollection;
    }

    public UserManager(AppManager manager) {
        super(manager);
    }

    public BookCollection getCollection() {
        return AccountsManager.getBookCollection(currentUser);
    }

    public boolean checkBorrowedBook(Book book) {
        return AccountsManager.getBookCollection(currentUser).contains(book);
    }

    public void borrowBook(Book book) {
        if (checkBorrowedBook(book)) return;
        AccountsManager.addBookToAccount(currentUser, book);
        currentCollection.add(book);
    }

    public void returnBook(Book book) {
        if (!checkBorrowedBook(book)) return;
        AccountsManager.removeBook(currentUser, book);
        currentCollection.remove(book);
    }
}
