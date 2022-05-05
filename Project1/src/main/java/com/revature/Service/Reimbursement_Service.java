package com.revature.Service;

import java.util.ArrayList;
import java.util.List;

import com.revature.Model.Reimbursement;
import com.revature.Model.Status;

public class Reimbursement_Service {
	private final List<Reimbursement> reimbursements = new ArrayList<>();
	
	public void submitReimbursement(Reimbursement reimbursementToBeSubmitted){
		Reimbursement latestReimbursement = reimbursements.get(reimbursements.size() - 1);
		int id = latestReimbursement.getId() + 1; // New Id is 1 higher than the previous highest
		
		reimbursementToBeSubmitted.setId(id);
		reimbursementToBeSubmitted.setStatus(Status.PENDING);
		reimbursements.add(reimbursementToBeSubmitted);
	}
	public void update(Reimbursement unprocessedReimbursement, int resolver, Status updatedStatus) {
		for(Reimbursement reimbursement: reimbursements) {
			if(reimbursement.getId()== unprocessedReimbursement.getId()) {
				reimbursement.setResolver(resolver);
				reimbursement.setStatus(updatedStatus);
				return;
			}
		}
		throw new RuntimeException("There was an error processing this reimbursement, please try again");
		
	}
	public Reimbursement getReimbursementById(int id) {
		for(Reimbursement reimbursement : reimbursements) {
			if(reimbursement.getId()== id) {
				return reimbursement;
			}
		}
		return null;
	}
	
	public List<Reimbursement> getReimbursementByAuthor(int userId) {
		List<Reimbursement> userReimbursements = new ArrayList<>();
		
		for(Reimbursement r : reimbursements) {
			if(r.getAuthor() == userId || r.getResolver() == userId) {
				userReimbursements.add(r);
			}
		}
		return userReimbursements;
	}

	public List<Reimbursement> getPendingReimbursements(){
		List<Reimbursement> pendingReimbursements = new ArrayList<>();
		
		for (Reimbursement reimbursement : reimbursements) {
			if(reimbursement.getStatus() == Status.PENDING) {
				pendingReimbursements.add(reimbursement);
			}
		}
		return pendingReimbursements;
	}
	public List<Reimbursement> getResolvedReimbursements(){
		List<Reimbursement> resolvedReimbursements = new ArrayList<>();
		
		for (Reimbursement reimbursement : reimbursements) {
			if(reimbursement.getStatus() == Status.APPROVED || reimbursement.getStatus() == Status.DENIED) {
				resolvedReimbursements.add(reimbursement);
			}
		}
		return resolvedReimbursements;
	}
	
}
