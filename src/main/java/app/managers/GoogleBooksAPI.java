package app.managers;
import okhttp3.*;
import com.google.gson.*;

public class GoogleBooksAPI {
    private static final String API_KEY = "YOUR_API_KEY";
    private static final String BASE_URL = "https://www.googleapis.com/books/v1/volumes";

    public static String searchBooks(String query) throws Exception {
        OkHttpClient client = new OkHttpClient();

        HttpUrl.Builder urlBuilder = HttpUrl.parse(BASE_URL).newBuilder();
        urlBuilder.addQueryParameter("q", query);
        urlBuilder.addQueryParameter("key", API_KEY);

        Request request = new Request.Builder()
                .url(urlBuilder.build().toString())
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response);
            }
            return response.body().string();
        }
    }
}

