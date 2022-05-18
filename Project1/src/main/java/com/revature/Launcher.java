package com.revature;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

import com.revature.Controller.AuthController;
import com.revature.Controller.EmployeeController;
import com.revature.Controller.ReimbursementController;
import com.revature.Controller.UserController;
import com.revature.DAO.ReimbursementDAO;
import com.revature.DAO.UserDAO;
import com.revature.Model.Reimbursement;
import com.revature.Model.Role;
import com.revature.Model.Status;
import com.revature.Service.*;
import com.revature.Utility.ConnectionFactoryUtility;

import io.javalin.Javalin;

public class Launcher {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		UserController uc = new UserController();
//		AuthController ac = new AuthController();
//		ReimbursementController rc = new ReimbursementController();
//		try(Connection conn = ConnectionFactoryUtility.getConnection()){
//			System.out.println("Connection Successful :)");
//		} catch (SQLException e) {
//			System.out.println("Connection failed");
//			e.printStackTrace();
//		}
		//ReimbursementService reim = new ReimbursementService();
		//ReimbursementDAO rdao = new ReimbursementDAO();
		
		//for(Reimbursement r : reim.getReimbursementByAuthor(1)) {
			//System.out.println(r.getAmount());
			
		//}
		//UserDAO udao = new UserDAO();
		//UserService users = new UserService();
		//System.out.println(users.getAllUsers());
		//System.out.println(users.getByRole(Role.MANAGER));
		
		
		CLI_Menu_Service options = new CLI_Menu_Service();
		options.displayLoginMenu();
		
		
//		Javalin app = Javalin.create(
//			config -> {
//				config.enableCorsForAllOrigins();
//			}
//		).start(3000);
//		
//		app.post("/login", ac.getLoginHandler);
//		
//		app.post("/register", ac.getRegisterHandler);
//		
//		app.get("/user", uc.getUsersHandler );
//		
//		app.get("/{id}", uc.getUserByIdHandler);
//		
//		app.get("/reimbursement", rc.getReimbursementHandler);
//		
//		app.post("/reimbursement", rc.submitHandler);
//		
//		app.get("/{id}",rc.getReimbursementById);
//		
//		app.put("/{id}", rc.processHandler);//check how to format id in parameters
		
	}

}
