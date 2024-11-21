package app.service;

import org.json.JSONArray;
import org.json.JSONObject;

import app.data.Book;
import app.data.BookCollection;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
public class ApiService {

	private static final String API_URL_KEYWORD = "https://www.googleapis.com/books/v1/volumes?q=";
    private static final String API_KEY = "AIzaSyAZeZEk4esk0OcZSepx8uI-LBxSiLdc8uk";
    
	public void fetchBooks(String query, int totalBooks, BookCollection books) {
	    int fetchedBooks = 0;
	    int batchSize = 40; // Maximum allowed by the API in a single request

	    try {
	        while (fetchedBooks < totalBooks) {
	            // Calculate the number of books to fetch in this batch
	            int remainingBooks = totalBooks - fetchedBooks;
	            int currentBatchSize = Math.min(batchSize, remainingBooks);

	            // Construct API URL with pagination
	            URL url = new URL(API_URL_KEYWORD + query.replace(" ", "+") +
	                              "&startIndex=" + fetchedBooks +
	                              "&maxResults=" + currentBatchSize +
	                              "&key=" + API_KEY);

	            // Fetch response from API
	            String response = fetchResponse(url);

	            // Parse JSON response
	            JSONObject jsonResponse = new JSONObject(response);
	            JSONArray items = jsonResponse.optJSONArray("items");

	            if (items != null) {
	                for (int i = 0; i < items.length(); i++) {
	                    JSONObject item = items.getJSONObject(i);
	                    JSONObject volumeInfo = item.optJSONObject("volumeInfo");

	                    if (volumeInfo != null) {
	                        String title = volumeInfo.optString("title");
	                        JSONArray authorsArray = volumeInfo.optJSONArray("authors");
	                        List<String> authors = new ArrayList<>();
	                        if (authorsArray != null) {
	                            for (int j = 0; j < authorsArray.length(); j++) {
	                                authors.add(authorsArray.getString(j));
	                            }
	                        }
	                        String publisher = volumeInfo.optString("publisher");

	                        // Add book to the library
	                        books.add(new Book(title, authors, publisher));
	                    }
	                }
	            }

	            // Increment fetched books count
	            fetchedBooks += currentBatchSize;

	            // If no more items are returned, break the loop
	            if (items == null || items.length() == 0) {
	                break;
	            }
	        }
	        System.out.println("Fetched " + books.size() + " books for the library.");
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	
	private String fetchResponse(URL url) throws Exception {
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder responseBuilder = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            responseBuilder.append(line);
        }
        reader.close();
        return responseBuilder.toString();
    }
	
	public void displayBooks(BookCollection books) {
        if (books.isEmpty()) {
            System.out.println("The library is empty.");
        } else {
            for (Book book : books.get()) {
                System.out.println(book);
            }
        }
    }
	
	public List<Book> searchBooksByTitle(String title, BookCollection books) {
        List<Book> results = new ArrayList<>();
        for (Book book : books.get()) {
            if (book.getTitle().toLowerCase().contains(title.toLowerCase())) {
                results.add(book);
            }
        }
        return results;
    }
	
	// Get a book by title
    public Book getBook(String title, BookCollection books) {
        for (Book book : books.get()) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                return book;
            }
        }
        System.out.println("Book not found: " + title);
        return null;
    }
}
