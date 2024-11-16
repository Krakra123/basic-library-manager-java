package app.service;

import app.data.Book;
import app.data.Library;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

public class LibraryApiService {
    private static final String BASE_URL = "https://postman-library-api.glitch.me/books";
    private Library library; // Library instance to manage books

    public LibraryApiService(Library library) {
        this.library = library; // Inject the library instance
    }

    // Fetch all books from the API and populate the library
    public void fetchAllBooks() {
        Client client = ClientBuilder.newClient();
        Response response = client.target(BASE_URL)
                .request(MediaType.APPLICATION_JSON)
                .get();

        if (response.getStatus() == 200) {
            String jsonResponse = response.readEntity(String.class);
            ObjectMapper objectMapper = new ObjectMapper();

            try {
                List<Book> books = objectMapper.readValue(jsonResponse, new TypeReference<List<Book>>() {});
                library.clearLibrary(); // Clear existing library data
                books.forEach(library::addBook); // Add fetched books to the library
            } catch (Exception e) {
                throw new RuntimeException("Error parsing books JSON: " + e.getMessage(), e);
            }
        } else {
            throw new RuntimeException("Failed to fetch books: HTTP " + response.getStatus());
        }
    }

    // Add a new book to the API and the library
    public void addBook(String title, String author, String genre, int yearPublished) {
        Client client = ClientBuilder.newClient();

        // JSON payload
        String bookJson = String.format("{\"title\":\"%s\",\"author\":\"%s\",\"genre\":\"%s\",\"yearPublished\":%d}",
                title, author, genre, yearPublished);

        Response response = client.target(BASE_URL)
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(bookJson, MediaType.APPLICATION_JSON));

        if (response.getStatus() == 201) {
            System.out.println("Book added successfully: " + response.readEntity(String.class));
            library.addBook(new Book("generated-id", title, author, genre, yearPublished, false)); // Add to library
        } else {
            throw new RuntimeException("Failed to add book: HTTP " + response.getStatus());
        }
    }

    // Delete a book from the API and the library
    public void deleteBook(String id) {
        Client client = ClientBuilder.newClient();

        Response response = client.target(BASE_URL + "/" + id)
                .request(MediaType.APPLICATION_JSON)
                .delete();

        if (response.getStatus() == 204) { // No Content
            System.out.println("Book deleted successfully.");
            library.removeBookById(id); // Remove from the library
        } else {
            throw new RuntimeException("Failed to delete book: HTTP " + response.getStatus());
        }
    }

    // Update a book in the API and the library
    public void updateBook(String id, String title, String author, String genre, int yearPublished) {
        Client client = ClientBuilder.newClient();

        // JSON payload
        String updatedBookJson = String.format("{\"title\":\"%s\",\"author\":\"%s\",\"genre\":\"%s\",\"yearPublished\":%d}",
                title, author, genre, yearPublished);

        Response response = client.target(BASE_URL + "/" + id)
                .request(MediaType.APPLICATION_JSON)
                .put(Entity.entity(updatedBookJson, MediaType.APPLICATION_JSON));

        if (response.getStatus() == 200) {
            System.out.println("Book updated successfully.");
            Book book = library.findBookById(id);
            if (book != null) {
                book.setTitle(title);
                book.setAuthor(author);
                book.setGenre(genre);
                book.setYearPublished(yearPublished);
            }
        } else {
            throw new RuntimeException("Failed to update book: HTTP " + response.getStatus());
        }
    }
}