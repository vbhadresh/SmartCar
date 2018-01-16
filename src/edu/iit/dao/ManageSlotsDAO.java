package edu.iit.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import edu.iit.controller.AddCar;
import edu.iit.controller.Admin_addSlots_controller;
import edu.iit.controller.BookSlot;
import edu.iit.controller.ConfirmBookingSlotController;
import edu.iit.model.Payment;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class ManageSlotsDAO {
	
	

	public boolean addSlots(Admin_addSlots_controller slotDetails, String Places) {	
		String query="INSERT INTO slotdetails (sd_totalSlots, sd_availableSlots, "
				+ "sd_categoryId,categoryName,slotArea)"+ "VALUES(?,?,?,?,?)";
			
		// Use prepared statements to avoid SQL injection attacks
		try (PreparedStatement statement = daoModel.connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
			// Set the parameters to the query
			statement.setInt(1, slotDetails.getTotalSLots());
			statement.setInt(2, slotDetails.getAvailableSLots());
			statement.setInt(3, slotDetails.getCategoryID());
			statement.setString(4, slotDetails.getCategoryName());
			statement.setString(5,Places);
			// Execute the insert
			statement.executeUpdate();
			//statement.close();
			return true;
		} catch (SQLException e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error Dialog");
			alert.setHeaderText("Unable to Add Slot Details to DataBase");
			alert.setContentText("Please check the input and try again");
			alert.showAndWait();
			slotDetails = null;
			
		}
		// Return the club object that was inserted with the id field set.
		return false;

	}
	public ArrayList displaySlots(String categoryName, String place){
		String query = "Select * from slotdetails where categoryName= '"+ categoryName +"'" +" and slotArea= '"+place + "';";
		ArrayList<String> slotcount_details = new ArrayList<String>();
		try {
			PreparedStatement ps = daoModel.connection.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			//ResultSet result=statement.executeQuery(query);
			while (rs.next()) {
				slotcount_details.add(rs.getString("sd_totalSlots"));
				slotcount_details.add(rs.getString("sd_availableSlots"));
			}
			ps.close();
			//close();
			return slotcount_details;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return slotcount_details;
	}
	
	public void updateSlots(){
		Payment confirmslotdetails= new Payment();
		
		String query ="Update slotdetails set sd_availableSlots='"+ confirmslotdetails.getAvailableslots()
		+"'" + " where categoryName= '" + confirmslotdetails.getCategory()   
		+"'"+ " and slotArea ='"+confirmslotdetails.getView()  +"';"; 
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
	
	public int getAvaialbleSLots(String categoryName, String place){
		String query="Select * from slotdetails where categoryName= '"+ categoryName +"'" +" and slotArea ='"+place + "';";
		int available_slots=0;
		try {
			PreparedStatement ps = daoModel.connection.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				available_slots=rs.getInt(1);
				ps.close();
				rs.close();
				//close();
				return available_slots;
			}
		}
		catch(Exception e){
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error Dialog");
			alert.setHeaderText("Unable to Fetching Available Slot Details from DataBase");
			alert.setContentText("Please check the input and try again");
			alert.showAndWait();
		}
		return available_slots;
	}
	
	/*public void close(){
		try{
			daoModel.connection.close();
			daoModel.connection=null;
		}
		catch(Exception e){
			System.err.println("Failed to close DB Connection");
		}
	}
	*/
	public void set_tariff(int tariff,int categoryID){
		String query="UPDATE slotdetails set slot_tariff=' "+ tariff +"'"+ " where sd_categoryId=' "+ categoryID+"';";
		try {
			PreparedStatement ps = daoModel.connection.prepareStatement(query);
			ps.executeUpdate();
			ps.close();
			//close();
		}
		catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		}
	}
	
	public int get_tariff(String categoryName,String place){
		String query="Select * from slotdetails where categoryName='"+categoryName+"' "+ "and slotArea='" +place+"';";
		int getTariff=0;
		try {
			PreparedStatement ps = daoModel.connection.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				getTariff=rs.getInt(6);
				return getTariff;
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return 0;
	}
	
	public void updateTariff(String categoryID,int tariff){
		String query="UPDATE slotdetails set slot_tariff=' "+ tariff +"'"+ " where sd_categoryId=' "+ categoryID+"';";
		try {
			PreparedStatement ps = daoModel.connection.prepareStatement(query);
			ps.executeUpdate();
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Confirmation Dialog");
			alert.setHeaderText("Successfully Updtaed Slot Tariff details");
			alert.showAndWait();
			ps.close();
			//close();
		}
		catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		}
	}
}
