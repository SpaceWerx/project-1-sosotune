package com.revature.Controller;

import java.util.List;

import javax.net.ssl.SSLEngineResult.Status;

import com.google.gson.Gson;
import com.revature.DAO.ReimbursementDAO;
import com.revature.Model.Reimbursement;
import com.revature.Service.ReimbursementService;

import io.javalin.http.Handler;

public class ReimbursementController {

	Reimbursement reimbursement = new Reimbursement();
	ReimbursementDAO rDAO = new ReimbursementDAO();
	ReimbursementService rService = new ReimbursementService();
	
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
		
		try {
			String idParam = ctx.pathParam("id");
			int id = Integer.parseInt(idParam);
			
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
	
	//work on processhandler
	public Handler processHandler = (ctx) -> {
		
		String authHeader = ctx.header("Current-User");
		if(authHeader != null) {
			
			int userId = Integer.parseInt(authHeader);
			try {
				String reimbursementIdInput = ctx.pathParam("id");
				int id = Integer.parseInt(reimbursementIdInput);
				String body = ctx.body();
				String statusInput = ctx.formParam("status");
				com.revature.Model.Status status = com.revature.Model.Status.valueOf(statusInput);
				Gson gson = new Gson();
				
				
				reimbursement = rService.getReimbursementById(id);
				
				if(reimbursement != null) {
					
					Reimbursement processedReimbursement = gson.fromJson(body, Reimbursement.class);
					rService.update(processedReimbursement, id, status);
					ctx.status(202);
					ctx.json(processedReimbursement);
				} else {
					ctx.status(400);
					ctx.result("Reimbursement processing was not successful");
				}
			} catch(Exception e) {
				
				ctx.status(500);
				
				if(!e.getMessage().isEmpty()) {
					ctx.result(e.getMessage());
				}
				e.printStackTrace();
			}
			
		}ctx.status(403);
		ctx.result("Missing Current User Header with ID");	
	};
	
	public Handler getByStatusHandler =(ctx)->{
		
		String statusParam = ctx.pathParam("status");
		com.revature.Model.Status status = com.revature.Model.Status.valueOf(statusParam);
		
		List<Reimbursement> byStatus = rDAO.getByStatus(status);
		Gson gson = new Gson();
		String JSONObject = gson.toJson(byStatus);
		
		ctx.result(JSONObject);
		ctx.status(200);
		
	};
	public Handler getByAuthorMethod = (ctx) -> {
		String idParam = ctx.pathParam("author");
		int id = Integer.parseInt(idParam);
		List<Reimbursement> byAuthor = rService.getReimbursementByAuthor(id);
		Gson gson = new Gson();
		String JSONObject= gson.toJson(byAuthor);
		
		ctx.result(JSONObject);
		ctx.status(200);
	};

}
