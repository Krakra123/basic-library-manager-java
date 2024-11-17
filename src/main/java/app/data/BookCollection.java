package app.data;

import java.util.ArrayList;
import java.util.List;

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

    public void removeBookById(String id) {
        for (Book book : bookList) {
        	if (book.getId().equals(id)) {
        		bookList.remove(book);
        	}
        }
    }
    
    public Book findBookById(String id) {
        for (Book book : bookList) {
        	if (book.getId().equals(id)) {
        		return book;
        	}
        }
        return null;
    }
    public List<Book> get() {
        return bookList;
    }

    public Book search(String type) { //TODO
        throw new RuntimeException();
    }
    
    public void clear() {
    	bookList.clear();
    }
}
