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

	public Handler getLoginHandler = (ctx) -> {
		String body = ctx.body();
		
		Gson gson = new Gson();
		
		user = gson.fromJson(body, User.class);
		
		
		if(as.login(user.getUsername(), user.getPassword()) != null) {
			ctx.status(201);
			ctx.result("Login Successful");
			
		}
		else {
			ctx.status(404);
			ctx.result("Password or username incorrect");
		}
	};
	public Handler getRegisterHandler = (ctx) -> {
		String body = ctx.body();
		Gson gson = new Gson();
		
		user = gson.fromJson(body, User.class);
		userDAO.createUser(user);
		
		ctx.result("User successfully added");
		ctx.status(201);
	};

}
