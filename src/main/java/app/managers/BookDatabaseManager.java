package app.managers;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import app.data.Book;

public class BookDatabaseManager {
    
    // FIXME 
    private List<Book> data;

    public BookDatabaseManager() {
        data = new ArrayList<>();
        data.add(new Book("", "adwaa", "000", LocalDate.now()));
        data.add(new Book("", "qwwqddd", "3wqe33", LocalDate.now()));
        data.add(new Book("", "ddqwdd", "333", LocalDate.now()));
        data.add(new Book("", "ddd", "333", LocalDate.now()));
        data.add(new Book("", "bddqbb", "1eqw11", LocalDate.now()));
        data.add(new Book("", "freff", "55e5", LocalDate.now()));
        data.add(new Book("", "fff", "55c5", LocalDate.now()));
        data.add(new Book("", "ewqeaaa", "4000", LocalDate.now()));
        data.add(new Book("", "aaa", "0040", LocalDate.now()));
        data.add(new Book("", "qwaaa", "00ew0", LocalDate.now()));
        data.add(new Book("", "crqwcc", "2e22", LocalDate.now()));
        data.add(new Book("", "deqwdd", "333", LocalDate.now()));
        data.add(new Book("", "ddd", "333", LocalDate.now()));
        data.add(new Book("", "ffqwf", "55qw5", LocalDate.now()));
        data.add(new Book("", "ffrf", "55q5", LocalDate.now()));
        data.add(new Book("", "atraa", "000", LocalDate.now()));
        data.add(new Book("", "ddwqtd", "33q3", LocalDate.now()));
        data.add(new Book("", "dewdd", "333", LocalDate.now()));
        data.add(new Book("", "cwqcc", "22wq2", LocalDate.now()));
        data.add(new Book("", "ddd", "333xwq", LocalDate.now()));
        data.add(new Book("", "ddd", "dw333", LocalDate.now()));
        data.add(new Book("", "eeqwde", "444ew", LocalDate.now()));
        data.add(new Book("", "fff", "5e55", LocalDate.now()));
    }
}
