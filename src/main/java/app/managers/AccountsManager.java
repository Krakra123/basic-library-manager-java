package app.managers;

import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import app.data.Account;
import app.data.DataHash;
import app.data.Account.AccountType;

@SuppressWarnings("CallToPrintStackTrace")
public class AccountsManager {
    
    private static final String ACCOUNTS_SAVE_DIR = "data/accounts.txt";

    private final List<Account> accountList;

    public AccountsManager() {
        accountList = new ArrayList<>();
    }

    public void read() {
        Path path = Paths.get(ACCOUNTS_SAVE_DIR);
        try (Stream<String> lines = Files.lines(path);) {
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
    }

    public boolean tryLogin(String username, String password) {
        try {
            Account account = findAccount(username);
            if (account.checkPassword(password)) {
                return true;
            }
        } catch (Exception e) {
            return false;
        }

        return false;
    }

    public boolean tryRegister(String username, String password, AccountType type) {
        if (checkHasUsername(username)) {
            return false;
        }
        
        registerNewAccount(username, password, type);
        return true;
    }

    public void registerNewAccount(String username, String password, AccountType type) {
        DataHash usernameHash = new DataHash(username);
        DataHash passwordHash = new DataHash(password);

        String line = 
            usernameHash + " " + 
            passwordHash + " " + 
            (type == AccountType.USER ? '0' : '1');

        Path path = Paths.get(ACCOUNTS_SAVE_DIR);
        try {
            Files.writeString(path, "\n" + line, StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        } catch (IOException e) {
            e.printStackTrace();
        }

        accountList.add(new Account(usernameHash, passwordHash, type));
    }

    public boolean checkHasUsername(String username) {
        for (Account account : accountList) {
            if (account.usernameHash.check(username)) {
                return true;
            }
        }
        return false;
    }

    public Account findAccount(String username) throws Exception {
        for (Account account : accountList) {
            if (account.usernameHash.check(username)) {
                return account;
            }
        }
        
        throw new Exception("Username not found");
    }
}
