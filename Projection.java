package application;

/** 
 * This class is used to populate the CBooking TableView
 * in the customer profile.
 *
 * @author Kristiyan Tomov
 */

public class Projection{
	
	/**
	 * The title of the movie
	 */
	private String title;
	
	/**
	 * The time of the projecion
	 */
	private String time;
	
	/**
	 * The description of the movie
	 */
	private String description;
	
	/**
	 * The date of the projection
	 */
	private String date;
	
	/**
	 * Available seats at the projection
	 */
	private int seatsAvailable;
	
	/**
	 * Taken seats at the projection
	 */
	private int seatsBooked;
	
	/**
	 * Constructor that initialises the object
	 * @param title
	 * @param time
	 * @param description
	 */
	Projection(String title, String time, String description){
		this.title = title;
		this.time = time;
		this.description = description;
		
		//this.date = date;
		//this.seatsAvailable = seatsAvailable;
		//this.seatsBooked = seatsBooked;
		
	}
	
	/**
	 * Set method for the title
	 * @param title
	 */
	public void setTitle(String title){
		this.title = title;
	}
	
	/**
	 * Get method for the title
	 * @return String
	 */
	public String getTitle(){
		return this.title;
	}
	
	/**
	 * Set method for the date
	 * @param date
	 */
	
	public void setDate(String date){
		this.date = date;
	}
	
	/**
	 * Get method for the date
	 * @return String
	 */
	public String getDate(){
		return this.date;
	}
	
	/**
	 * Set method for the time
	 * @param time
	 */
	public void setTime(String time){
		this.time = time;
	}
	
	/**
	 * Get method for the time
	 * @return String
	 */
	public String getTime(){
		return this.time;
	}
	/**
	 * Set method for the description
	 * @param description
	 */
	public void setDescription(String description){
		this.description = description;
	}
	
	/**
	 * Get method for the description
	 * @return String
	 */
	public String getDescription(){
		return this.description;
	}
	
	/**
	 * Set method for the available seats
	 * @param seatsAvailable
	 */
	public void setSeatsAvailable(int seatsAvailable){
		this.seatsAvailable = seatsAvailable;
	}
	
	/**
	 * Get method for the available seats
	 * @return int
	 */
	public int getSeatsAvailable(){
		return this.seatsAvailable;
	}
	
	/**
	 * Set method for the booked seats
	 * @param seatsBooked
	 */
	public void setSeatsBooked(int seatsBooked){
		this.seatsBooked = seatsBooked;
	}
	
	/**
	 * Get method for the booked seats
	 * @return int 
	 */
	public int getSeatsBooked(){
		return this.seatsBooked;
	}
	
}
