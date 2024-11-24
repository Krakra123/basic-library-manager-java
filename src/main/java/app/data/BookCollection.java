package app.data;

import java.text.Normalizer;
import java.util.ArrayList;
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

    public TreeMap<String, List<Book>> getBookGroups(GroupByType type) {
        TreeMap<String, List<Book>> bookGroups = new TreeMap<>();

        for (Book book : items) {
            String key = "";
            switch (type) {
                case GroupByType.NONE -> {
                    key = "";
                }
                case GroupByType.TITLE -> {
                    String title = book.volumeInfo.title;
                    String normalized = Normalizer.normalize(title, Normalizer.Form.NFD);
                    title = normalized.replaceAll("\\p{M}", "");
                    title = title.replace("Ă", "A");
                    title = title.replace("Â", "A");
                    title = title.replace("Đ", "D");
                    title = title.replace("Ê", "E");
                    title = title.replace("Ô", "O");
                    title = title.replace("Ơ", "O");
                    title = title.replace("Ư", "U");
                    key = String.valueOf(title.charAt(0)).toUpperCase();
                }
                case GroupByType.AUTHOR -> {
                    String author = ".";
                    if (!book.volumeInfo.authors.isEmpty()) author = book.volumeInfo.authors.get(0);
                    key = author.toUpperCase();
                }
                case GroupByType.CATEGORY -> {
                    String author = ".";
                    if (!book.volumeInfo.categories.isEmpty()) author = book.volumeInfo.categories.get(0);
                    key = author.toUpperCase();
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
