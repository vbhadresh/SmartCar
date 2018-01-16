package edu.iit.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import edu.iit.dao.ManageSlotsDAO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class BookSlot extends UserLayout{
	
	@FXML
	private AnchorPane rootpane;
	
	
	public void selectCategory() {
		//selectSlot();
		try {
			AnchorPane pane= FXMLLoader.load(getClass().getResource("/edu/iit/view/BookaSlotPage2.fxml"));
			rootpane.getChildren().setAll(pane);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void selectMall() {
		
		try {
			AnchorPane pane= FXMLLoader.load(getClass().getResource("/edu/iit/view/BookaSlotPage2.4_Malls.fxml"));
			rootpane.getChildren().setAll(pane);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void selectPublicPlace() {
		try {
			AnchorPane pane= FXMLLoader.load(getClass().getResource("/edu/iit/view/BookaSlotPage2.3_PublicPlace.fxml"));
			rootpane.getChildren().setAll(pane);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void selecthospital() {
		try {
			AnchorPane pane= FXMLLoader.load(getClass().getResource("/edu/iit/view/BookaSlotPage2.2_Hospitals.fxml"));
			rootpane.getChildren().setAll(pane);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void selectapartment() {
		try {
			AnchorPane pane= FXMLLoader.load(getClass().getResource("/edu/iit/view/BookaSlotPage2.1_Apartments.fxml"));
			rootpane.getChildren().setAll(pane);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	public void cancel(){
		try {
			AnchorPane pane= FXMLLoader.load(getClass().getResource("/edu/iit/view/Basic.fxml"));
			rootpane.getChildren().setAll(pane);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
