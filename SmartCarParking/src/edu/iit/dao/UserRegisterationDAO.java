package edu.iit.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import edu.iit.controller.UserRegisteration;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class UserRegisterationDAO {


	public UserRegisteration registerUser(UserRegisteration userDetails) {
	
		String query="INSERT INTO smartparkingbookingcommittee (sp_userId, sp_userName, "
				+ "sp_userType, sp_password, sp_confirmpassword)"+ "VALUES(?,?,?,?,?)";
			
		// Use prepared statements to avoid SQL injection attacks
		try (PreparedStatement statement = daoModel.connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
			// Set the parameters to the query
			System.out.println(userDetails.getuserName());
			statement.setInt(1, userDetails.getuserId());
			statement.setString(2, userDetails.getuserName());
			statement.setString(3, userDetails.getUserType());
			statement.setString(4, userDetails.getPassword());
			statement.setString(5, userDetails.getConfirmPassowrd());
			// Execute the insert
			statement.executeUpdate();
			statement.close();
			//close();
		} catch (SQLException e) {
			userDetails = null;
			System.err.println("Error adding member: " + e);
		}
		// Return the club object that was inserted with the id field set.
		return userDetails;

	}
	
	public void displayuserDetails() {
		String query = "Select * from smartparkingbookingcommittee";
		try {
			PreparedStatement ps = daoModel.connection.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/*public void close(){
		try {
			daoModel.connection.close();
			daoModel.connection=null;
		} catch (SQLException e) {
			System.out.println("Error closing connection: " + e);
		}
	}*/

	/*
	 * public boolean getUserDetails(String){ Select }
	 */
	private Connection connection = null;
	private static int count = 0;
	private static Statement statement = null;

}
