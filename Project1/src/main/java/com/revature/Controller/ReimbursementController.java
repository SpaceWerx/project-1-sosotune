package com.revature.Controller;

import java.util.List;

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
		} catch(Exception e) {
			
			ctx.status(500);
			
			if(!e.getMessage().isEmpty()) {
				ctx.result(e.getMessage());
			}
			e.printStackTrace();
		}
		
	};
	public Handler processHandler;

}
