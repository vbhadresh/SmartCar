package edu.iit.controller;

import java.io.IOException;

import javax.swing.JOptionPane;

import edu.iit.dao.ManageSlotsDAO;
import edu.iit.dao.UserLoginDAO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

public class AdminLogin extends UserRegisteration {
	
	@FXML
	private AnchorPane rootpane;
	
	
	private UserLoginDAO userLogin=null;
	public AdminLogin(){
		userLogin = new UserLoginDAO();
	}
	public void Login(){
		
		setuserName();
		setpassWord();
		setUserid();
		
		if(userLogin.ValidateTypeofUser("Admin",new AdminLogin())){
			typeofUser="Admin";
			}
			else{
				System.err.println("Login Credentials didnt matched for User");
				System.exit(0);
			}
		if(!(userName=="" || userName.isEmpty() ||Password=="" || Password.isEmpty() ||Password==null||userName==null)){
			if(userLogin.checkuser(new AdminLogin())){
				//userLogin.close();
					AdminLayout();
			}
			else{
					JOptionPane.showMessageDialog(null,"Please enter correct credentials");
					Back();
					
				}
			}
	}
	
	public void AdminLayout(){
		
		try {
			AnchorPane pane= FXMLLoader.load(getClass().getResource("/edu/iit/view/AdminPage.fxml"));
			rootpane.getChildren().setAll(pane);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void Back(){
		try {
			AnchorPane pane= FXMLLoader.load(getClass().getResource("/edu/iit/view/Welcomepage.fxml"));
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

}
