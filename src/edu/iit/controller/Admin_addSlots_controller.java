package edu.iit.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import edu.iit.dao.ManageSlotsDAO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;

public class Admin_addSlots_controller {

	public static int total_Slots = 10;
	public static String categoryName = "";
	public static int categoryId;
	public static int available_Slots;

	@FXML
	private AnchorPane rootpane;

	public void selectCategory() {
		try {
			AnchorPane pane = FXMLLoader.load(getClass().getResource("/edu/iit/view/Admin_BookaSlotPage2.fxml"));
			rootpane.getChildren().setAll(pane);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void selectMall() {

		try {
			AnchorPane pane = FXMLLoader.load(getClass().getResource("/edu/iit/view/Admin_BookaSlotPage2.4_Malls.fxml"));
			rootpane.getChildren().setAll(pane);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void selectPublicPlace() {
		try {
			AnchorPane pane = FXMLLoader
					.load(getClass().getResource("/edu/iit/view/Admin_BookaSlotPage2.3_PublicPlace.fxml"));
			rootpane.getChildren().setAll(pane);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void selecthospital() {
		try {
			AnchorPane pane = FXMLLoader.load(getClass().getResource("/edu/iit/view/Admin_BookaSlotPage2.2_Hospitals.fxml"));
			rootpane.getChildren().setAll(pane);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void selectapartment() {
		try {
			AnchorPane pane = FXMLLoader.load(getClass().getResource("/edu/iit/view/Admin_BookaSlotPage2.1_Apartments.fxml"));
			rootpane.getChildren().setAll(pane);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void cancel() {
		try {
			AnchorPane pane = FXMLLoader.load(getClass().getResource("/edu/iit/view/AdminPage.fxml"));
			rootpane.getChildren().setAll(pane);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public void Logout(){
		
		LogoutController logout= new LogoutController();
		logout.Logout();
		try {
			AnchorPane pane= FXMLLoader.load(getClass().getResource("/edu/iit/view/Welcomepage.fxml"));
			rootpane.getChildren().setAll(pane);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void editTariff(){
		ManageSlotsDAO manageSlots = new ManageSlotsDAO();
		String categoryID=getInput("Please Enter the Category ID for the Venue you want to update Tariffs"+"\n ",
	"Enter 1- Mall "+"\n"+" Enter 2- PublicPlace"+"\n"+"Enter 3- Apartment"+"\n"+"Enter 4 - Hospital"+"\n") ;
		String tariff= getInput("Please Enter the updated Tariff amount", "Tariff");
		manageSlots.updateTariff(categoryID, Integer.parseInt(tariff));
	}
	
	public String getInput(String Title,String text){
		TextInputDialog dialog = new TextInputDialog(Title);
		dialog.setHeaderText(Title);
		dialog.setContentText(text);
		Optional<String> result = dialog.showAndWait();
		return result.get();
	}
	
	public void GoBack(){
		AnchorPane pane;
		try {
			pane = FXMLLoader.load(getClass().getResource("/edu/iit/view/Admin_BookaSlotPage2.fxml"));
			rootpane.getChildren().setAll(pane);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		}

	public String getCategoryName() {
		return categoryName;
	}

	public int getCategoryID() {
		return categoryId;
	}

	public int getTotalSLots() {
		return total_Slots;
	}

	public int getAvailableSLots() {
		return available_Slots;
	}
	
	public void confirmBlock37Slot(){
		this.categoryName="Mall";
		this.categoryId=1;
		addSlots("Block37");
	}
	public void confirmoakSlot() {
		this.categoryName="Mall";
		this.categoryId=1;
		addSlots("OakBrook");;
	}

	public void confirmwaterTowerSlot() {
		this.categoryName="Mall";
		this.categoryId=1;
		addSlots("WaterTower");;
	}

	public void confirmWoodFieldSlot() {
		this.categoryName="Mall";
		this.categoryId=1;
		addSlots("WoodField");
	}
	
	/*
	 * Apartments
	 */

	public void confirmPrairieShores(){
		this.categoryName="Apartment";
		this.categoryId=3;
		addSlots("PrairieShores");;
	}
	public void confirmPresidentialTower(){
		this.categoryName="Apartment";
		this.categoryId=3;
		addSlots("PresidentialTowers");;
	}
	public void confirmLakeMeadows(){
		this.categoryName="Apartment";
		this.categoryId=3;
		addSlots("LakeMeadows");;
	}
	public void confirmSouthCommons(){
		this.categoryName="Apartment";
		this.categoryId=3;
		addSlots("SouthCommons");;
	}

	/*
	 * Hospitals
	 */
	public void confirmMercy(){
		this.categoryName="Hospital";
		this.categoryId=4;
		addSlots("Mercy");;
	}
	public void confirmRush(){
		this.categoryName="Hospital";
		this.categoryId=4;
		addSlots("RushUniversity");;
	}
	public void confirmLoyola(){
		this.categoryName="Hospital";
		this.categoryId=4;
		addSlots("LoyalaUniversity");;
	}
	public void confirmNorthWestern(){
		this.categoryName="Hospital";
		this.categoryId=4;
		addSlots("NorthWestern");;
	}
	/*
	 * Public Places
	 */
	public void  confirmShedd(){
		this.categoryName="PublicPlace";
		this.categoryId=2;
		addSlots("SheddAquairum");
	}
	public void  confirmMiilennium(){
		this.categoryName="PublicPlace";
		this.categoryId=2;
		addSlots("MillineuimPark");
	}
	public void  confirmCulturalCenter(){
		this.categoryName="PublicPlace";
		this.categoryId=2;
		addSlots("ChicagoCulturalCenter");
	}
	public void confirmPlanetorium(){
		this.categoryName="PublicPlace";
		this.categoryId=2;
		addSlots("AdlerPlanetorium");
	}
	
	public void addSlots(String place) {
		this.available_Slots = this.total_Slots;
		ManageSlotsDAO manageSlots = new ManageSlotsDAO();
		switch (this.categoryName) {
		case "Mall":
			manageSlots.addSlots(new Admin_addSlots_controller(), place);
			manageSlots.set_tariff(45,categoryId);
			displayMsg(place);
			break;
		case "PublicPlace":
			manageSlots.addSlots(new Admin_addSlots_controller(), place);
			displayMsg(place);
			manageSlots.set_tariff(45,categoryId);
			break;
		case "Apartment":
			manageSlots.addSlots(new Admin_addSlots_controller(), place);
			manageSlots.set_tariff(45,categoryId);
			displayMsg(place);
			break;
		case "Hospital":
			manageSlots.addSlots(new Admin_addSlots_controller(), place);
			manageSlots.set_tariff(45,categoryId);
			displayMsg(place);
			break;
		}
	}

	
	private void displayMsg(String place){
		Alert alert= new Alert(AlertType.INFORMATION);
		alert.setTitle("Confirmation Dialog");
		alert.setHeaderText("Added Slot Details to DataBase for "+place);
		alert.showAndWait();
	}
	
	public void set_tariff(ManageSlotsDAO manageSlots, String place){
		
		
	}
}
