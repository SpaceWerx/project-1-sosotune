package com.revature.Service;

import com.revature.DAO.UserDAO;
import com.revature.Model.User;


public class AuthService {
	
	UserDAO userDAO = new UserDAO();

	public int register(User userToBeRegistered) {
		
		if(userDAO.getByUsername(userToBeRegistered.getUsername()) != null) {
			
			throw new NullPointerException("Username is arleady taken");
		}
		return userDAO.createUser(userToBeRegistered);
	}
	
	///////////////////////////////////////////////////
	
	public User login(String username, String password) {
		
		User user;
		
		try {
			
			user = userDAO.getByUsername(username);
			
			if(user!=null && password.equals(user.getPassword())) {
				
				System.out.println("Logged In Successfully!");
				return user;
			} 
			else if(user!=null && !password.equals(user.getPassword())){
				
				System.out.println("Wrong Password");
				return null;
			
			} 
			else {
				
				System.out.println("User Does Not Exist");
				return null;
			}
		} catch (Exception e) {
			System.out.println("Login Unsuccessfull");
			e.printStackTrace();
		}
		return null;
	}
}
