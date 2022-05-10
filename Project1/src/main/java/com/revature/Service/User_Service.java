package com.revature.Service;
import java.util.ArrayList;
import java.util.List;

import com.revature.Model.*;

public class User_Service {
	private final List<User> users = new ArrayList<>();
	
	
	public User getUserByUsername(String username){
		for (User user : users) {
			if (user.getUsername() == username) {
				return user;
			}
		}
		return null;
	}
	public User getUserById(int id) {
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
	
	public User getByRole(Role role) {
		for(User user: users) {
			if(user.getRole() == role) {
				return user;
			}
		}
		return null;
		
	}

}
