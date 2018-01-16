package edu.iit.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import edu.iit.model.LoginDetails;
import edu.iit.controller.AddCar;
import edu.iit.model.Payment;
import edu.iit.controller.UserLogin;
import edu.iit.controller.UserRegisteration;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class UserLoginDAO {

	public boolean checkuser(LoginDetails userdetails){
		String query="Select * from smartparkingbookingcommittee where sp_userId= '"+ userdetails.getuserId() +"' AND sp_userName ='"+ userdetails.getuserName() 
		+"' AND sp_password ='"+ userdetails.getPassword() +"';";
		try {
			PreparedStatement ps = daoModel.connection.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
			if(userdetails.getuserName().equals(rs.getString("sp_userName")) && userdetails.getPassword().equals(rs.getString("sp_password")))
				ps.close();
				//close();
				return true;
			}
		} catch (SQLException e) {
			System.err.println("Login Credentials didnt match with any registered user details. please try again");
		}
		return false;
	}
	
	public boolean ValidateTypeofUser(String typeofUser,LoginDetails userdetails){
		String query ="Select * from smartparkingbookingcommittee where sp_userId='"+userdetails.getuserId()+"';";
		String userType="";
		try {
			PreparedStatement ps = daoModel.connection.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				userType=rs.getString("sp_userType");
			}
			if(userType.equalsIgnoreCase(typeofUser)){
				return true;
			}
			else{
				AlertErrorMsg("Login Credentials did not match for "+typeofUser);
			}
		}
		catch (SQLException e) {
			System.err.println("Login Credentials didnt match with any registered user details. please try again");
		}
		return false;
	}
	
	
	
	private void AlertErrorMsg(String Msg){
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("ERROR");
		alert.setHeaderText(Msg);
		alert.showAndWait();
	}
	
	
	public void UpdateUserDetails(String Age, int Contact, String Sex, String emailId,String Name){
		UserLogin userCred=new UserLogin();
		String query ="Update userDetails set user_Name='"+ Name+"' ,"+ "ud_Age='"+ Age
				+"'" +", ud_Contact="+ Contact +", ud_Sex='"+Sex+"' ,ud_EmailId='"+emailId+"'" +" where ud_userId="+userCred.getuserId()	+";"; 
		try {
			PreparedStatement ps = daoModel.connection.prepareStatement(query);
			ps.executeUpdate();
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Confirmation Dialog");
			alert.setHeaderText("Successfully Updated User details");
			alert.showAndWait();
			ps.close();
			//close();
		}
		catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}
	
	public boolean AddUserDetails() {	
		String query="INSERT INTO userDetails (ud_Age,ud_Contact,ud_Sex,ud_EmailId,ud_userId,user_Name)"+ "VALUES(?,?,?,?,?,?)";
		UserLogin userCred=new UserLogin();
		// Use prepared statements to avoid SQL injection attacks
		try (PreparedStatement statement = daoModel.connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
			// Set the parameters to the query
			statement.setString(1,"");
			statement.setInt(2,userCred.getuserId() );
			statement.setString(3,"");
			statement.setString(4,"");
			statement.setInt(5,userCred.getuserId() );
			statement.setString(6,"");
			statement.executeUpdate();
			//statement.close();
			//close();
			return true;
		} catch (SQLException e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error Dialog");
			alert.setHeaderText("Unable to Add USer Details to DataBase");
			alert.setContentText("Each User Can Update only car Details. Delete an Entry in DB and try again");
			alert.showAndWait();
			
		}
		// Return the club object that was inserted with the id field set.
		return false;

	}
	
	
	public ArrayList displayUserDetails(){
		UserLogin userCred= new UserLogin();
		int userId=userCred.getuserId();
		ArrayList<String> car_list = new ArrayList<String>();
		String query = "Select * from userDetails where ud_userId= "+ userId +";";
		
		try {
		PreparedStatement ps = daoModel.connection.prepareStatement(query);
		ResultSet rs = ps.executeQuery();
		//ResultSet result=statement.executeQuery(query);
		while (rs.next()) {
			car_list.add(rs.getString("ud_Age"));
			car_list.add(rs.getString("ud_Contact"));
			car_list.add(rs.getString("ud_Sex"));
			car_list.add(rs.getString("ud_EmailId"));
			car_list.add(rs.getString("ud_userId"));
			car_list.add(rs.getString("user_Name"));
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
	private Connection connection=null;
	private Statement statement=null;
}
