package edu.iit.controller;

import java.io.IOException;
import java.util.ArrayList;

import edu.iit.dao.UserLoginDAO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class ManageUserDetailsPageTwo {
	
	@FXML
	public AnchorPane rootPane;
	
	@FXML
	private TextField name;
	
	@FXML
	private TextField age;

	@FXML
	private TextField sex;
	
	@FXML
	private TextField contact;

	@FXML
	private TextField email;
	
	
	UserLoginDAO userLogin=null;
	UserLogin userLogincred=null;
	
	public ManageUserDetailsPageTwo() {
		
		userLogin = new UserLoginDAO();
		userLogincred= new UserLogin();
	}
	
	public void updateUserDetails() {
		try {
		int number=Integer.parseInt(contact.getText());
		userLogin.AddUserDetails();
		userLogin.UpdateUserDetails(age.getText(),number,sex.getText(),email.getText(),name.getText());
		existingDetails();
		} catch (Exception e) {
			System.err.println("Error in Updating UserDetails");
		}
		
	}
	
	private void existingDetails(){
		try{
			AnchorPane pane = FXMLLoader.load(getClass().getResource("/edu/iit/view/ManageUserDetailsPage1.fxml"));
			rootPane.getChildren().setAll(pane);
			}
			catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	public void goback() {
		try{
		AnchorPane pane = FXMLLoader.load(getClass().getResource("/edu/iit/view/Basic.fxml"));
		rootPane.getChildren().setAll(pane);
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
