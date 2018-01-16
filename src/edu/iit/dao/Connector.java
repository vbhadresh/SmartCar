/**Controller.java 10/01/2017 4:32 pm
 *Program to Database Connectivity operations.
 *Programmer: Vaishnavi Bhadresh , FileName: Connector.java , Lab Number: Lab 4
 * @author vaish
 */
// packageName
package edu.iit.dao;
// import statements
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connector {
	// Declaring Connection Variable
	public static Connection connection= null;
	
	public Connector(){
		try {
			// Invoking Connection method to esttablish connection to Database
			System.out.println("Trying to establish MySQL JDBC Driver connection");
		} catch (Exception e) {
			System.err.println("Failed to Connect to Database");
	}
	}
	// Method to connect to Database
	public Connection getConnection() throws SQLException, ClassNotFoundException{
			 Class.forName("com.mysql.jdbc.Driver");
			 System.out.println("MySQL JDBC Driver Registered");
			 return connection = DriverManager.getConnection("jdbc:mysql://www.papademas.net:3306/510labs?", "db510","510");
	}
}
