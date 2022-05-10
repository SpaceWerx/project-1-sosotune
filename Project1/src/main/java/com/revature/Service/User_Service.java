package com.revature.Service;
import java.util.ArrayList;
import java.util.List;

import com.revature.Model.*;

public class User_Service {
	private final static List<User> users = new ArrayList<>();
	
	
	public User getUserByUsername(String username){
		for (User user : users) {
			if (user.getUsername() == username) {
				return user;
			}
		}
		return null;
	}
	public static User getUserById(int id) {
		for (User user : users) {
			if (user.getId() == id) {
				return user;
			}
		}
		return null;
		
	}
	
	public List<User> getAllUsers(List<User> users) {
		return users;
		
	}
	public void idExists(int id) {
		for (User user: users) {
			if(user.getId()==id) {
				System.out.println("Id exists.");
				break;
			}
		}
		System.out.println("Id does not exist");
		
	}
	
	public static List<User> getByRole(Role role) {
		for(User user: users) {
			if(user.getRole() == role) {
			users.add(user);
			}
		}
		return users;
		
		
	}

}
