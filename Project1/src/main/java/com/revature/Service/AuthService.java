package com.revature.Service;

import com.revature.Model.User;

public class AuthService {

	public int register(User userToBeRegistered) {
		
		if(userDAO.getByUserName(userToBeRegistered.getUserName()) != null) {
			
			throw new NullPointerException("Username is arleady taken");
		}
		return userDAO.create(userToBeRegistered);
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
