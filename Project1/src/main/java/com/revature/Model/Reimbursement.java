package com.revature.Model;
import com.revature.Controller.AuthController;

public class Reimbursement {
	private int id;
	private int author;
	private int resolver;
	private String description;
	private Type type;
	private Status status;
	private double amount;
	public Reimbursement() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	


	public Reimbursement(int id, int author, int resolver, String description, Type type, Status status,
			double amount) {
		super();
		this.id = id;
		this.author = author;
		this.resolver = resolver;
		this.description = description;
		this.type = type;
		this.status = status;
		this.amount = amount;
	}
	
	




	public Reimbursement(int id, int author, String description, Type type, Status status, double amount) {
		super();
		this.id = id;
		this.author = author;
		this.description = description;
		this.type = type;
		this.status = status;
		this.amount = amount;
	}
	





	public Reimbursement(String description, Type type, double amount) {
		super();
		this.author = AuthController.currentUser;
		this.description = description;
		this.type = type;
		this.status = Status.PENDING;
		this.amount = amount;
	}





	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public int getAuthor() {
		return author;
	}





	public void setAuthor(int author) {
		this.author = author;
	}





	public int getResolver() {
		return resolver;
	}





	public void setResolver(int resolver) {
		this.resolver = resolver;
	}





	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public Type getType() {
		return type;
	}


	public void setType(Type type) {
		this.type = type;
	}


	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	
	

}
