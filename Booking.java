package application;

/** 
 * This class is used to populate the bookings TableView
 * in the customer profile.
 *
 * @author Kristiyan Tomov
 */
public class Booking {
	
	/**
	 * The title of the movie
	 */
	private String title;
	
	/**
	 * The date of the projection
	 */
	private String date;
	
	/**
	 * The time of the projection
	 */
	private String time;
	
	/**
	 * The cinema room seat for the given projection/booking
	 */
	private String seat;
	
	/**
	 * Constructor which initialises a booking
	 */
	Booking(String title, String date, String time, String seat){
		this.title=title;
		this.date=date;
		this.time=time;
		this.seat=seat;
	}
	
	/**
	 * Get method for the title
	 * @return String
	 */
	public String getTitle(){
		return title;
	}
	
	/**
	 * Set method for the title
	 * 
	 * @param newTitle
	 */
	public void setTitle(String newTitle){
		title = newTitle;
	}
	
	/**
	 * Get method for the date
	 * 
	 * @return String
	 */
	public String getDate(){
		return date;
	}
	
	/**
	 * Set method for the date
	 * 
	 * @param newDate
	 */
	public void setDate(String newDate){
		date = newDate;
	}
	
	/**
	 * Get method for the time
	 *
	 * @return String
	 */
	public String getTime(){
		return time;
	}
	
	/**
	 * Set method for the time
	 * 
	 * @param newTime
	 */
	public void setTime(String newTime){
		time = newTime;
	}
	
	/**
	 * Get method for the seat
	 * 
	 * @return String
	 */
	public String getSeat(){
		return seat;
	}
	
	/**
	 * Set method for the seat
	 * 
	 * @param newSeat
	 */
	public void setSeat(String newSeat){
		seat = newSeat;
	}
	
	/**
	 * Overriding the Object's toString() method just in case 
	 * we need to print the contents in a readable format. 
	 * 
	 * @return String
	 */
	public String toString(){
		return "Title: " + title + " Date: " + date + " Time: " + time + " Seat: " + seat+"\n";
	}
}
