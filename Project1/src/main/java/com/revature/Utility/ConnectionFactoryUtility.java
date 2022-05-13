package com.revature.Utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactoryUtility {
	private static ConnectionFactoryUtility instance;
	
	private ConnectionFactoryUtility() {
		super();
	}
	
	public static ConnectionFactoryUtility getInstance() {
		if(instance == null) {
			instance = new ConnectionFactoryUtility();
		}
		return instance;
		
	}
	
	public static Connection getConnection() throws SQLException{
		try {
			Class.forName("org.postgresql.Driver");
		} catch(ClassNotFoundException e){
			System.out.println("CLASS WASN'T FOUND");
			e.printStackTrace();
		}
		String url = "jdbc:postgresql://javafullstackaws.cng9tkjck5xm.us-west-2.rds.amazonaws.com:5432/postgres?currentSchema=projectone";
		
		String username = "postgres";
		
		String password = "password";
		
		return DriverManager.getConnection(url, username, password);
	}
	
}
