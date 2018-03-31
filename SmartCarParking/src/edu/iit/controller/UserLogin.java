package edu.iit.controller;

import java.io.IOException;

import javax.swing.JOptionPane;

import edu.iit.dao.UserLoginDAO;
import edu.iit.model.LoginDetails;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

public class UserLogin extends LoginDetails {

	@FXML
	private AnchorPane rootpane;
	private UserLoginDAO userLogin=null;
	
	
	public UserLogin(){
		userLogin = new UserLoginDAO();
	}
	public void Login() {
		setuserName();
		setpassWord();
		setUserid();
		
		if(userLogin.ValidateTypeofUser("User",new UserLogin())){
		typeofUser="User";
		}
		else{
			System.err.println("Login Credentials didnt matched for User");
			System.exit(0);
		}
		if (!(userName == "" || userName.isEmpty() || Password == "" || Password.isEmpty() || Password == null
				|| userName == null)) {
		
			if (userLogin.checkuser(new UserLogin())) {
				userLayout();
			} else {
				JOptionPane.showMessageDialog(null, "Please enter correct credentials");
				Back();

			}
		}
	}

	public void userLayout() {

		try {
			AnchorPane pane = FXMLLoader.load(getClass().getResource("/edu/iit/view/Basic.fxml"));
			rootpane.getChildren().setAll(pane);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void Back() {
		try {
			AnchorPane pane = FXMLLoader.load(getClass().getResource("/edu/iit/view/Welcomepage.fxml"));
			rootpane.getChildren().setAll(pane);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
