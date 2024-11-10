package app.data;

public class UserAccount {

	public String username;
	private String password;
	
	public UserAccount(String username, String password){
		this.username = username;
		this.password = password;
	}

	public boolean checkPassword(String password) {
		return this.password.equals(password);
	}
}
