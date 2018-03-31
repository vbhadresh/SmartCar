package edu.iit.controller;
import java.io.IOException;
import java.util.Optional;

import edu.iit.controller.UserRegisteration;
import edu.iit.dao.CarModelDAO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
public class AddCar {
	@FXML
	private AnchorPane rootpane;
	public AddCar(){
		userDetails= new UserRegisteration();
		Login= new UserLogin();
	}
	
	public void UpdateCarDetails() throws InterruptedException{
		try {
			setCarModel();
			setCarName();
			CarModelDAO carModel= new CarModelDAO();
			if(carModel.AddCarDetails(new AddCar())){
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Confirmation Dialog");
			alert.setHeaderText(null);
			alert.setContentText("Your Car Details have added successfully. Click on OK to continue Booking slot or Cancel to Logout");
			ButtonType buttonTypeOne = new ButtonType("OK");
			ButtonType buttonTypeTwo = new ButtonType("Cancel");
			alert.getButtonTypes().setAll(buttonTypeOne,buttonTypeTwo);
			Optional<ButtonType> result = alert.showAndWait();
			if (result.get().getText() .equals(ButtonType.OK.getText())){
				Back();
			} else {
				Logout();
			}
			}
			else{
				new Exception("Each User Can Update only car Details ");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			new Exception("Each User Can Update only car Details");
		}
	}
	
	public void Back(){
		try {
			AnchorPane pane = FXMLLoader.load(getClass().getResource("/edu/iit/view/Basic.fxml"));
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
	
	public String setCarName(){
		return carName=sp_carName.getText();
	}
	
	public String setCarModel(){
		return carModel=sp_carModel.getText();
	}
	
	public String getUserName(){
		return userDetails.getuserName();
	}
	
	public String getCarName(){
		return carName;
	}
	public String getCarModel(){
		return carModel;
	}

	public int getUserID(){
		return userDetails.getuserId();
	}
	private static int sp_userid;
	@FXML
	private  TextField sp_carName;
	@FXML
	private  TextField sp_carModel;
	
	private static String carName;
	private static String carModel;
	@FXML
	public  static Stage primaryStage;
	private UserRegisteration userDetails=null;
	private UserLogin Login=null;
	
	
}
