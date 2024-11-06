package app.data;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BookDatabase {
    
    private String data;

    private List<Book> books;

    public BookDatabase(String dataDir) throws IOException { 
        StringBuilder content = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(dataDir))) {
            String line;
            while ((line = br.readLine()) != null) {
                content.append(line).append(System.lineSeparator());
            }
        } 
        data = content.toString();
            
        books = new ArrayList<>();
        String[] lines = data.split("\n");
        
        for (String line : lines) {
            if (line.startsWith("*")) {
                String[] parts = line.substring(1).split("-");
                if (parts.length == 2) {
                    Book book = new Book("", "");
                    book.name = parts[0];
                    book.publisher = parts[1];
                    books.add(book);
                }
            }
        }
    }

    public Book GetBook(int index) {
        return books.get(index);
    }
}
