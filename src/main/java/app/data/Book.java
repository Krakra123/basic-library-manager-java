package app.data;

public class Book {
    
	private String id;
	private String title;
	private String author;
    public String name;
    public String publisher;
    public String genre;
    public int yearPublished;
    boolean isBorrowed = false;
    
    public Book(String name, String publisher) {
        this.name = name;
        this.publisher = publisher;
    }
    
    public Book(String name, String publisher, String genre, int yearPublished) {
        this.name = name;
        this.publisher = publisher;
        this.genre = genre;
        this.yearPublished = yearPublished;
    }

	public Book(String id, String title, String author, String genre, int yearPublished, boolean b) {
		this.title = title;
		this.id = id;
		this.author = author;
		this.genre = genre;
		this.yearPublished = yearPublished;
		this.isBorrowed = b;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public int getYearPublished() {
		return yearPublished;
	}

	public void setYearPublished(int yearPublished) {
		this.yearPublished = yearPublished;
	}

	public boolean isBorrowed() {
		return isBorrowed;
	}

	public void setBorrowed(boolean isBorrowed) {
		this.isBorrowed = isBorrowed;
	}
}
