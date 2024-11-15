package app.data;

public class Book {
    
    public String name;
    public String publisher;
    public String genre;
    public int yearPubished;
    
    public Book(String name, String publisher) {
        this.name = name;
        this.publisher = publisher;
    }
    
    public Book(String name, String publisher, String genre, int yearPublished) {
        this.name = name;
        this.publisher = publisher;
        this.genre = genre;
        this.yearPubished = yearPublished;
    }
}
