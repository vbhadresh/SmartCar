package application;
	
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import com.sun.javafx.stage.StageHelper;

import edu.iit.dao.daoModel;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			Parent root= FXMLLoader.load(getClass().getResource("/edu/iit/view/Welcomepage.fxml"));
			Scene scene = new Scene(root);
			//Main.class.getResource("Default.css").toExternalForm();
			primaryStage.setTitle("Smart Car Parking System");
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		String timeStamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar.getInstance().getTime());
		System.out.println("Current Date = " + timeStamp + "\nProgrammed by Vaishnavi Bhadresh\n");
		try{
		if(daoModel.connection==null){
			daoModel daoModel= new daoModel();
			daoModel.createTables();
				}
				else{
					daoModel.connection.close();
				}
			
		} catch (Exception e) {
			System.err.println("Error in creating Tables");
		}
		launch(args);
	}
}
