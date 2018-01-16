package edu.iit.model;

import java.io.IOException;

import edu.iit.controller.BookSlot;
import edu.iit.controller.ConfirmBookingSlotController;
import edu.iit.dao.ManageSlotsDAO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class Payment extends BookSlot {
	
	@FXML
	public AnchorPane rootPane1;

	public int pd_amt;
	public String pd_emailID="";
	public String pd_payID="";
	public String bs_apt="";
	public String bs_mail="";
	public int book_id;
	private  static int available_slots;
	
	@FXML
	private TextField cardnumber;
	
	@FXML
	private TextField cardexpirymonth;
	
	@FXML
	private TextField cardexpiryyear;
	
	@FXML
	private TextField cardcvv;
	
	@FXML
	private TextField cardname;
	
	@FXML
	private CheckBox chek;
	
	private ConfirmBookingSlotController confirmSlot=new ConfirmBookingSlotController();
	private ManageSlotsDAO manage=new ManageSlotsDAO();
	
	public void confirmPayment(){
		available_slots=manage.getAvaialbleSLots(getCategory(),getView());
		available_slots=available_slots-1;
		manage.updateSlots();
		
	}
	public int getAvailableslots(){
		return available_slots;
	}
	
	public String getCategory(){
		return confirmSlot.getCategory();
	}
	public String getView(){
		return confirmSlot.getView();
	}
	
	public void validateFields() throws IOException {
		
		String validatecard="^[1-9][0-9]{15}";
		if(cardnumber.getText().matches((validatecard))) {
			
			expiryMonth();
		
		}
		else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Card Error");
			alert.setHeaderText("Card number input is wrong");
			alert.setContentText("Please enter 16-digits ONLY!");

			alert.showAndWait();
		}
	}
	
	public void expiryMonth() throws IOException {
		
		String validatemonth="^[0-9][0-9]";
		if(cardexpirymonth.getText().matches((validatemonth)) && Integer.parseInt(cardexpirymonth.getText()) < 13 ) {
			expiryYear();
		}
		else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Month on Card Error");
			alert.setHeaderText("Card expiry month input is wrong");
			alert.setContentText("Please enter 2-digits between 01-12 ONLY!");

			alert.showAndWait();
			
		}
	}
	
	public void expiryYear() throws IOException {
		
		String validateyear="^[1-9][0-9]{3}";
		if(cardexpiryyear.getText().matches((validateyear)) && ((Integer.parseInt(cardexpiryyear.getText()) > 2016) && (Integer.parseInt(cardexpiryyear.getText()) < 2030 ))) {
			cvv();
		}
		else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Year on Card Error");
			alert.setHeaderText("Card expiry year input is wrong");
			alert.setContentText("Please enter 4-digits between 2017-2030 ONLY!");

			alert.showAndWait();
			
		}
		
	}
	
	public void cvv() throws IOException {
		
		String validatecvv="^[1-9][0-9]{2}";
		if(cardcvv.getText().matches((validatecvv))) {
			cardName();
		}
		else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("CVV/C2V on Card Error");
			alert.setHeaderText("Card security number input is wrong");
			alert.setContentText("Please enter 3-digits ONLY!");

			alert.showAndWait();
			
		}
		
	}
	
	public void cardName() throws IOException {
		
		String nameoncard="^[a-zA-Z]+";
		if(cardname.getText().matches((nameoncard)) && cardname.getText()!="") {
			tickbox();
		}
		else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Name on Card Error");
			alert.setHeaderText("Card name input is wrong");
			alert.setContentText("Please enter alphabets ONLY!");

			alert.showAndWait();
			
		}
		
	}
	
	public void tickbox() throws IOException {
		
		if (chek.isSelected()) {
			Label label1= new Label();
			String amount=Integer.toString(new ConfirmBookingSlotController().getpayableAmount())+"$";
			label1.setText(amount);
			label1.setTextFill(Color.WHITE);
			calculatedamount.getChildren().add(label1);
			paymentSuccessful();
			confirmPayment();
		}
		else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Check Box");
			alert.setContentText("Please check the box to confirm payment");

			alert.showAndWait();
		}
		
	}
	
	public void paymentSuccessful() throws IOException {
		
		AnchorPane pane = FXMLLoader.load(getClass().getResource("/edu/iit/view/PaymentSuccessful.fxml"));
		rootPane1.getChildren().setAll(pane);
	}
	
	public void returntomain() throws IOException {
		
		AnchorPane pane = FXMLLoader.load(getClass().getResource("/edu/iit/view/Basic.fxml"));
		rootPane1.getChildren().setAll(pane);
	}
	
	@FXML
	private VBox calculatedamount;
}
