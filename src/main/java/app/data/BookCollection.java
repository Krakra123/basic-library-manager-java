package app.data;

import java.util.ArrayList;
import java.util.List;

import app.service.ApiService;

public class BookCollection {
    
    private List<Book> bookList;
    private ApiService service = new ApiService();
    
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
    
    public int size() {
		return bookList.size();
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
    
    @Override 
    public String toString() {
    	return bookList.toString();
    }

	public boolean isEmpty() {
		return (bookList.isEmpty());
	}
	
	public void fetch(String query, int total) {
		this.service.fetchBooks(query, total, this);
	}
	
	public static void main(String[] args) {
		BookCollection books = new BookCollection();
		books.fetch("Java programming", 10);
		System.out.println(books);
	}
}
