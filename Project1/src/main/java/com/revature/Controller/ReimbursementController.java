package com.revature.Controller;

import java.util.List;



import javax.net.ssl.SSLEngineResult.Status;

import com.google.gson.Gson;
import com.revature.DAO.ReimbursementDAO;
import com.revature.Model.Reimbursement;
import com.revature.Model.User;
import com.revature.Service.ReimbursementService;
import com.revature.Controller.AuthController;
import io.javalin.http.Handler;

public class ReimbursementController {

	Reimbursement reimbursement = new Reimbursement();
	ReimbursementDAO rDAO = new ReimbursementDAO();
	ReimbursementService rService = new ReimbursementService();
	AuthController ac = new AuthController();
	
	
	public Handler getReimbursementHandler = (ctx) -> {
		
		List<Reimbursement> allReim = rDAO.getAllReimbursements();
		
		Gson gson = new Gson();
		String JSONObject = gson.toJson(allReim);
		
		ctx.result(JSONObject);
		ctx.status(200);
		
	};
	
	public Handler submitHandler = (ctx) ->{
		String body= ctx.body();
		
		Gson gson = new Gson();
		
		Reimbursement reimbursement = gson.fromJson(body, Reimbursement.class);
		rDAO.create(reimbursement);
		
		ctx.result("Reimbursement successfully added!");
		ctx.status(201);
	};
	public Handler getReimbursementById = (ctx) -> {
		String body = ctx.body();
		try {
			
			int id = Integer.parseInt(body);
			
			reimbursement = rService.getReimbursementById(id);
			
			if(reimbursement != null) {
				ctx.status(200);
				ctx.json(reimbursement);
			} else {
				ctx.status(400);
				ctx.result("Couldn not retrieve the reimbursement");
			}
		} catch (Exception e) {
			
			ctx.status(500);
			
			if(!e.getMessage().isEmpty()) {
				ctx.result(e.getMessage());
			}
			e.printStackTrace();
		}
		
	};
	
	
//	public Handler processHandler = (ctx) -> {
//		String authHeader = ctx.header("Current-User");
//		if(authHeader != null) {
//			
//			int userId = Integer
//		}
//		
//	
//	};
	
	public Handler getByStatusHandler =(ctx)->{
		
		String body = ctx.body();
		com.revature.Model.Status status = com.revature.Model.Status.valueOf(body);
		
		List<Reimbursement> byStatus = rDAO.getByStatus(status);
		Gson gson = new Gson();
		String JSONObject = gson.toJson(byStatus);
		
		ctx.result(JSONObject);
		ctx.status(200);
		
	};
	public Handler getByAuthorMethod = (ctx) -> {
		
		List<Reimbursement> byAuthor = rService.getReimbursementByAuthor(ctx.sessionAttribute("currentUser"));
		Gson gson = new Gson();
		String JSONObject= gson.toJson(byAuthor);
		
		ctx.result(JSONObject);
		ctx.status(200);
	};
	
	

}
