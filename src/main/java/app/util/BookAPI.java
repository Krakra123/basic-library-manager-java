package app.util;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import app.data.Book;
import app.data.BookCollection;

@SuppressWarnings("CallToPrintStackTrace")
public class BookAPI {
    private static final String BASE_URL = "https://www.googleapis.com/books/v1/volumes";
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static Book getBook(String id) {
        Book book = new Book();

        String url = BASE_URL + "/" + id;
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                String json = response.body();
                objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
                book = objectMapper.readValue(json, Book.class);
            }
        } catch (IOException | InterruptedException e) {
            System.err.println("Error at getting book id=" + id);
            e.printStackTrace();
        }

        return book;
    }

    public static BookCollection getBookCollection(String search, int num) {
        search = search.trim().replaceAll("\\s", "+");

        BookCollection collection = new BookCollection();

        String url = BASE_URL + "?q=" + search + "&maxResults=" + num;
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                String json = response.body();
                objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
                collection = objectMapper.readValue(json, BookCollection.class);
            }
        } catch (IOException | InterruptedException e) {
            System.err.println("Error at searching books");
            e.printStackTrace();
        }

        return collection;
    }
}