package edu.iit.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import edu.iit.model.LoginDetails;
import edu.iit.controller.AddCar;
import edu.iit.controller.UserLogin;
import edu.iit.controller.UserRegisteration;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class CarModelDAO {
	
	
	public boolean AddCarDetails(AddCar carDetails) {	
		String query="INSERT INTO addcar (ad_carName, ad_carModel, "
				+ "userID,carId, userName)"+ "VALUES(?,?,?,?,?)";
			
		// Use prepared statements to avoid SQL injection attacks
		try (PreparedStatement statement = daoModel.connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
			// Set the parameters to the query
			
			statement.setString(1, carDetails.getCarName());
			statement.setString(2, carDetails.getCarModel());
			statement.setInt(3, carDetails.getUserID());
			statement.setInt(4, carDetails.getUserID());
			statement.setString(5, carDetails.getUserName());
			// Execute the insert
			statement.executeUpdate();
			//statement.close();
			//close();
			return true;
		} catch (SQLException e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error Dialog");
			alert.setHeaderText("Unable to Add Car Details to DataBase");
			alert.setContentText("Each User Can Update only car Details. Delete an Entry in DB and try again");
			alert.showAndWait();
			carDetails = null;
			
		}
		// Return the club object that was inserted with the id field set.
		return false;

	}
	
	public ArrayList displaycarDetails() {
		UserLogin userCred= new UserLogin();
		int userId=userCred.getuserId();
		ArrayList<String> car_list = new ArrayList<String>();
		String query = "Select * from addcar where userID= '"+ userId +"';";
		try {
		PreparedStatement ps = daoModel.connection.prepareStatement(query);
		ResultSet rs = ps.executeQuery();
		//ResultSet result=statement.executeQuery(query);
		while (rs.next()) {
			car_list.add(rs.getString("ad_carName"));
			car_list.add(rs.getString("ad_carModel"));
			car_list.add(rs.getString("userID"));
			car_list.add(rs.getString("carId"));
			car_list.add(rs.getString("userName"));
//			/ps.close();
			//rs.close();
			//close();
			return car_list;
		}
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return car_list;
		
	}
	
	public void updateCarDetails(String carName,String carModel){
		UserLogin logincred=new UserLogin();
		String query ="Update addcar set ad_carName='"+ carName
		+"'" + " , ad_carModel= '" + carModel   
		+"'"+ " where userID ="+  logincred.getuserId()+";"; 
		try {
			PreparedStatement ps = daoModel.connection.prepareStatement(query);
			ps.executeUpdate();
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Confirmation Dialog");
			alert.setHeaderText("Successfully Updtaed Slot details");
			//alert.setContentText("Please check the input and try again");
			alert.showAndWait();
			ps.close();
			//close();
		}
		catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		
	}
	private Connection connection=null;
	private Statement statement=null;
}
