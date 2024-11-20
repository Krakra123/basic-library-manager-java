package app.managers;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class GoogleBooksAPI {
    private static final String API_KEY = "YOUR_API_KEY";
    private static final String BASE_URL = "https://www.googleapis.com/books/v1/volumes";

    public static void main(String[] args) throws IOException, InterruptedException {
        String query = "java programming";
        String filter = "ebooks"; // Ví dụ: lọc kết quả chỉ là ebooks

        String url = String.format("%s?q=%s&filter=%s&key=%s", BASE_URL, query, filter, API_KEY);

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 200) {
            System.out.println(response.body());
        } else {
            System.out.println("Error: " + response.statusCode());
        }
    }
}