package com.revature.Controller;

import com.google.gson.Gson;
import com.revature.DAO.UserDAO;
import com.revature.Model.User;
import com.revature.Service.AuthService;

import io.javalin.http.Handler;

public class AuthController {
	
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
		ctx.sessionAttribute("currentUser", CurrentUser.getId());
		currentUser = ctx.sessionAttribute("currentUser");
		if(as.login(user.getUsername(), user.getPassword()) != null) {
			ctx.status(201);
			ctx.result("Login Successful");
			
			
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
