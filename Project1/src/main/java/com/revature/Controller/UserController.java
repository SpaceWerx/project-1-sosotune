package com.revature.Controller;

import java.util.List;



import com.google.gson.Gson;
import com.revature.Model.User;
import com.revature.Service.UserService;

import io.javalin.http.Handler;

public class UserController {
	
	UserService us = new UserService();
	User user;
	
	public Handler getUsersHandler = (ctx) ->{
		
		List<User> allUsers = us.getAllUsers();
		
		Gson gson = new Gson();
		
		String JSONObject = gson.toJson(allUsers);
		
		ctx.result(JSONObject);
		ctx.status(200);
		
	};
	
	public Handler getUserByIdHandler = (ctx) ->{
		String body = ctx.body();
		
		int id = Integer.parseInt(body);
		user = us.getUserById(id);
		Gson gson = new Gson();
		String JSONObject = gson.toJson(user);
		
		ctx.result(JSONObject);
		ctx.status(200);
		
	};
	
	public Handler getUserByUsernameHandler = (ctx) ->{
		String usernameParam = ctx.pathParam("username");
		
		
		user = us.getUserByUsername(usernameParam);
		Gson gson = new Gson();
		String JSONObject = gson.toJson(user);
		
		ctx.result(JSONObject);
		ctx.status(200);
		
	};

}
