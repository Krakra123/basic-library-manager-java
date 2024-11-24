package app.util;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.stream.Stream;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import app.data.Book;
import app.data.BookCollection;

@SuppressWarnings("CallToPrintStackTrace")
public class BookAPI {
    private static final String API_BASE_URL = "https://www.googleapis.com/books/v1/volumes";
    private static final String BOOK_ID_SAVE_DIR = "data/books/books-id.txt";
    private static final String BOOK_DATA_SAVE_DIR = "data/books/save/";
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static Book getBook(String id) {
        Book book = new Book();
        
        Path path = Paths.get(BOOK_DATA_SAVE_DIR + id + ".txt");
        if (checkSavedBook(id) && Files.exists(path)) {
            try {
                String json = Files.readString(path);
                objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
                book = objectMapper.readValue(json, Book.class);
            } catch (IOException e) {
                e.printStackTrace();
            }

            return book;
        }

        String url = API_BASE_URL + "/" + id;
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

        saveBook(id, book);

        return book;
    }

    public static BookCollection getBookCollection(String search, int num) {
        search = search.trim().replaceAll("\\s", "+");

        BookCollection collection = new BookCollection();

        String url = API_BASE_URL + "?q=" + search + "&maxResults=" + num;
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

    public static void saveBook(String id, Book book) {
        if (checkSavedBook(id)) {
            return;
        }

        Path path = Paths.get(BOOK_ID_SAVE_DIR);
        try {
            Files.writeString(path, "\n" + id, StandardOpenOption.CREATE, StandardOpenOption.APPEND);

            String json = objectMapper.writeValueAsString(book);
            Path savePath = Path.of(BOOK_DATA_SAVE_DIR + id + ".txt");
            if (Files.notExists(savePath)) {
                Files.writeString(savePath, json, StandardOpenOption.CREATE_NEW);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean checkSavedBook(String id) {
        Path path = Paths.get(BOOK_ID_SAVE_DIR);
        try (Stream<String> lines = Files.lines(path);) {
            List<String> data = lines
                .filter(line -> !line.isBlank())
                .toList();

            for (String s : data) {
                if (id.equals(s)) {
                    return true;
                }
            }

            lines.close();
            
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }

    public static void removeSavedData(String id) {
        Path path = Paths.get(BOOK_ID_SAVE_DIR);
        if (Files.notExists(path)) {
            return;
        }

        try {
            String content = Files.readString(path);
            content = content.replaceAll(id, "");
            content = content.replaceAll("(?m)^[ \\t]*\\r?\\n", "");
            Files.writeString(path, content);

            Path dataPath = Paths.get(BOOK_DATA_SAVE_DIR + id + ".txt");
            Files.delete(dataPath);
        } catch (IOException e) {

        }
    }

    public static void clearSavedData() {
        Path path = Paths.get(BOOK_DATA_SAVE_DIR);
        try {
            if (Files.exists(path) && Files.isDirectory(path)) {
                try (DirectoryStream<Path> entries = Files.newDirectoryStream(path)) {
                    for (Path entry : entries) {
                        Files.delete(entry);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}