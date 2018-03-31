package edu.iit.model;

import java.io.IOException;

import edu.iit.model.LoginDetails;
import edu.iit.controller.ConfirmBookingSlotController;
import edu.iit.dao.BookingHistoryDAO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;

public class PaymentSuccess {
	
	@FXML
	public AnchorPane rootPane2;
	private BookingHistoryDAO bookingHistory=null;
	
	public PaymentSuccess(){
		bookingHistory=new BookingHistoryDAO();
	}
	public void redirect() throws IOException {
		
		bookingHistory.updateBookingDetails();
		Alert alert=new Alert(AlertType.INFORMATION);
		alert.setTitle("Payment Confirmation Dialog");
		String amount=Integer.toString(new ConfirmBookingSlotController().getpayableAmount())+"$";
		alert.setContentText("Amount Paid: "+amount);
		alert.setHeaderText("Your Slot has been booked successfully. To check your booking details in Booking History click on Ok");
		alert.showAndWait();
		AnchorPane pane = FXMLLoader.load(getClass().getResource("/edu/iit/view/Basic.fxml"));
		rootPane2.getChildren().setAll(pane);
	}

	
	
}
