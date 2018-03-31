package edu.iit.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.Optional;

import javax.swing.JOptionPane;

import com.mysql.jdbc.ResultSet;

import edu.iit.dao.CarModelDAO;
import edu.iit.dao.ManageSlotsDAO;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class ConfirmBookingSlotController {

	private static String view = "";
	private static String category="";
	@FXML
	private AnchorPane rootpane;
	private ManageSlotsDAO manage=null;
	private static int payable_amt=0;
	private static String date;
	private CarModelDAO carModel=null;
	
	public ConfirmBookingSlotController(){
		manage=new ManageSlotsDAO();
		carModel = new CarModelDAO();
	}
	public void Confirm(){
		if(date!=null && !(carModel.displaycarDetails().isEmpty())){
		try {
			AnchorPane pane= FXMLLoader.load(getClass().getResource("/edu/iit/view/PaymentPage.fxml"));
			rootpane.getChildren().setAll(pane);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		else{
			AlertWarningMsg("Need Booking Details to Confirm Slot!!!!... Please Enter it By Clicking on Enter Details");
		}
	}
	
	
	public String getView(){
		return view;
	}
	
	public String getCategory(){
		return category;
	}
	
	public int getpayableAmount(){
		return payable_amt;
	}
	
	public String getDate(){
		return date;
	}
	/*
	 * Mall Places
	 */
	public void confirmBlock37Slot(){
		category="Mall";
		view = "Block37";
		LoadStage();
	}
	public void confirmoakSlot() {
		category="Mall";
		view = "OakBrook";
		LoadStage();
	}

	public void confirmwaterTowerSlot() {
		category="Mall";
		view = "WaterTower";
		LoadStage();
	}

	public void confirmWoodFieldSlot() {
		category="Mall";
		view = "Woodfield";
		LoadStage();
	}
	
	/*
	 * Apartments
	 */

	public void confirmPrairieShores(){
		category="Apartment";
		view = "PrairieShores";
		LoadStage();
	}
	public void confirmPresidentialTower(){
		category="Apartment";
		view = "PresidentialTowers";
		LoadStage();
	}
	public void confirmLakeMeadows(){
		category="Apartment";
		view = "LakeMeadows";
		LoadStage();
	}
	public void confirmSouthCommons(){
		category="Apartment";
		view = "SouthCommons";
		LoadStage();
	}

	/*
	 * Hospitals
	 */
	public void confirmMercy(){
		category="Hospital";
		view = "Mercy";
		LoadStage();
	}
	public void confirmRush(){
		category="Hospital";
		view = "RushUniversity";
		LoadStage();
	}
	public void confirmLoyola(){
		category="Hospital";
		view = "LoyalaUniversity";
		LoadStage();
	}
	public void confirmNorthWestern(){
		category="Hospital";
		view = "NorthWestern";
		LoadStage();
	}
	/*
	 * Public Places
	 */
	public void  confirmShedd(){
		category="PublicPlace";
		view = "SheddAquairum";
		LoadStage();
	}
	public void  confirmMiilennium(){
		category="PublicPlace";
		view = "MillineuimPark";
		LoadStage();
	}
	public void  confirmCulturalCenter(){
		category="PublicPlace";
		view = "ChicagoCulturalCenter";
		LoadStage();
	}
	public void confirmPlanetorium(){
		category="PublicPlace";
		view = "AdlerPlanetorium";
		LoadStage();
	}
	
	public void View() {
	
		ArrayList<String> cararr = carModel.displaycarDetails();
		ArrayList<String> slotDetails= new ArrayList<String>();
		if (!cararr.isEmpty()) {
			Label label1 = new Label();
			Label label2 = new Label();
			Label label3 = new Label();
			Label label4 = new Label();
			Label label5 = new Label();
			Label label6= new Label();
			Label label7= new Label();
			slotDetails=loadSlot();
			label1.setText("Booking Details :"+"\n");
			label2.setText("carName : " + cararr.get(0) + "\n");
			label3.setText("carModel : " + cararr.get(1) + "\n");
			if(slotDetails!=null){
			label4.setText("TotalSlots : " + slotDetails.get(0) + "\n");
			label5.setText("AvailableSlots : " + slotDetails.get(1) + "\n");
			}
			else{
				new Exception("No Data found for Number of slots");
			}
			setDate();
			label6.setText("Booking Date: "+date);
			payable_amt=calculatepaymentCharges(hours.getText());
			label7.setText("Payable Amount: " +Integer.toString(payable_amt)+"$");
			label1.setTextFill(Color.WHITE);
			label2.setTextFill(Color.WHITE);
			label3.setTextFill(Color.WHITE);
			label4.setTextFill(Color.WHITE);
			label5.setTextFill(Color.WHITE);
			label6.setTextFill(Color.WHITE);
			label7.setTextFill(Color.WHITE);
			vbox1.getChildren().add(label1);
			vbox1.getChildren().add(label2);
			vbox1.getChildren().add(label3);
			vbox1.getChildren().add(label4);
			vbox1.getChildren().add(label5);
			vbox1.getChildren().add(label6);
			vbox1.getChildren().add(label7);
		} else {
			JOptionPane.showMessageDialog(null, "Please add your car details before booking a slot");
		}

	}
	
	public void Back(){
		AnchorPane pane=null;
		try {
			if(category.equals("Apartment")){
				pane= FXMLLoader.load(getClass().getResource("/edu/iit/view/BookaSlotPage2.1_Apartments.fxml"));
			}
			else if(category.equals("PublicPlace")){
				pane= FXMLLoader.load(getClass().getResource("/edu/iit/view/BookaSlotPage2.3_PublicPlace.fxml"));
			}
			else if(category.equals("Hospital")){
				pane= FXMLLoader.load(getClass().getResource("/edu/iit/view/BookaSlotPage2.2_Hospitals.fxml"));
			}
			else if(category.equals("Mall")){
				pane= FXMLLoader.load(getClass().getResource("/edu/iit/view/BookaSlotPage2.4_Malls.fxml"));
			}
			rootpane.getChildren().setAll(pane);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void GoBack(){
		AnchorPane pane;
		try {
			pane = FXMLLoader.load(getClass().getResource("/edu/iit/view/BookaSlotPage2.fxml"));
			rootpane.getChildren().setAll(pane);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		}
	
	public void LoadStage(){
		try {
			AnchorPane pane= FXMLLoader.load(getClass().getResource("/edu/iit/view/ConfirmBookSlotDetails.fxml"));
			rootpane.getChildren().setAll(pane);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public ArrayList<String> loadSlot(){
	
		switch(view){
		case "Block37":return manage.displaySlots("Mall","Block37");
		case "OakBrook":return manage.displaySlots("Mall","OakBrook"); 
		case "WaterTower":return manage.displaySlots("Mall","WaterTower");
		case "WoodField":return manage.displaySlots("Mall","Woodfield"); 
		
		// Apartments
		case "PrairieShores":return manage.displaySlots("Apartment","PrairieShores"); 
		case "PresdentialTowers":return manage.displaySlots("Apartment","PresidentialTowers"); 
		case "LakeMeadows":return manage.displaySlots("Apartment","LakeMeadows"); 
		case "SouthCommons":return manage.displaySlots("Apartment","SouthCommons");
		
		// Hospital
		case "Mercy":return manage.displaySlots("Hospital","Mercy"); 
		case "NorthWestern":return manage.displaySlots("Hospital","NorthWestern"); 
		case "RushUniversity":return manage.displaySlots("Hospital","RushUniversity"); 
		case "LoyalaUniversity":return manage.displaySlots("Hospital","LoyalaUniversity"); 
		
		//Public Places
		case "SheddAquairum":return manage.displaySlots("PublicPlace","SheddAquairum"); 
		case "AdlerPlanetorium":return manage.displaySlots("PublicPlace","AdlerPlanetorium"); 
		case "MillineuimPark":return manage.displaySlots("PublicPlace","MillineuimPark"); 
		case "ChicagoCulturalCenter":return manage.displaySlots("PublicPlace","ChicagoCulturalCenter"); 
		}
		return null;
	}
	
	
	
	/*
	
	private String getBookinghours(){
		String res=getInput("Please enter the Number of hours you want to book a slot","Booking Hours");
				if(res.equals("")){
					 System.exit(0);
				}
				else{
					return res;
				}
			return "";	
	}*/
	
	public void setDate(){
		try {
			String mon=Month();
			String year= Year();
			if(mon!=""){
			date= mon+"/"+year;
			}
			else{
				System.err.println("Please enter correct input for booking date and try again");
				System.exit(0);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
  public String Month() throws IOException {
		
		String validatemonth="^[0-9][0-9]";
		if(month.getText().matches((validatemonth)) && Integer.parseInt(month.getText()) < 13 ) {
			if(Year().equals("")){
				AlertErrorMsg("Error in year Input");
			}
			else{
				return month.getText();
			}
		}
		else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Month on Date Error");
			alert.setHeaderText("Month input is wrong");
			alert.setContentText("Please enter 2-digits between 01-12 ONLY!");
			alert.showAndWait();
			LoadStage();
			
		}
		return "";
	}
	
	public String Year() throws IOException {
		
		String validateyear="^[1-9][0-9]{3}";
		if(year.getText().matches((validateyear)) && ((Integer.parseInt(year.getText()) > 2016) && (Integer.parseInt(year.getText()) < 2030 ))) {
			return year.getText();
		}
		else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Year on Date Error");
			alert.setHeaderText("Year input is wrong");
			alert.setContentText("Please enter 4-digits between 2017-2030 ONLY!");

			alert.showAndWait();
			
		}
		return "";
		
	}
	@FXML
	private TextField year;
	
	@FXML
	private TextField month;
	@FXML
	private TextField hours;
	
private String getBookingHours(){
		
		return hours.getText();
	}
	
	private int calculatepaymentCharges(String bookinghours){
		int hours=Integer.parseInt(bookinghours);
		int tariff=manage.get_tariff(getCategory(),getView());
		int payment_amt=(hours*tariff)/24;
		return payment_amt;
	}
	
	private void AlertWarningMsg(String Msg){
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle("Warning");
		alert.setHeaderText(Msg);
		alert.showAndWait();
	}
	
	private void AlertErrorMsg(String Msg){
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("ERROR");
		alert.setHeaderText(Msg);
		alert.showAndWait();
	}
	@FXML
	private VBox vbox1;
	
}