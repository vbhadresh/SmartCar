package edu.iit.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.iit.model.LoginDetails;
import edu.iit.controller.AddCar;
import edu.iit.controller.Admin_addSlots_controller;
import edu.iit.controller.BookSlot;
import edu.iit.controller.ConfirmBookingSlotController;
import edu.iit.model.Payment;
import edu.iit.controller.UserLogin;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class BookingHistoryDAO {
	
	
	public static ConfirmBookingSlotController confirmedSlot=null;
	public static UserLogin login= null;
	
	public BookingHistoryDAO(){
		confirmedSlot= new ConfirmBookingSlotController();
		login= new UserLogin();
	}

	public boolean updateBookingDetails() {	
		String query="INSERT INTO bookinghistorydetails (bookingiD,bh_bookingDate,bh_bookingVenue,bh_userName)"+ 
				"VALUES(?,?,?,?)";
			
		// Use prepared statements to avoid SQL injection attacks
		try (PreparedStatement statement = daoModel.connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
			// Set the parameters to the query
			statement.setInt(1, login.getuserId());
			statement.setString(2, confirmedSlot.getDate());
			statement.setString(3, confirmedSlot.getView());
			statement.setString(4, login.getuserName());
			// Execute the insert
			statement.executeUpdate();
			//statement.close();
			return true;
		} catch (SQLException e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error Dialog");
			alert.setHeaderText("Unable to Add Booking Details to DataBase");
			//alert.setContentText("Please check the input and try again");
			alert.showAndWait();
		}
		// Return the club object that was inserted with the id field set.
		return false;

	}
	public ArrayList displayBookingHistory(){
		
		String query = "Select * from bookinghistorydetails where  "+" bh_userName= '"+login.getuserName() + "';";
		ArrayList<List<String>> sloBookingdetails = new ArrayList<List<String>>();
		List<String> details= new ArrayList<String>();
		
		try {
			PreparedStatement ps = daoModel.connection.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			//ResultSet result=statement.executeQuery(query);
			while (rs.next()) {
				//details.clear();
				details.add(rs.getString("bh_bookingDate"));
				details.add(rs.getString("bh_bookingVenue"));
				details.add(rs.getString("bh_userName"));
			}
			sloBookingdetails.add(details);
			ps.close();
			//close();
			return sloBookingdetails;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return sloBookingdetails;
	}
	
	public void deleteBooking(String placeName){
		String query="Delete from bookinghistorydetails where bh_bookingVenue='"+placeName+"';";
		try {
			PreparedStatement ps = daoModel.connection.prepareStatement(query);
			 ps.execute();
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setHeaderText("Successfully Delete Booking Details from DataBase for Venue "+placeName);
			//alert.setContentText("Please check the input and try again");
			alert.showAndWait();
		}catch(Exception e){
			System.err.println("Failed to delete booking history");
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error Dialog");
			alert.setHeaderText("Failed to Delete Booking History to DataBase");
			//alert.setContentText("Please check the input and try again");
			alert.showAndWait();
		}
			
	}
	
	public ArrayList getLatestBooking(String placeName){
		String query="SELECT * FROM bookinghistorydetails where bh_bookingVenue='"+placeName+"';";
		ArrayList<String> sloBookingdetails = new ArrayList<String>();
		try {
			PreparedStatement ps = daoModel.connection.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			//ResultSet result=statement.executeQuery(query);
			while (rs.next()) {
				//details.clear();
				sloBookingdetails.add(rs.getString("bh_bookingDate"));
				sloBookingdetails.add(rs.getString("bh_bookingVenue"));
				sloBookingdetails.add(rs.getString("bh_userName"));
			}
			ps.close();
			//close();
			return sloBookingdetails;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return sloBookingdetails;
	}
}
