package com.revature;

import java.util.Scanner;

import com.revature.DAO.ReimbursementDAO;
import com.revature.DAO.UserDAO;
import com.revature.Model.Reimbursement;
import com.revature.Model.Role;
import com.revature.Model.Status;
import com.revature.Service.*;

public class Launcher {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		ReimbursementService reim = new ReimbursementService();
		//ReimbursementDAO rdao = new ReimbursementDAO();
		
		//for(Reimbursement r : reim.getReimbursementByAuthor(1)) {
			//System.out.println(r.getAmount());
			
		//}
		//UserDAO udao = new UserDAO();
		//UserService userdao = new UserService();
		//System.out.println(userdao.getAllUsers());
		//System.out.println(userService.getByRole(Role.MANAGER));
		CLI_Menu_Service options = new CLI_Menu_Service();
		options.displayLoginMenu();
		
	}

}
