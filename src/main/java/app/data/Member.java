package app.data;

import java.util.ArrayList;
import java.util.List;

public class Member {

	private BookCollection borrowedBooks = new BookCollection();
	private String name;
	private String id;
	private Account loginInfo;
	
	public Member(String name, String id, Account loginInfo) {
		this.name = new String(name);
		this.id = new String(id);
		this.loginInfo = loginInfo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	
}
