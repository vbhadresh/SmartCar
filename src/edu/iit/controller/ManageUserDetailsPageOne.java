package edu.iit.controller;

import java.io.IOException;
import java.util.ArrayList;

import edu.iit.dao.CarModelDAO;
import edu.iit.dao.UserLoginDAO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class ManageUserDetailsPageOne {
	
	@FXML
	public AnchorPane rootPane;
	
	@FXML
	private VBox name;
	
	@FXML
	private VBox age;

	@FXML
	private VBox sex;
	
	@FXML
	private VBox contact;

	@FXML
	private VBox email;
	UserLoginDAO userLogin=null;
	
	public ManageUserDetailsPageOne() {
		
		userLogin = new UserLoginDAO();
	}
	
	
	public void existingDetails(){
		 Label label1= new Label();
		 Label label2= new Label();
		 Label label3= new Label();
		 Label label4= new Label();
		 Label label5= new Label();
		  ArrayList<String> userDetails = userLogin.displayUserDetails();
		  if(userDetails.isEmpty()){
			  label1.setText("User Details are not updated in your Profile.Please update it by Clicking on Update Button Below.");
			  label1.setTextFill(Color.WHITE);
			  name.getChildren().clear();
			  name.getChildren().add(label1);
		  }
		 else{
			 name.getChildren().clear();
			 age.getChildren().clear();
			 sex.getChildren().clear();
			 email.getChildren().clear();
			 contact.getChildren().clear();
			
		  label1.setText(userDetails.get(0));
		  label2.setText(userDetails.get(1));
		  label3.setText(userDetails.get(2));
		  
		  label4.setText(userDetails.get(3));
		  
		  label5.setText(userDetails.get(4));
		  
		  label1.setTextFill(Color.WHITE);
		  label2.setTextFill(Color.WHITE);
		  label3.setTextFill(Color.WHITE);
		  label4.setTextFill(Color.WHITE);
		  label5.setTextFill(Color.WHITE);
		  age.getChildren().add(label1);
		  contact.getChildren().add(label2);
		  sex.getChildren().add(label3);
		  email.getChildren().add(label4);
		 }
	}	
	
	public void gotoUpdateUserDetails() throws IOException {
		
		AnchorPane pane = FXMLLoader.load(getClass().getResource("/edu/iit/view/ManageUserDetailsPage2.fxml"));
		rootPane.getChildren().setAll(pane);
		
		
	}
	
	public void goback() throws IOException {
		AnchorPane pane = FXMLLoader.load(getClass().getResource("/edu/iit/view/Basic.fxml"));
		rootPane.getChildren().setAll(pane);
		
	}

	

}
