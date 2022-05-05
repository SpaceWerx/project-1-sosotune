package com.revature.Model;

public class Users {
	private int id;
	private String userName;
	private String passWord;
	private Roles role;
	
	public Users() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Users(int id, String userName, String passWord, Roles role) {
		super();
		this.id = id;
		this.userName = userName;
		this.passWord = passWord;
		this.role = role;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public Roles getRole() {
		return role;
	}

	public void setRole(Roles role) {
		this.role = role;
	}

	
}
