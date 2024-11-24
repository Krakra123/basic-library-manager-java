package app.util;

import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import app.data.Account;
import app.data.Account.AccountType;
import app.data.Book;
import app.data.BookCollection;

@SuppressWarnings("CallToPrintStackTrace")
public class AccountsManager {
    
    private static final String ACCOUNTS_DIR = "data/account/accounts.txt";
    private static final String ACCOUNTS_DATA_DIR = "data/account/user-data/";

    public static boolean tryLogin(String username, String password) {
        Account account = findAccount(username);
        if (account == null) return false;

        return account.checkPassword(password);
    }

    public static boolean tryRegister(String username, String password, AccountType type) {
        if (findAccount(username) != null) {
            return false;
        }
        
        registerNewAccount(username, password, type);
        return true;
    }

    public static List<Account> readAccountList() {
        List<Account> accountList = new ArrayList<>();

        Path path = Paths.get(ACCOUNTS_DIR);
        try (Stream<String> lines = Files.lines(path)) {
            List<String> data = lines
                .filter(line -> !line.isBlank())
                .flatMap(line -> Arrays.stream(line.split(" ")))
                .filter(word -> !word.isEmpty())
                .toList();

            int l = data.size();
            for (int i = 0; i < l; i+=3) {
                DataHash usernameHash = new DataHash(Long.parseLong(data.get(i), 10));
                DataHash passwordHash = new DataHash(Long.parseLong(data.get(i + 1), 10));
                AccountType type = AccountType.USER;
                if (data.get(i + 2).charAt(0) != '0') type = AccountType.ADMIN;
            
                accountList.add(new Account(usernameHash, passwordHash, type));
            }

            lines.close();
            
        } catch (IOException e) {
            e.printStackTrace();
        }

        return accountList;
    }

    public static void registerNewAccount(String username, String password, AccountType type) {
        DataHash usernameHash = new DataHash(username);
        DataHash passwordHash = new DataHash(password);

        String line = 
            usernameHash + " " + 
            passwordHash + " " + 
            (type == AccountType.USER ? '0' : '1');

        Path path = Paths.get(ACCOUNTS_DIR);
        try {
            Files.writeString(path, "\n" + line, StandardOpenOption.CREATE, StandardOpenOption.APPEND);

            Path savePath = Paths.get(ACCOUNTS_DATA_DIR + usernameHash + ".txt");
            if (Files.notExists(savePath)) {
                Files.writeString(savePath, "", StandardOpenOption.CREATE_NEW);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Account findAccount(String username) {
        List<Account> accountList = readAccountList();
        for (Account account : accountList) {
            if (account.usernameHash.check(username)) {
                return account;
            }
        }
        
        return null;
    }

    public static BookCollection getBookCollection(Account account) {
        DataHash hash = account.usernameHash;
        return getBookCollection(hash);
    }
    public static BookCollection getBookCollection(String username) { 
        DataHash hash = new DataHash(username);
        return getBookCollection(hash);
    }
    public static BookCollection getBookCollection(DataHash hash) {
        Path path = Paths.get(ACCOUNTS_DATA_DIR + hash + ".txt");
        BookCollection collection = new BookCollection();
        try (Stream<String> lines = Files.lines(path)) {
            List<String> data = lines
                .filter(line -> !line.isBlank())
                .flatMap(line -> Arrays.stream(line.split(" ")))
                .filter(word -> !word.isEmpty())
                .toList();

            int l = data.size(); // FIXME
            for (int i = 0; i < l; i++) {
                collection.add(BookAPI.getBook(data.get(i)));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return collection;
    }

    public static void addBookToAccount(Account account, Book book) {
        DataHash hash = account.usernameHash;
        addBookToAccount(hash, book);
    }
    public static void addBookToAccount(String username, Book book) { 
        DataHash hash = new DataHash(username);
        addBookToAccount(hash, book);
    }
    public static void addBookToAccount(DataHash hash, Book book) {
        Path path = Paths.get(ACCOUNTS_DATA_DIR + hash + ".txt");
        try {
            Files.writeString(path, " " + book.id, StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void removeSavedData(String username) {
        Path path = Paths.get(ACCOUNTS_DIR);
        if (Files.notExists(path)) {
            return;
        }

        try {
            DataHash hash = new DataHash(username);
            String content = Files.readString(path);
            content = content.replaceAll(hash.toString(), "");
            content = content.replaceAll("(?m)^[ \\t]*\\r?\\n", "");
            Files.writeString(path, content);

            Path dataPath = Paths.get(ACCOUNTS_DATA_DIR + hash + ".txt");
            if (Files.exists(dataPath)) {
                Files.delete(dataPath);
            }
        } catch (IOException e) {

        }
    }

    public static void clearSavedData() {
        Path path = Paths.get(ACCOUNTS_DATA_DIR);
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
