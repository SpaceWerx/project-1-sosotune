package com.revature.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.Model.Reimbursement;
import com.revature.Model.Role;
import com.revature.Model.Status;
import com.revature.Model.Type;
import com.revature.Model.User;
import com.revature.Utility.ConnectionFactoryUtility;



public class UserDAO {
	//get users from repository by the id, called by userservice
	public User getUserbyId(int id) {
		
		try(Connection connection = ConnectionFactoryUtility.getConnection()){
			
			String sql = "select * from ers_users where id = ?";
			
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			
			preparedStatement.setInt(1, id);
			
			ResultSet resultSet = preparedStatement.executeQuery();
			
			if (resultSet.next()) {	
				return new User(
						resultSet.getInt("id"),
						resultSet.getString("username"),
						resultSet.getString("password"),
						Role.valueOf(resultSet.getString("role"))
						);
			}
			
		}catch (SQLException e) {
			
			System.out.println("Something when wrong with getting user by id via the database!");
			e.printStackTrace();
		}
		return null;
	}
	
	/////////////////////////////////////////////////////////////////
	//gets user by username called by userservice
	public User getByUsername(String username) {
		
try(Connection connection = ConnectionFactoryUtility.getConnection()){
			String sql = "select * from ers_users where username = ?";
			
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, username);
			ResultSet resultSet = preparedStatement.executeQuery();
			
			if (resultSet.next()) {
				return new User(
						resultSet.getInt("id"),
						resultSet.getString("username"),
						resultSet.getString("password"),
						Role.valueOf(resultSet.getString("role"))
						);
			}
			
		}catch (SQLException e) {
			System.out.println("Something when wrong with obtaining user by username via the database!");
			e.printStackTrace();
		}
		return null;
		
	}
	
	////////////////////////////////////////////////////////
	//creates a user in database called by register in authservice
	public int createUser(User user){
		try(Connection connection = ConnectionFactoryUtility.getConnection()){
			
			String sql = "INSERT INTO ers_users (username, password, role)" 
			+ "VALUES (?, ?, ?::role)"
			+ "RETURNING ers_users.id";
			
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			
			
			preparedStatement.setString(1, user.getUsername());
			preparedStatement.setString(2, user.getPassword());
			preparedStatement.setObject(3, user.getRole().name());
			
			
			ResultSet resultSet;
			
			if((resultSet = preparedStatement.executeQuery()) != null) {
				resultSet.next();
				return resultSet.getInt(1);
				}
			} catch (SQLException e) {
				System.out.println("Creating User has failed");
				e.printStackTrace();
			}
			return 0;
		}
	
	///////////////////////////////////////////////
	//retrieves all users from database
	public List<User> getAllUsers() {
		
try(Connection connection = ConnectionFactoryUtility.getConnection()){
			
			List<User> users = new ArrayList<>();
			
			String sql = "select * from ers_users";
			
			Statement statement = connection.createStatement();
			
			ResultSet resultSet = statement.executeQuery(sql);
			
			
			
			while(resultSet.next()) {
				
				users.add(new User(
						resultSet.getInt( "id"),
						resultSet.getString("username"),
						resultSet.getString("password"),	
						Role.valueOf(resultSet.getString("role"))
						));
			}
			
			return users;
			
		} catch (SQLException sqlException) {
			
			System.out.println("Something went wrong with the database!");
			sqlException.printStackTrace();
			
		}
		return null;
		
	}
	//////////////////////////////////////////////////////////////////////
	
}
