package edu.iit.controller;

import java.io.IOException;

import javax.swing.JOptionPane;

import edu.iit.model.LoginDetails;
import edu.iit.dao.UserRegisterationDAO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

public class UserRegisteration extends LoginDetails {

	
	@FXML
	public AnchorPane rootpane;
	
	@FXML
	private Button register;
	public void registerUser() {
		
		if(setuserName()){
			
		}else{
			System.err.println("Invalid credentials");
			System.exit(0);
		}
		if(setpassWord()){}
			else{
				System.err.println("Invalid credentials");
				System.exit(0);
			}
		if(setConfirmPassword()){}else{
			System.err.println("Invalid credentials");
			System.exit(0);
		}
		if(setUserid()){}else{
			System.err.println("Invalid credentials");
			System.exit(0);
		}
		if(setuserType()){} else{
			System.err.println("Invalid credentials");
			System.exit(0);
		}
		if (userID == 0) {
			System.out.println("Re- enter with proper details");
		} else {

			if (typeofUser.equals(" ")) {
				JOptionPane.showInputDialog("Please enter the type for UserType. Either Admin/user");
			}
			if (userName.equals(" ")) {
				System.err.println("Please enter the userName. Cannot accept userName as space character");
			}
			if (validatecredentials()) {
				System.out.println("Password and ConfirmPassword matched successfully");
			} else {
				System.err.println("Password and Confirm Password do not match. please try again");
				//System.exit(0);
				AlertErrorMsg("Password and Confirm Password do not match. please try again");
				Back();
				
			}
			UserRegisterationDAO register = new UserRegisterationDAO();
			register.registerUser(new UserRegisteration());
			register.displayuserDetails();
			//register.close();

			existingUser();
			
		}
	}

	
	public void existingUser(){
		try {
			AnchorPane pane= FXMLLoader.load(getClass().getResource("/edu/iit/view/Login.fxml"));
			rootpane.getChildren().setAll(pane);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void Back(){
		try {
			AnchorPane pane= FXMLLoader.load(getClass().getResource("/edu/iit/view/WelcomePage.fxml"));
			rootpane.getChildren().setAll(pane);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	public boolean validatecredentials() {
		String pwd = Password;
		String confirmpwd = confirmPassword;
		if (pwd != "" && confirmpwd != "") {
			if (Password.equals(confirmPassword)) {
				return true;
			}
		}
		return false;
	}

}
