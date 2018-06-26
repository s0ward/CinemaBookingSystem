package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;

/**
 * This class is the controller for the CHomeController page
 * @author Kristiyan Tomov
 *
 */
public class CHomeController {
	
	/**
	 * A reference to the Label with fx:id "welcome" in the corresponding fxml file
	 */
	@FXML
	private Label welcome;
	
	/**
	 * A reference to the Label with fx:id "date" in the corresponding fxml file
	 */
	@FXML
	private DatePicker date;
	
	/**
	 * The id of the user(customer)
	 */
	private String id;
	
	/**
	 * Set method for the customer id
	 */
	public void setId(String id){
		this.id = id;
	}
	
	/**
	 * Set method for the Label {@link CHomeController#welcome}
	 */
	public void setText(){
		welcome.setText("Welcome back "+id+"!");
	}
	
	/**
	 * Takes care of the event of clicking on a particular date from the date picker. It then does the required 
	 * initialisation of the next page CDayPj. It most importantly loads the available projections for the current
	 * date into the TableView {@link CDayPjController#table}.
	 * @param e
	 */
	public void datePick(ActionEvent e){
		
		try{
			
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/CDayPj.fxml"));
			Parent root = loader.load();
			CDayPjController CDPCtll = loader.getController();
		
			CDPCtll.setId(id);
			CDPCtll.setDate(date.getValue().toString());
		
			CDPCtll.setText();
			CDPCtll.loadProjections();
		
			((DatePicker) e.getSource()).getScene().setRoot(root);
		}catch(Exception ex){
			System.out.println("Invalid fxml file...");
		}
	}
	/**
	 * Takes care of the event of clicking on the Update profile button. It redirects the user to the next page 
	 * CUpdateProfile where they could change their details. It also prepares the textfields in that following page
	 * as it looks up the details of the current user in the database.
	 * @param e
	 */
	public void updateProfile(ActionEvent e){
		
		try{
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/CUpdateProfile.fxml"));
			Parent root = loader.load();
			CUpdateProfileController CUPCtll = loader.getController();
		
			CUPCtll.setId(id);
			CUPCtll.setDetails();
		
			((Hyperlink) e.getSource()).getScene().setRoot(root);
			
			}catch(Exception ex){
				System.out.println("Invalid fxml file...");
			}
	}
	
	/**
	 * Takes care of the event of clicking on the My Bookings button. It redirects the user to the next page 
	 * CMyBookings where they could see their history of bookings. It most importantly, looks up all the bookings done
	 * from the current user from the database and populates the TableView {@link CMyBookingsController#table} on the
	 * following page.
	 * @param e
	 */
	public void myBookings(ActionEvent e){
		
		try{
			
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/CMyBookings.fxml"));
			Parent root = loader.load();
			CMyBookingsController cMBCtll = loader.getController();
		
			cMBCtll.setId(id);
			cMBCtll.loadBookings();
		
			((Hyperlink) e.getSource()).getScene().setRoot(root);
			
			}catch(Exception ex){
				System.out.println("Invalid fxml file...");
			}
	}
	
	/**
	 * Takes care of the event of clicking on the Rate Movies button. It redirects the user to the CRateMovies page
	 * where they could rate movies. The only information that it passes is the user's id which is needed to check
	 * whether or not they have already rated some of the movies. 
	 * @param e
	 */
	public void rateMovies(ActionEvent e) {
		
		try{
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/CRateMovies.fxml"));
		Parent root = loader.load();
		CRateMoviesController CRMCtll = loader.getController();
		
		CRMCtll.setId(id);
		
		((Hyperlink) e.getSource()).getScene().setRoot(root);
		}catch(Exception ex){
			System.out.println("Invalid fxml file...");
		}
	}
	/**
	 * Goes to the login page.
	 * @param e
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
