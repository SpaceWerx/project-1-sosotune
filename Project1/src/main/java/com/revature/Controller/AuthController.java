package com.revature.Controller;

import com.google.gson.Gson;
import com.revature.DAO.UserDAO;
import com.revature.Model.Role;
import com.revature.Model.User;
import com.revature.Service.AuthService;
import com.revature.Service.UserService;

import io.javalin.http.Handler;

public class AuthController {
	UserService userService = new UserService();
	AuthService as = new AuthService();
	UserDAO userDAO = new UserDAO();
	User user = new User();
	public static int currentUser;
	User CurrentUser;
//login for users, saves current user to session attribute to be used in other handlers.
	public Handler getLoginHandler = (ctx) -> {
		String body = ctx.body();
		
		Gson gson = new Gson();
		
		user = gson.fromJson(body, User.class);
		CurrentUser = userDAO.getByUsername(user.getUsername());
		if(CurrentUser!=null) {
		ctx.sessionAttribute("currentUser", CurrentUser.getId());
		
		currentUser = ctx.sessionAttribute("currentUser");}
		if(as.login(CurrentUser.getUsername(), CurrentUser.getPassword()) != null) {
			if(CurrentUser.getRole() == Role.EMPLOYEE) {
			ctx.status(201);
			ctx.result("Login Successful");	
			System.out.println("Employee");
			}
			if(CurrentUser.getRole()==Role.MANAGER){
			ctx.status(202);
			ctx.result("Login Successful");
			System.out.println("employee");
			}
			
		}
			
		else {
			ctx.status(404);
			ctx.result("Password or username incorrect");
		}
	};
	//Allows user to register and creates a new user in the database
	public Handler getRegisterHandler = (ctx) -> {
		String body = ctx.body();
		Gson gson = new Gson();
		
		User user = gson.fromJson(body, User.class);
		if(user!= null) {
			userDAO.createUser(user);
		
			ctx.result("User successfully added");
			ctx.status(201);
		}else {
			ctx.result("User not created");
			ctx.status(400);
		}
	};

}
