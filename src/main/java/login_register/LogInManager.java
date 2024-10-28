package login_register;

import user_account.UserAccount;

import java.util.ArrayList;
import java.util.List;

public class LogInManager {

	
	private static UserAccount noAccount = new UserAccount("siuuuuuuuuuu", "KieuVanTuyen");
	private static List<UserAccount> accountList = new ArrayList<UserAccount>();
	
	public LogInManager() {
		
	}
	
	public static UserAccount getAccount(String username) {
		UserAccount returnAccount = noAccount;
		for (int i = 0;i < accountList.size(); i++) {
			if (username.equals(accountList.get(i).getUsername()) ) {
				returnAccount = accountList.get(i);
				break;
			}
		}
		return returnAccount;
	}

	public static UserAccount getNoAccount() {
		return noAccount;
	}
	
	public static void addNewAccount(UserAccount newAccount) {
		accountList.add(newAccount);
	}
	
}
