package app.data;

import app.util.DataHash;

public class Account {

	public static enum AccountType {
		USER,
		ADMIN
	}

	public String username;
	public DataHash passwordHash;
	public AccountType type;

	public Account(String username, String password, AccountType type) {
		username = username.trim();
        this.username = username;
        this.passwordHash = new DataHash(password);
        this.type = type;
    }

	public Account(String username, DataHash passwordHash, AccountType type) {
		username = username.trim();
        this.username = username;
        this.passwordHash = passwordHash;
        this.type = type;
    }

	public boolean checkUsername(String username) {
		username = username.trim();
		return this.username.equals(username);
	}

    public boolean checkPassword(String password) {
		return passwordHash.check(password);
	}
}
