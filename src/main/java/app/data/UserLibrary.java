package app.data;

import java.util.List;

public class UserLibrary {
    private BookContainer bookList;

    public void Borrow(Book book) { //TODO
        bookList.add(book);
    }

    public void Return(Book book) { //TODO implement more
        bookList.remove(book);
    }

    public void Edit(Book book) {
        throw new RuntimeException();
    }

    public List<Book> Get() {
        return bookList.get();
    }
}
