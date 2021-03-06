package com.revature.DAO;

import java.sql.Connection;



import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.Model.Reimbursement;
import com.revature.Model.Status;
import com.revature.Model.Type;
import com.revature.Utility.ConnectionFactoryUtility;

public class ReimbursementDAO {
	
	public void update(Reimbursement unprocessedReimbursement) {
		
		try(Connection connection = ConnectionFactoryUtility.getConnection()){
			
			String sql = "UPDATE ers_reimbursements SET resolver = ?, status = ?::status WHERE id = ?";
			
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			
			preparedStatement.setInt(1, unprocessedReimbursement.getResolver());///check parameterindex
			preparedStatement.setObject(2, unprocessedReimbursement.getStatus().name());
			preparedStatement.setInt(3, unprocessedReimbursement.getId());
			
			preparedStatement.executeUpdate();
			
			System.out.println("Reimbursement Successfully Updated!");
		} catch (SQLException e) {
			System.out.println("Updating Failed");
			e.printStackTrace();
		}
	}
	
	////////////////////////////////////////
	
public int create(Reimbursement reimbursementToBeSubmitted) {
		
		try(Connection connection = ConnectionFactoryUtility.getConnection()){
			
			String sql = "INSERT INTO ers_reimbursements (author, description, type, status, amount)"
			+ "VALUES (?, ?, ?::type, ?::status, ?)"
			+ "RETURNING ers_reimbursements.id"; //sql statement to create new reimbursement
			
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			
			preparedStatement.setInt(1, reimbursementToBeSubmitted.getAuthor());//sets author to user who created reimbursement
			preparedStatement.setString(2, reimbursementToBeSubmitted.getDescription());//add description
			preparedStatement.setObject(3, reimbursementToBeSubmitted.getType().name());//set type
			preparedStatement.setObject(4, reimbursementToBeSubmitted.getStatus().name());//sets status
			preparedStatement.setDouble(5, reimbursementToBeSubmitted.getAmount());//set reimbursement amount
			
			ResultSet resultSet;// object created to hold values
			
			if((resultSet = preparedStatement.executeQuery()) != null) {//assigns values if not null will add to database
				
				resultSet.next();
				return resultSet.getInt(1);
			}
		} catch (SQLException e) {
			System.out.println("Creating Reimbursement has failed");
			e.printStackTrace();
		}
		return 0;
	}
	
	///////////////////////////////////////
	
	public List<Reimbursement> getReimbursementsByUser(int userId){
		try(Connection connection = ConnectionFactoryUtility.getConnection()){
			
			String sql = "select * from ers_reimbursements where author = ?";
			
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			
			preparedStatement.setInt(1, userId);
			
			ResultSet resultSet = preparedStatement.executeQuery();
			
			List<Reimbursement> reimbursements = new ArrayList<>();
			
			while(resultSet.next()) {
				
				reimbursements.add(new Reimbursement(
						resultSet.getInt( "id"),
						resultSet.getInt("author"),
						resultSet.getInt("resolver"),
						resultSet.getString("description"),
						Type.valueOf(resultSet.getString("type")),
						Status.valueOf(resultSet.getString("status")),
						resultSet.getDouble("amount")
						));
			}
			return reimbursements;
		} catch (SQLException e) {
			
			System.out.println("Something Went Wrong Obtaining Your List!");
			e.printStackTrace();
		}
		return null;
	}
	
	/////////////////////////////////////////////
	
	public Reimbursement getReimbursementsById(int id){
		try(Connection connection = ConnectionFactoryUtility.getConnection()){
			
			String sql = "select * from ers_reimbursements where id = ?";
			
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			
			preparedStatement.setInt(1, id);
			
			ResultSet resultSet = preparedStatement.executeQuery();
			
			
			if(resultSet.next()) {
				
				return new Reimbursement(
						resultSet.getInt( "id"),
						resultSet.getInt("author"),
						resultSet.getInt("resolver"),
						resultSet.getString("description"),
						Type.valueOf(resultSet.getString("type")),
						Status.valueOf(resultSet.getString("status")),
						resultSet.getDouble("amount")
						);
			}
		} catch (SQLException e) {
			
			System.out.println("Something went wrong with the database!");
			e.printStackTrace();
		}
		return null;
	}
	
	/////////////////////////////////////////
	//gets all reimbursements from dao and uses the input status to grab the correct reimbursements to add to the list
	public List<Reimbursement> getByStatus(Status status){
		List<Reimbursement> byStatus = new ArrayList<>();
		for(Reimbursement r: getAllReimbursements()) {
			if(r.getStatus() == status) {
				byStatus.add(r);
			}
		}
		for(Reimbursement bs : byStatus) {
			System.out.println( bs.getAuthor() + " " + bs.getType() + " " + bs.getDescription() 
			+ " " + bs.getAmount() + " " + bs.getStatus());
		}
		return byStatus;
	}
	
	///////////////////////////////////////////////
	//
	public List<Reimbursement> getAllReimbursements(){
		
		try(Connection connection = ConnectionFactoryUtility.getConnection()){
			
			List<Reimbursement> reimbursements = new ArrayList<>();
			
			String sql = "select * from ers_reimbursements";
			
			Statement statement = connection.createStatement();
			
			ResultSet resultSet = statement.executeQuery(sql);
			
			
			
			while(resultSet.next()) {
				
				reimbursements.add(new Reimbursement(
						resultSet.getInt( "id"),
						resultSet.getInt("author"),
						resultSet.getInt("resolver"),
						resultSet.getString("description"),
						Type.valueOf(resultSet.getString("type")),
						Status.valueOf(resultSet.getString("status")),
						resultSet.getDouble("amount")
						));
			}
			
			return reimbursements;
			
		} catch (SQLException sqlException) {
			
			System.out.println("Something went wrong with the database!");
			sqlException.printStackTrace();
		}
		return null;
	}
	//////////////////////////////////////////////////////////////

}
