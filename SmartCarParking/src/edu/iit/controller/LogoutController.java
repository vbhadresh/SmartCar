package edu.iit.controller;

import edu.iit.model.LoginDetails;

public class LogoutController extends LoginDetails{
	
	
	public void Logout(){
		updateuserName();
		updatepassWord();
		updateUserid();
	}
	
	public void updateuserName(){
		 userName= "";
	}
	public void updatepassWord(){
		Password="";
	}
	public void updateUserid(){
		userID=0;
	}

}
