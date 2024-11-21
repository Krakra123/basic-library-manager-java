package app.managers;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

import app.data.Book;

public class BookAPI {
    private static final String BASE_URL = "https://www.googleapis.com/books/v1/volumes";
    private static ObjectMapper objectMapper = new ObjectMapper();
    
    public static void getBook(String id) {
        Book book;
        String url = BASE_URL + "/" + id;
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                book = objectMapper.readValue(response.body(), Book.class);
                System.out.println(response.body());
            }
        } catch (IOException | InterruptedException e) {
            System.err.println("Error at getting book id=" + id);
            // e.printStackTrace();
        }
    }
}