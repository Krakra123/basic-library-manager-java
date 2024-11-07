package app.controller;

import app.data.Book;

public abstract class ItemBookController {

    protected Book data;

    public void load(Book book) {
        data = book;
    }

    public abstract void update();
}
