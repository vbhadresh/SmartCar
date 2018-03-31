	/**Controller.java 10/01/2017 4:32 pm
 *Program to Database CRUD operations.
 *Programmer: Vaishnavi Bhadresh , FileName: daoModel.java , Lab Number: Lab 4
 * @author vaish
 */
// packageName
package edu.iit.dao;
// import statements
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;


public class daoModel {
	// daoModel Constructor to create Connection with Database
	public daoModel(){
		connector=new Connector();
		connectDB();
	}
	
	private void connectDB(){
		try {
			connection=connector.getConnection();
			System.out.println("MySQL JDBC Driver established successfully");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public Connection getConnection(){
		return connection;
	}
	
	// Method to create Table in Database
	public void createTables(){
	try {
		statement=connection.createStatement();
		// verifying whether table with same name exists or not
		DatabaseMetaData dbm=connection.getMetaData();
		ArrayList<String> tables=checkTablesExistence(dbm);
		if(tables.isEmpty()){
			System.out.println("All Table remain unaltered");
		}
		else{
			for(String tab:tables){
				String sql1="";
				switch(tab){
				case "smartparkingbookingcommittee": 
					
					sql1="CREATE TABLE smartparkingbookingcommittee"+ "(sp_userId int(100) NOT NULL,"+
							  "sp_userName varchar(100) DEFAULT NULL,"+
							  "sp_userType varchar(100) DEFAULT NULL,"+
							  "sp_password varchar(100) DEFAULT NULL,"+
							  "PRIMARY KEY (sp_userId))";
					statement.executeUpdate(sql1);
					System.out.println("Table Smart_Parking_Booking_Committee successfully created in the Database!\n");
					break;
				case "bookslot":
					
					sql1="CREATE TABLE bookslot"+"( bs_publicplace varchar(100) DEFAULT NULL,"+
							  "bs_hospital varchar(100) DEFAULT NULL,"+
							  "bs_mall varchar(100) DEFAULT NULL,"+
							  "bs_apt varchar(100) DEFAULT NULL,"+
							  "bookID int(100) NOT NULL,"+
							  "PRIMARY KEY (bookID))";
					break;
				case "bookinghistory": 
					sql1="CREATE TABLE bookinghistory"+ "(bh_bookingDate varchar(100) DEFAULT NULL,"+
							  "bh_bookingVenue int(100) DEFAULT NULL,"+
							  "bh_carId varchar(100) DEFAULT NULL,"+
							  "bh_userId int(100) DEFAULT NULL,"+
							  "KEY bh_userId (bh_userId),"+
							  "CONSTRAINT bookinghistory_ibfk_1 FOREIGN KEY (bh_userId) REFERENCES smartparkingbookingcommittee (sp_userId),"+
							  "CONSTRAINT bookinghistory_ibfk_2 FOREIGN KEY (bh_userId) REFERENCES addcar (carId))"; 
							   statement.executeUpdate(sql1);
							   System.out.println("Table Booking History successfully created in the Database!\n");
					break;
				case "addcar": 
					sql1="CREATE TABLE addcar ("+
							  "ad_carName varchar(100) DEFAULT NULL,"+
							  "ad_carModel varchar(100) DEFAULT NULL,"+
							  "userID int(100) DEFAULT NULL,"+
							  "carId int(100) NOT NULL,"+
							  "userName varchar(100) DEFAULT NULL,"+
							  "PRIMARY KEY (carId),"+
							  "KEY userID (userID),"+
							  "CONSTRAINT addcar_ibfk_1 FOREIGN KEY (userID) REFERENCES smartparkingbookingcommittee (sp_userId))";
							   statement.executeUpdate(sql1);
							   System.out.println("Table Add Car successfully created in the Database!\n");		
					break;
				case "slotdetails":
					sql1="CREATE TABLE slotdetails"+"(sd_totalSlots int(100) DEFAULT NULL,"+
							  "sd_availableSlots int(100) DEFAULT NULL,"+
							  "sd_categoryId int(100) DEFAULT NULL,"+
							  "PRIMARY KEY (sd_categoryId),"+
							  "CONSTRAINT slotdetails  FOREIGN KEY (sd_categoryId) REFERENCES bookslot (bookID))";
					statement.executeUpdate(sql1);
					System.out.println("Table Slot Details successfully created in the Database!\n");
					break;
				case "paymentdetails": 
					sql1="CREATE TABLE paymentdetails ("+
							  "Pd_amount int(100) DEFAULT NULL,"+
							  "Pd_emailId varchar(100) DEFAULT NULL,"+
							  "Pd_payId int(100) NOT NULL,"+
							  "Pd_bookId int(100) DEFAULT NULL,"+
							  "PRIMARY KEY (Pd_payId),"+
							  "KEY Pd_bookId (Pd_bookId),"+
							  "CONSTRAINT paymentdetails_ibfk_1 FOREIGN KEY (Pd_bookId) REFERENCES bookslot (bookID))";
							statement.executeUpdate(sql1);
							System.out.println("Table Payment Details successfully created in the Database!\n");
					break;
				case "parkingratedetails": 
					sql1="CREATE TABLE parkingratedetails ("+
							  "Pd_expectedCost int(100) DEFAULT NULL,"+
							  "Pd_parkId int(100) NOT NULL,"+
							  "Pd_bookId int(100) DEFAULT NULL,"+
							  "PRIMARY KEY (Pd_parkId),"+
							  "KEY Pd_bookId (Pd_bookId),"+
							  "CONSTRAINT parkingratedetails_ibfk_1 FOREIGN KEY (Pd_bookId) REFERENCES bookslot (bookID))";
					statement.executeUpdate(sql1);
					System.out.println("Table Parking Rate Details successfully created in the Database!\n");
					break;
					
				}
			}
			
		}
		
	} catch (Exception e) {
		System.err.println("Failed to create Table"+e);
	}
	}
	
	private ArrayList<String> checkTablesExistence(DatabaseMetaData dbm){
		ArrayList<String> tableNames= new ArrayList<String>();
		ArrayList<String> tabletoCreate = new ArrayList<String>();
		tableNames.add("smartparkingbookingcommittee");
		tableNames.add("bookslot");
		tableNames.add("bookinghistory");
		tableNames.add("addcar");
		tableNames.add("slotdetails");
		tableNames.add("paymentdetails");
		tableNames.add("parkingratedetails");
		for(String table: tableNames){
			try {
				ResultSet tables=dbm.getTables(null, null, table, null);
				if (tables.next()) {
					System.out.println("Table "+ table +" already exists");
				}
				else{
				}
			} catch (SQLException e) {
				System.err.println("Failed in checking the previous existence of table " +table);
			}
		}
		
		return tabletoCreate;
	}
	
	public void close(){
		try{
			connection.close();
			connection=null;
		}
		catch(Exception e){
			System.err.println("Failed to close DB Connection");
		}
	}
	// declaring class variables
	private static Connector connector=null;
	public static Connection connection=null;
	public static Statement statement = null;
	
}
