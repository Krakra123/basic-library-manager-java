package app.service;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import app.data.Book;

@Service
public class LibraryApiService {

	private final RestTemplate restTemplate;

    public LibraryApiService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String getAllBooks() {
        String url = "https://postman-library-api.glitch.me/books";
        return restTemplate.getForObject(url, String.class);
    }

    public String getBookById(String bookId) {
        String url = "https://postman-library-api.glitch.me/books/" + bookId;
        return restTemplate.getForObject(url, String.class);
    }

    public String addNewBook(String title, String author, String genre, int yearPublished) {
        String url = "https://postman-library-api.glitch.me/books";
        Book book = new Book(title, author, genre, yearPublished);
        return restTemplate.postForObject(url, book, String.class);
    }
}
