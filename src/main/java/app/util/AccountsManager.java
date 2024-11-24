package app.util;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
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

    public static void creatingSavingFiles()
    {
        try {
            Path savePath = Path.of(ACCOUNTS_DIR);
            Files.createDirectories(savePath.getParent());
            if (Files.notExists(savePath)) {
                Files.createFile(savePath);
            }
    
            Path dataPath = Path.of(ACCOUNTS_DATA_DIR);
            Files.createDirectories(dataPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

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
        creatingSavingFiles();
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
        creatingSavingFiles();

        List<Account> accountList = readAccountList();
        for (Account account : accountList) {
            if (account.usernameHash.check(username)) {
                return account;
            }
        }
        
        return null;
    }

    public static BookCollection getBookCollection(Account account, String collection) {
        return getBookCollection(account.usernameHash, new DataHash(collection));
    }
    public static BookCollection getBookCollection(String username, String collection) { 
        return getBookCollection(new DataHash(username), new DataHash(collection));
    }
    public static BookCollection getBookCollection(DataHash hash, DataHash collectionHash) {
        Path path = Paths.get(ACCOUNTS_DATA_DIR + hash + ".txt");
        BookCollection collection = new BookCollection();
        try (Stream<String> lines = Files.lines(path)) {
            List<String> data = lines
                .filter(line -> !line.isBlank())
                .filter(word -> !word.isEmpty())
                .toList();

            for (String s : data) {
                String[] ss = s.split("\\s+");
                String cHash = ss[0];
                if (collectionHash.toString().equals(cHash)) {
                    int l = ss.length;
                    for (int i = 1; i < l; i++) {
                        collection.add(BookAPI.getBook(ss[i]));
                    }
                }
            }

            lines.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return collection;
    }

    public static void addBookToAccount(Account account, String collection, Book book) {
        addBookToAccount(account.usernameHash, new DataHash(collection), book);
    }
    public static void addBookToAccount(String username, String collection, Book book) { 
        addBookToAccount(new DataHash(username), new DataHash(collection), book);
    }
    public static void addBookToAccount(DataHash usernameHash, DataHash collectionHash, Book book) {
        creatingSavingFiles();
        Path path = Paths.get(ACCOUNTS_DATA_DIR + usernameHash + ".txt");
        try (Stream<String> lines = Files.lines(path)) {
            List<String> data = lines
                .filter(line -> !line.isBlank())
                .filter(word -> !word.isEmpty())
                .toList();
            data = new ArrayList<>(data);

            int l = data.size();
            for (int i = 0; i <= l; i++) {
                if (i >= l) {
                    data.add(collectionHash.toString() + " " + book.id);
                    break;
                }

                String[] ss = data.get(i).split("\\s+");
                String cHash = ss[0];
                if (collectionHash.toString().equals(cHash)) {
                    data.set(i, data.get(i) + " " + book.id);
                    break;
                }
            }

            Files.write(path, data);

            lines.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void removeSavedData(String username) {
        creatingSavingFiles();
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
        creatingSavingFiles();
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
