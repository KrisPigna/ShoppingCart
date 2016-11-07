package Database;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import Models.Account;

public class UserDB {

	private List<Account> accountList;
	
	public UserDB() {
		accountList = new LinkedList<Account>();
	}
	
	public void add(Account key) {
		accountList.add(key);
	}
	
	public List<Account> getAccountList() {
		return accountList;
	}
}
