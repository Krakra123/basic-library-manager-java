package app.library;

import java.util.ArrayList;
import java.util.List;

import app.data.Book;

public class BookCollection {
    
    private List<Book> bookList;

    public BookCollection() {
        bookList = new ArrayList<>();
    }

    public void add(Book book) {
        bookList.add(book);
    }

    public void remove(Book book) {
        bookList.remove(book);
    }

    public List<Book> get() {
        return bookList;
    }

    public Book search(String type) { //TODO
        throw new RuntimeException();
    }
}
