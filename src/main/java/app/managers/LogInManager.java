package app.managers;

import java.util.ArrayList;
import java.util.List;

import app.data.UserAccount;

public class LogInManager {

	private static UserAccount noAccount = new UserAccount("siuuuuuuuuuu", "KieuVanTuyen"); // TODO remove hard-code
	private static List<UserAccount> accountList = new ArrayList<UserAccount>();
	
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
	
	public static boolean isUsernameExisted(String username) {
		for (UserAccount account : accountList) {
			if (account.getUsername().equals(username)) {
				return true;
			}
		}
		return false;
	}
}
