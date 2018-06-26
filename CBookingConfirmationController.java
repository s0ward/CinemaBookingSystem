

package application;

import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * This class is the controller for the CBookingConfirmation page
 * @author Kristiyan Tomov
 *
 */

public class CBookingConfirmationController {
	
	/**
	 * A reference to the TableView with fx:id "table" in the corresponding fxml file
	 */
	@FXML
	private TableView table;
	
	/**
	 * A reference to the TableColumn with fx:id "titleColumn" in the corresponding fxml file
	 */
	@FXML
	private TableColumn titleColumn;
	
	/**
	 * A reference to the TableColumn with fx:id "dateColumn" in the corresponding fxml file
	 */
	@FXML
	private TableColumn dateColumn;
	
	/**
	 * A reference to the TableColumn with fx:id "timeColumn" in the corresponding fxml file
	 */
	@FXML
	private TableColumn timeColumn;
	
	/**
	 * A reference to the TableColumn with fx:id "seatColumn" in the corresponding fxml file
	 */
	@FXML
	private TableColumn seatColumn;
	
	/**
	 * The id of the user(customer)
	 */
	private String id;
	
	/**
	 * The title of the movie that is being booked
	 */
	private String title;
	
	/**
	 * The time of the booked projection
	 */
	private String time;
	
	/**
	 * The date of the booked projection
	 */
	private String date;
	
	/**
	 * The list of bookings that have been made.
	 * They could be more than one because ordering 3 seats for example
	 * is considered as doing 3 bookings since 1 booking allows for only one seat.
	 * The maximum number is less or equal to 12 which is the total number of seats.
	 */
	private ArrayList<Booking> bookings = new ArrayList<Booking>(12);
	
	/**
	 * Set method for the customer id
	 */
	public void setId(String id){
		this.id = id;
	}
	
	/**
	 * Set method for the movie title
	 */
	public void setTitle(String title){
		this.title = title;
	}
	
	/**
	 * Set method for the time
	 */
	public void setTime(String time){
		this.time = time;
	}
	
	/**
	 * Set method for the date
	 */
	public void setDate(String date){
		this.date = date;
	}
	
	/**
	 * Appends a booking to the list of bookings {@link CBookingController#bookings}
	 */
	public void addBooking(Booking b){
		bookings.add(b);
	}
	
	/**
	 * Loads the bookings from {@link CBookingController#bookings} to the TableView {@link CBookingController.table} 
	 */
	public void loadBookings(){
		try{
		Database dB = new Database();
		
		titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
		dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
		timeColumn.setCellValueFactory(new PropertyValueFactory<>("time"));
		seatColumn.setCellValueFactory(new PropertyValueFactory<>("seat"));
		
		for(Booking b: bookings) table.getItems().add(b);
		}catch(Exception e){
			System.out.println("Cannot connect to the database...");
		}
	}
	
	/**
	 * Goes back to the previous page. In order to do so it passes the required arguments and initialises it.
	 */
	public void back(ActionEvent e){
		
		try{
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/CBooking.fxml"));
		Parent root = loader.load();
		CBookingController CBCtll = loader.getController();
		
		CBCtll.setId(id);
		CBCtll.setTitle(title);
		CBCtll.setTime(time);
		CBCtll.setDate(date);
		
		CBCtll.setText();
		CBCtll.setSeats();
		CBCtll.setMovieImage();
		
		((Button) e.getSource()).getScene().setRoot(root);
		}catch(Exception ex){
			System.out.println("Invalid fxml file...");
		}
	}
	
	/**
	 * 
	 * Goes back to the home page. In order to do so it passes the required arguments and initialises it.
	 */
	public void home(ActionEvent e){
		
		try{
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/CHome.fxml"));
		Parent root = loader.load();
		CHomeController CHCtll = loader.getController();
		
		CHCtll.setId(id);
		CHCtll.setText();
		
		((Button) e.getSource()).getScene().setRoot(root);
		}catch(Exception ex){
			System.out.println("Invalid fxml file...");
		}
		
	}
	
	/**
	 * Goes to the login page.
	 */
	public void logOut(ActionEvent e){
		
		try{
		Parent root = FXMLLoader.load(getClass().getResource("/application/Login.fxml"));
		((Button) e.getSource()).getScene().setRoot(root);
		}catch(Exception ex){
			System.out.println("Invalid fxml file...");
		}
	}
	

	
}
