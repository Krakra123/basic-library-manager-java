package app.data;

public class Account {

	public static enum AccountType {
		USER,
		ADMIN
	}

	public String username;
	public PasswordHash passwordHash;
	public AccountType type;

	public Account(String username, PasswordHash passwordHash, AccountType type) {
        this.username = username;
        this.passwordHash = passwordHash;
        this.type = type;
    }

    public boolean checkPassword(String password) {
		return passwordHash.check(password);
	}
}
