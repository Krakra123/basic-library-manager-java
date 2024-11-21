package app.data;

import java.util.ArrayList;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.List;

@SuppressWarnings("FieldMayBeFinal")
public class BookCollection {
    
    private List<Book> bookList;
    public List<Book> getBookList() {
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

        String titleLetter = String.valueOf(book.volumeInfo.title.charAt(0));
        if (!bookGroupsByTitleLetter.containsKey(titleLetter)) {
            bookGroupsByTitleLetter.put(titleLetter, new ArrayList<>());
        }
        bookGroupsByTitleLetter.get(titleLetter).add(book);

        String authorLetter = ".";
        if (!book.volumeInfo.authors.isEmpty()) authorLetter = String.valueOf(book.volumeInfo.authors.get(0).charAt(0));
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

        String titleLetter = String.valueOf(book.volumeInfo.title.charAt(0));
        if (bookGroupsByTitleLetter.containsKey(titleLetter)) {
            bookGroupsByTitleLetter.get(titleLetter).remove(book);
        }

        String authorLetter = String.valueOf(book.volumeInfo.authors.get(0).charAt(0));
        if (bookGroupsByAuthorLetter.containsKey(authorLetter)) {
            bookGroupsByAuthorLetter.get(authorLetter).remove(book);
        }
    }

    public void clear() {
    	bookList.clear();

        bookGroupsByTitleLetter.clear();
        bookGroupsByAuthorLetter.clear();
    }
}
