package com.revature.Service;

import java.util.ArrayList;


import java.util.List;

import com.revature.DAO.ReimbursementDAO;
import com.revature.Model.Reimbursement;
import com.revature.Model.Role;
import com.revature.Model.Status;
import com.revature.Model.User;

public class ReimbursementService {
	
	ReimbursementDAO reimbursementDAO = new ReimbursementDAO();
	UserService userService = new UserService();
	
	
	
	
	
	public int submitReimbursement(Reimbursement reimbursementToBeSubmitted){
		
		User employee = userService.getUserById(reimbursementToBeSubmitted.getAuthor());
		
		if(employee.getRole() != Role.EMPLOYEE) {
			throw new IllegalArgumentException("Managers cannot submit reimbursemtn requests.");
		} else {
			reimbursementToBeSubmitted.setStatus(Status.PENDING);
			
			return reimbursementDAO.create(reimbursementToBeSubmitted);
		}
	}
		
	public Reimbursement update(Reimbursement unprocessedReimbursement, int resolver, Status status) {
		
		User manager = userService.getUserById(resolver);
		
		if(manager.getRole() != Role.MANAGER) {
			throw new IllegalArgumentException("An Employee cannot process reimbursement requests.");
		} else {
			
			unprocessedReimbursement.setResolver(resolver);
			unprocessedReimbursement.setStatus(status);
			
			reimbursementDAO.update(unprocessedReimbursement);
			
			return unprocessedReimbursement;
		}
		
	}
	public Reimbursement getReimbursementById(int id) {
		return reimbursementDAO.getReimbursementsById(id);
	}
	
	public List<Reimbursement> getReimbursementByAuthor(int userId) {
		return reimbursementDAO.getReimbursementsByUser(userId);
	}

	public List<Reimbursement> getPendingReimbursements(){
		return reimbursementDAO.getByStatus(Status.PENDING);
	}
	public List<Reimbursement> getResolvedReimbursements(){
		List<Reimbursement> resolvedReimbursements = new ArrayList<>();
		
		resolvedReimbursements.addAll(reimbursementDAO.getByStatus(Status.APPROVED));
		resolvedReimbursements.addAll(reimbursementDAO.getByStatus(Status.DENIED));
		
		return resolvedReimbursements;
		
	}
	
}
