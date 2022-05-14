package com.revature.Service;
import java.util.ArrayList;
import java.util.List;

import com.revature.DAO.UserDAO;
import com.revature.Model.*;

public class UserService {
	UserDAO userDAO = new UserDAO();
	
	
	public User getUserByUsername(String username){
		return userDAO.getByUsername(username);
	}
	public User getUserById(int id) {
		return userDAO.getUserbyId(id);
	}
	
	public List<User> getAllUsers(List<User> users) {
		return userDAO.getAllUsers();
		
	}
	////
	public void idExists(int id) {
		for(User user : userDAO.getAllUsers()) {
			if(user.getId()== id) {
				System.out.println("This ID exists.");
				break;
			}
		}
		System.out.println("This ID does not exist.");
	}
	
	public  List<User> getByRole(Role role) {
		List<User> byRole = new ArrayList<>();
		for (User user : userDAO.getAllUsers()) {
			if (user.getRole() == role) {
				byRole.add(user);
			}
		}
		return byRole;
		
		
	}

}
