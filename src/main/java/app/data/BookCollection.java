package app.data;

import java.util.ArrayList;
import java.util.SortedMap;
import java.util.TreeMap;
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

    private SortedMap<String, List<Book>> bookGroupsByTitleLetter;
    public SortedMap<String, List<Book>> getBookGroupsByTitleLetter() {
        return bookGroupsByTitleLetter;
    }

    private SortedMap<String, List<Book>> bookGroupsByAuthorLetter;
    public SortedMap<String, List<Book>> getBookGroupsByAuthorLetter() {
        return bookGroupsByAuthorLetter;
    }

    public BookCollection() {
        bookList = new ArrayList<>();
        bookGroupsByTitleLetter = new TreeMap<>();
        bookGroupsByAuthorLetter = new TreeMap<>();
    }

    public void add(Book book) {
        if (bookList.contains(book)) {
            throw new RuntimeException("Cannot add book.");
        }

        bookList.add(book);

        String titleLetter = String.valueOf(book.title.charAt(0));
        if (!bookGroupsByTitleLetter.containsKey(titleLetter)) {
            bookGroupsByTitleLetter.put(titleLetter, new ArrayList<>());
        }
        bookGroupsByTitleLetter.get(titleLetter).add(book);

        String authorLetter = String.valueOf(book.author.charAt(0));
        if (!bookGroupsByAuthorLetter.containsKey(authorLetter)) {
            bookGroupsByAuthorLetter.put(authorLetter, new ArrayList<>());
        }
        bookGroupsByAuthorLetter.get(authorLetter).add(book);
    }

    public void remove(Book book) {
        if (!bookList.contains(book)) {
            throw new RuntimeException("Cannot remove book.");
        }

        bookList.remove(book);

        String titleLetter = String.valueOf(book.title.charAt(0));
        if (bookGroupsByTitleLetter.containsKey(titleLetter)) {
            bookGroupsByTitleLetter.get(titleLetter).remove(book);
        }

        String authorLetter = String.valueOf(book.author.charAt(0));
        if (bookGroupsByAuthorLetter.containsKey(authorLetter)) {
            bookGroupsByAuthorLetter.get(authorLetter).remove(book);
        }
    }

    public void clear() {
    	bookList.clear();

        bookGroupsByTitleLetter.clear();
        bookGroupsByAuthorLetter.clear();
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
