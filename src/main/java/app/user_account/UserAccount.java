package app.user_account;

public class UserAccount {

	private String username;
	private String password;
	
	public UserAccount(String username, String password){
		this.username = username;
		this.password = password;
	}
	
	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public boolean checkPassword(String password) {
		if (this.password.equals(password)) return true;
		return false;
	}
}