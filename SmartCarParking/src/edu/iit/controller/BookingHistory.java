package edu.iit.controller;

import edu.iit.dao.BookingHistoryDAO;

public class BookingHistory extends UserLayout {
	
	private String bookdate;
	private String bookvenue;
	private String carmodel;
	private String carname;
	private int sp_userid;
	public int amountpaid;
	
	public void displayBookingHistory(){
		
		BookingHistoryDAO bookingHistory= new BookingHistoryDAO();
		bookingHistory.displayBookingHistory();
		
	}
	
	/*SELECT t1.ID
	FROM Table1 t1
	WHERE NOT EXISTS (SELECT t2.ID FROM Table2 t2 WHERE t1.ID = t2.ID)*/
}
