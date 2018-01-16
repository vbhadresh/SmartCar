package edu.iit.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import edu.iit.dao.BookingHistoryDAO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
public class UserLayout{
	
	@FXML
	private AnchorPane rootpane;	
	private static BookingHistoryDAO bookingHistory=null;
	 public UserLayout() {
		// TODO Auto-generated constructor stub
		 bookingHistory= new BookingHistoryDAO();
		
	}
	
	public void AddCarDetails(){
		try {
			AnchorPane pane= FXMLLoader.load(getClass().getResource("/edu/iit/view/AddCarDetailsPage.fxml"));
			rootpane.getChildren().setAll(pane);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void BookaSlot(){
		try {
			AnchorPane pane= FXMLLoader.load(getClass().getResource("/edu/iit/view/BookaSlotPage2.fxml"));
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
	
	public void getLatestBooking(){
			Alert alert = new Alert(AlertType.CONFIRMATION);
			TextInputDialog dialog = new TextInputDialog("Last Booking Place");
			dialog.setTitle("Booking Venue");
			dialog.setHeaderText("Enter any place from the below list "+"\n"+"Malls : WaterTower,Block37,OakBrook,WoodField"
					+"\n"+"Apartments :PrairieShores,PresidentialTowers,LakeMeadows,SouthCommons"+"\n"+"PublicPlace :SheddAquairum,AdlerPlanetorium,ChicagoCulturalCenter,MillineuimPark"+"\n"+"Hospital :Mercy, NorthWestern,RushUniveristy,LoyalaUniversity"+"\n"+"Ex: RushUniveristy"+"\n");
			dialog.setContentText("Please enter your last booking venue");
			// Traditional way to get the response value.
			Optional<String> result = dialog.showAndWait();
			if(result.isPresent()){
			ArrayList<String> bookingDetails=bookingHistory.getLatestBooking(result.get().trim());
			if(!(bookingDetails.isEmpty())){
			alert.setTitle("Booking History Details");
			alert.setContentText("Latest Booking Date : " +bookingDetails.get(0)+"\n "+"Booking Venue : " +
			bookingDetails.get(1)+"\n"+"UserID : " +bookingDetails.get(2) +"\n"+"Click on Cancel to delete this booking");
			alert.showAndWait();
			if(alert.isShowing()){
				System.out.println("");			}
			else{
				bookingHistory.deleteBooking(bookingDetails.get(1));
			}
			}
			else{
				alert.setTitle("Booking History Details");
				alert.setContentText("No details for this Place");
				alert.showAndWait();
			}
	}
	}
	
	
	public void editCarDetails(){
		try {
			AnchorPane pane= FXMLLoader.load(getClass().getResource("/edu/iit/view/ManageCarDetailsPage1.fxml"));
			rootpane.getChildren().setAll(pane);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
	}
	
	public void editUserDetails(){
		try {
			AnchorPane pane= FXMLLoader.load(getClass().getResource("/edu/iit/view/ManageUserDetailsPage1.fxml"));
			rootpane.getChildren().setAll(pane);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	private VBox vbox1;
}
