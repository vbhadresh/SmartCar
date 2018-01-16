package edu.iit.controller;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Welcomepage {
	
	
	FXMLLoader loader;
	@FXML
	private AnchorPane rootpane;
	Scene scene;
	Stage primaryStage=null;
	public void newUser(){
		try {
			AnchorPane pane= FXMLLoader.load(getClass().getResource("/edu/iit/view/Register.fxml"));
			rootpane.getChildren().setAll(pane);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void Back(){
		try {
			AnchorPane pane= FXMLLoader.load(getClass().getResource("/edu/iit/view/WelcomePage.fxml"));
			rootpane.getChildren().setAll(pane);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void existingUser(){
		try {
			AnchorPane pane= FXMLLoader.load(getClass().getResource("/edu/iit/view/Login.fxml"));
			rootpane.getChildren().setAll(pane);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void AdminLogin(){
		try {
			AnchorPane pane= FXMLLoader.load(getClass().getResource("/edu/iit/view/LoginPageAdmin.fxml"));
			rootpane.getChildren().setAll(pane);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@FXML
	private TextField username;

	@FXML
	private TextField password;
}
