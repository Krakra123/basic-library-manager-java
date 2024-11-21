package app.data;

import java.util.ArrayList;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.List;

import app.managers.BookCollectionHandler.GroupByType;

@SuppressWarnings("FieldMayBeFinal")
public class BookCollection {
    
    public List<Book> items;

    public BookCollection() {
        items = new ArrayList<>();
    }

    public void add(Book book) {
        if (items.contains(book)) {
            throw new RuntimeException("Cannot add book.");
        }

        items.add(book);
    }

    public void remove(Book book) {
        if (!items.contains(book)) {
            throw new RuntimeException("Cannot remove book.");
        }

        items.remove(book);
    }

    public void clear() {
    	items.clear();
    }

    public boolean contains(Book book) {
        return items.contains(book);
    }

    public SortedMap<String, List<Book>> getBookGroups(GroupByType type) {
        SortedMap<String, List<Book>> bookGroups = new TreeMap<>();

        for (Book book : items) {
            String key = "";
            switch (type) {
                case GroupByType.NONE -> {
                    key = "";
                }
                case GroupByType.TITLE -> {
                    key = String.valueOf(book.volumeInfo.title.charAt(0)).toUpperCase();
                }
                case GroupByType.AUTHOR -> {
                    key = book.volumeInfo.authors.get(0);
                }
                default -> throw new AssertionError();
            }

            if (!bookGroups.containsKey(key)) {
                bookGroups.put(key, new ArrayList<>());
            }
            bookGroups.get(key).add(book);
        }

        return bookGroups;
    }
}
