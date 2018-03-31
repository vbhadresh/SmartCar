package edu.iit.model;

import java.io.IOException;

import edu.iit.controller.UserRegisteration;
import edu.iit.controller.Welcomepage;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.TextField;

public abstract class LoginDetails {
	
	
	public static String typeofUser;
	public static String userName;
	public static String Password;
	public static String confirmPassword;
	public static int userID=1;
	
	@FXML
	private  TextField username;
	@FXML
	private TextField userid;
	@FXML
	private TextField password;
	@FXML
	private TextField confirmpassword;
	@FXML 
	private TextField userType;
	
	public Welcomepage welcome = new Welcomepage();
	public boolean setuserName(){
		
	 userName= username.getText();
	 if(userName.equals("")){
		 AlertErrorMsg("UserName Cannot be null");
		return false;
	 }
	 else{
		 return true;
	 }
	}
	
	public void AlertErrorMsg(String Msg){
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("ERROR");
		alert.setHeaderText(Msg);
		alert.showAndWait();
	}
	
	public String getuserName(){
		return userName;
	}
	public String getPassword(){
		return Password;
	}
	public String getConfirmPassowrd(){
		return confirmPassword;
	}
	
	public int getuserId(){
		return userID;
	}
	
	public boolean setpassWord(){
		String regex="^[a-zA-Z0-9]*$";
		if(password.getText().equals("")){
			 AlertErrorMsg("Password Cannot be null");
			 return false;
		}
		else if(password.getText().matches((regex)) ){
		Password=password.getText();
		return true;
		}
		
		else{
			AlertErrorMsg("Please do enter only alphanumberic characters  without any special characters for password");
			System.exit(0);
		}
		return false;
	}
	
	public boolean setUserid(){
		String regex = "[0-9]+"; 
		if(userid.getText().equals("")){
			 AlertErrorMsg("UserID Cannot be null");
			 welcome.newUser();
			 return false;
		}
		else if(userid.getText().matches(regex)){
		 userID=Integer.parseInt(userid.getText());
		 return true;
		 }
		else{
			AlertErrorMsg("Please do enter Number only for UserID ");
			System.exit(0);
		}
		 return false;
	}
	
	
	public boolean setuserType(){
		if(userType.getText().equals("")){
			 AlertErrorMsg("User Type Cannot be null");
			return false;
		}
		else{
		typeofUser=userType.getText();
		return true;
		}
	}
	
	public String getUserType(){
		return  typeofUser;
	}
	public boolean setConfirmPassword() {
		String regex="^[a-zA-Z0-9]*$";
		if(confirmpassword.getText().equals("")){
			 AlertErrorMsg("Confirm Password Cannot be null");
			return false;
		}
		else if(confirmpassword.getText().matches((regex))){
		 confirmPassword=confirmpassword.getText();
		 return true;
		}
		else{
			AlertErrorMsg("Please do enter alphanumberic characters for password");
			System.exit(0);
		}
		return false;
	}

}
