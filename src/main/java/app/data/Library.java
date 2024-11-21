package app.data;

import java.util.ArrayList;
import java.util.List;

import app.service.LibraryApiService;

public class Library {

	private BookCollection listOfBooks = new BookCollection();
	private List<Member> listOfMembers = new ArrayList<Member>();
	LibraryApiService service;
	
	public Library() {
		this.service = new LibraryApiService(this);
	}
	
	public void clearLibrary() {
        listOfBooks.clear();
    }
	
	public void addBook(Book book) {
        listOfBooks.add(book);
    }
	
	public void removeBookById(String id) {
		listOfBooks.removeBookById(id);
	}
	
	public Book findBookById(String id) {
		return listOfBooks.findBookById(id);
	}
	
	@Override
	public String toString() {
		return listOfBooks.toString();
	}
	
	public void fetch() {
		service.fetchAllBooks();
	}
	
	
}
