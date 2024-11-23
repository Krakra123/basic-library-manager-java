package app.data;

import java.util.ArrayList;
import java.util.List;

public class Account {

	public static enum AccountType {
		USER,
		ADMIN
	}

	public DataHash usernameHash;
	public DataHash passwordHash;
	public AccountType type;
	public ArrayList<BookCollection> AccountLibrary;

	public Account(String username, String password, AccountType type) {
        this.usernameHash = new DataHash(username);
        this.passwordHash = new DataHash(password);
        this.type = type;
        this.AccountLibrary = new ArrayList<BookCollection>();
    }

	public Account(DataHash usernameHash, DataHash passwordHash, AccountType type) {
        this.usernameHash = usernameHash;
        this.passwordHash = passwordHash;
        this.type = type;
        this.AccountLibrary = new ArrayList<BookCollection>();
    }

	public boolean checkUsername(String username) {
		username = username.trim();
		return usernameHash.check(username);
	}

    public boolean checkPassword(String password) {
		return passwordHash.check(password);
	}
    
    public void deleteAccount(Account account) {
    	if (this.type == AccountType.ADMIN && account.type == AccountType.USER) {
    		account = null;
    	}
    }
    
    public void addNewCollection() {
    	AccountLibrary.add(new BookCollection());
    	// add to Account.txt ?
    }
    
    public void addBookToCollection(Book book, BookCollection collection) {
    	collection.add(book);
    }
}
