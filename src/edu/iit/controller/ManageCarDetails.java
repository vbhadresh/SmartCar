package edu.iit.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import edu.iit.dao.CarModelDAO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class ManageCarDetails {
	
	@FXML
	public AnchorPane rootPane;
	
	@FXML
	private VBox dispCarModel;
	
	@FXML
	private VBox dispCarNumber;

	CarModelDAO conntoCarModelDAO=null;
	
	public ManageCarDetails(){
		 conntoCarModelDAO = new CarModelDAO();
	}
	
	public void existing_Details(){
		 Label label1= new Label();
		 Label label2= new Label();
		  ArrayList<String> carDetails = conntoCarModelDAO.displaycarDetails();
		  if(carDetails.isEmpty()){
			  label1.setText("Car Details are not updated in your Profile.Please update it by Clicking on Update Button Below.");
			  label1.setTextFill(Color.WHITE);
			  dispCarModel.getChildren().clear();
			  dispCarModel.getChildren().add(label1);
		  }
		 else{
		  dispCarModel.getChildren().clear();
		  dispCarNumber.getChildren().clear();
		  label1.setText(carDetails.get(0));
		  label2.setText(carDetails.get(1));
		  label1.setTextFill(Color.WHITE);
		  label2.setTextFill(Color.WHITE);
		  dispCarModel.getChildren().add(label1);
		  dispCarNumber.getChildren().add(label2);
		 }
	}
	@FXML
	private TextField feedCarModel;
	@FXML
	private TextField feedCarNumber;
	
	
	public void updateCarModel_Number(){
		String carModel=feedCarModel.getText();
		String carNumber=feedCarNumber.getText();
		conntoCarModelDAO.updateCarDetails(carModel,carNumber);
		existing_Details();
		
	}
	
	public void goback() throws IOException {
		
		//write code to go back by 1 step in the application
		//objLogin.userLogin();
		AnchorPane pane = FXMLLoader.load(getClass().getResource("/edu/iit/view/Basic.fxml"));
		rootPane.getChildren().setAll(pane);
	}

	
}
