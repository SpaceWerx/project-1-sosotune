package com.revature.Model;

public class User {
	private int id;
	private String userName;
	private String passWord;
	private Role role;
	
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public User(int id, String userName, String passWord, Role role) {
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

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	
}
