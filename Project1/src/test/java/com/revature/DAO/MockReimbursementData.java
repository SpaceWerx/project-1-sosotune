package com.revature.DAO;

import java.util.ArrayList;
import java.util.List;

import com.revature.Model.Reimbursement;
import com.revature.Model.Status;
import com.revature.Model.Type;

public class MockReimbursementData {
	private final List<Reimbursement> reimbursements = new ArrayList<>();
	public MockReimbursementData() {
		Reimbursement REIM_2_PROC_1 = new Reimbursement(1, 1, 0, "Oracle Java Certification", Type.OTHER, Status.PENDING, 250.00);
		Reimbursement REIM_2_PROC_2 = new Reimbursement(2, 2, 0, "Travel to Reston HQ", Type.TRAVEL, Status.PENDING, 600.00);
		Reimbursement REIM_2_APP_1 = new Reimbursement(3, 1, 3, "Free lunch offer from Sean", Type.FOOD, Status.APPROVED, 25.00);
		Reimbursement REIM_2_APP_2 = new Reimbursement(4, 2, 4, "2-night hotel stay near Orlando Office for Visit", Type.LODGING, Status.APPROVED, 300.00);
		Reimbursement REIM_2_DENY_1 = new Reimbursement(5, 1, 3, "Rental Car to drive from Reston to Orlando", Type.TRAVEL, Status.DENIED, 2500.00);
	
		reimbursements.add(REIM_2_PROC_1);
		reimbursements.add(REIM_2_PROC_2);
		reimbursements.add(REIM_2_APP_1);
		reimbursements.add(REIM_2_APP_2);
		reimbursements.add(REIM_2_DENY_1);
	}
	public List<Reimbursement> getReimbursements(){return reimbursements;}

}
