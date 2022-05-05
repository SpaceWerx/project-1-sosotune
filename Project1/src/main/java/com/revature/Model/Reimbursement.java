package com.revature.Model;

public class Reimbursement {
	private int id;
	private int Author;
	private int Resolver;
	private String description;
	private Type type;
	private Status status;
	private String amount;
	public Reimbursement() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	


	public Reimbursement(int id, int author, int resolver, String description, Type type, Status status,
			String amount) {
		super();
		this.id = id;
		Author = author;
		Resolver = resolver;
		this.description = description;
		this.type = type;
		this.status = status;
		this.amount = amount;
	}





	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public int getAuthor() {
		return Author;
	}





	public void setAuthor(int author) {
		Author = author;
	}





	public int getResolver() {
		return Resolver;
	}





	public void setResolver(int resolver) {
		Resolver = resolver;
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
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	
	

}
