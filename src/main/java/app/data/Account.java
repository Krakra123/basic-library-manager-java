package app.data;

import app.util.DataHash;

public class Account {

	public static enum AccountType {
		USER,
		ADMIN
	}

	public DataHash usernameHash;
	public DataHash passwordHash;
	public AccountType type;

	public Account(String username, String password, AccountType type) {
        this.usernameHash = new DataHash(username);
        this.passwordHash = new DataHash(password);
        this.type = type;
    }

	public Account(DataHash usernameHash, DataHash passwordHash, AccountType type) {
        this.usernameHash = usernameHash;
        this.passwordHash = passwordHash;
        this.type = type;
    }

	public boolean checkUsername(String username) {
		username = username.trim();
		return usernameHash.check(username);
	}

    public boolean checkPassword(String password) {
		return passwordHash.check(password);
	}
}
