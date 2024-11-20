package app.managers;

import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import app.data.Account;
import app.data.PasswordHash;
import app.data.Account.AccountType;

public class AccountsManager {
    
    private static final String ACCOUNTS_SAVE_DIR = "data/accounts.txt";

    private List<Account> accountList;

    public AccountsManager() {
        accountList = new ArrayList<>();
    }

    public void read() {
        Path path = Paths.get(ACCOUNTS_SAVE_DIR);
        try {
            Stream<String> lines = Files.lines(path);

            List<String> data = lines
                .filter(line -> !line.isBlank())
                .flatMap(line -> Arrays.stream(line.split(" ")))
                .filter(word -> !word.isEmpty())
                .toList();

            int l = data.size();
            for (int i = 0; i < l; i+=4) {
                String username = data.get(i);
                PasswordHash passwordHash = new PasswordHash(Long.parseLong(data.get(i + 1), 10), Long.parseLong(data.get(i + 2), 10));
                AccountType type = AccountType.USER;
                if (data.get(i + 3).charAt(0) != '0') type = AccountType.ADMIN;
            
                accountList.add(new Account(username, passwordHash, type));
            }
            
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
        if (checkUsername(username)) {
            return false;
        }
        
        registerNewAccount(username, password, type);
        return true;
    }

    public void registerNewAccount(String username, String password, AccountType type) {
        PasswordHash passHash = new PasswordHash(password);

        String line = 
            username + " " + 
            passHash.fw + " " + 
            passHash.rv + " " + 
            (type == AccountType.USER ? '0' : '1');

        Path path = Paths.get(ACCOUNTS_SAVE_DIR);
        try {
            Files.writeString(path, "\n" + line, StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        } catch (IOException e) {
            e.printStackTrace();
        }

        accountList.add(new Account(username, passHash, type));
    }

    public boolean checkUsername(String username) {
        for (Account account : accountList) {
            if (account.username.equals(username)) {
                return true;
            }
        }
        return false;
    }

    public Account findAccount(String username) throws Exception {
        for (Account account : accountList) {
            if (account.username.equals(username)) {
                return account;
            }
        }
        
        throw new Exception("Username not found");
    }
}
