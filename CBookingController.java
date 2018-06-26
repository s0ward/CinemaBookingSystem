package application;

import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * This class is the controller for the CBooking page
 * @author Kristiyan Tomov
 *
 */
public class CBookingController {
	
	/**
	 * A reference to the ImageeView with fx:id "image" in the corresponding fxml file
	 */
	@FXML
	private ImageView image;
	
	/**
	 * A reference to the Label with fx:id "projection" in the corresponding fxml file
	 */
	@FXML
	private Label projection;
	
	/**
	 * A reference to the Label with fx:id "selected" in the corresponding fxml file
	 */
	@FXML
	private Label selected;
	
	/**
	 * A reference to the Label with fx:id "available" in the corresponding fxml file
	 */
	@FXML
	private Label available;
	
	/**
	 * A reference to the Label with fx:id "unavailable" in the corresponding fxml file
	 */
	@FXML
	private Label unavailable;
	
	/**
	 * A reference to the CheckBox with fx:id "A1" in the corresponding fxml file
	 */
	@FXML
	private CheckBox A1;
	
	/**
	 * A reference to the CheckBox with fx:id "A2" in the corresponding fxml file
	 */
	@FXML
	private CheckBox A2;
	
	/**
	 * A reference to the CheckBox with fx:id "A3" in the corresponding fxml file
	 */
	@FXML
	private CheckBox A3;
	
	/**
	 * A reference to the CheckBox with fx:id "A4" in the corresponding fxml file
	 */
	@FXML
	private CheckBox A4;
	
	/**
	 * A reference to the CheckBox with fx:id "B1" in the corresponding fxml file
	 */
	@FXML
	private CheckBox B1;
	
	/**
	 * A reference to the CheckBox with fx:id "B2" in the corresponding fxml file
	 */
	@FXML
	private CheckBox B2;
	
	/**
	 * A reference to the CheckBox with fx:id "B3" in the corresponding fxml file
	 */
	@FXML
	private CheckBox B3;
	
	/**
	 * A reference to the CheckBox with fx:id "B4" in the corresponding fxml file
	 */
	@FXML
	private CheckBox B4;
	
	/**
	 * A reference to the CheckBox with fx:id "C1" in the corresponding fxml file
	 */
	@FXML
	private CheckBox C1;
	
	/**
	 * A reference to the CheckBox with fx:id "C2" in the corresponding fxml file
	 */
	@FXML
	private CheckBox C2;
	
	/**
	 * A reference to the CheckBox with fx:id "C3" in the corresponding fxml file
	 */
	@FXML
	private CheckBox C3;
	
	/**
	 * A reference to the CheckBox with fx:id "C4" in the corresponding fxml file
	 */
	@FXML
	private CheckBox C4;
	
	/**
	 * The id of the user(customer)
	 */
	private String id;
	
	/**
	 * The title of the movie that is being booked
	 */
	private String title;
	
	/**
	 * The date of the projection that is being booked 
	 */
	private String date;
	
	/**
	 * The time of the projection that is being booked 
	 */
	private String time;
	
	/**
	 * The list of the selected seats for the current booking
	 */
	private ArrayList<String> bookedSeats = new ArrayList<String>(12);
	
	
	
	
	/**
	 * Set method for the customer id
	 */
	public void setId(String id){
		this.id = id;
	}
	
	/**
	 * Set method for the time
	 */
	public void setTime(String time){
		this.time = time;
	}
	
	/**
	 * Set method for the title 
	 */
	public void setTitle(String title){
		this.title = title;
	}
	
	/**
	 * Set method for the date
	 */
	public void setDate(String date){
		this.date = date;
	}
	
	/**
	 * Set method for the projection label {@link CBookingController#projection}
	 */
	public void setText(){
		projection.setText(title + " AT " + time);
	}
	
	/**
	 * Takes the information from the database about the projection and represents it visually 
	 * by filling and disabling the checkboxes that correspond to seats, so that they cannot be clicked.
	 */
	public void setSeats(){
		try{
		Database dB = new Database();
		int count = 0;
		
		for (String s: dB.getSeats(title, date, time)){
			count++;
			switch(s){
			case "A1":
				A1.setIndeterminate(true);
				A1.setDisable(true);
				break;
			case "A2":
				A2.setIndeterminate(true);
				A2.setDisable(true);
				break;
			case "A3":
				A3.setIndeterminate(true);
				A3.setDisable(true);
				break;
			case "A4":
				A4.setIndeterminate(true);
				A4.setDisable(true);
				break;
			case "B1":
				B1.setIndeterminate(true);
				B1.setDisable(true);
				break;
			case "B2":
				B2.setIndeterminate(true);
				B2.setDisable(true);
				break;
			case "B3":
				B3.setIndeterminate(true);
				B3.setDisable(true);
				break;
			case "B4":
				B4.setIndeterminate(true);
				B4.setDisable(true);
				break;
			case "C1":
				C1.setIndeterminate(true);
				C1.setDisable(true);
				break;
			case "C2":
				C2.setIndeterminate(true);
				C2.setDisable(true);
				break;
			case "C3":
				C3.setIndeterminate(true);
				C3.setDisable(true);
				break;
			case "C4":
				C4.setIndeterminate(true);
				C4.setDisable(true);
				break;
			default:
				break;
			}
			
		}
		unavailable.setText(new Integer(count).toString());
		available.setText(new Integer(12-count).toString());
		}catch(Exception e){
			System.out.println("Cannot connect to the database...");
		}
	}
	
	/**
	 * Looks up the name of the image corresponding to the movie title in the database and loads the image in the 
	 * current window.The actual image should be in the "src/application/" directory since that is the default
	 * location to which the name, taken from the database, is simply appended.
	 * 
	 */
	public void setMovieImage(){
		
		String path ="/application/";
		
		try{
			
			Database dB = new Database();
			path += dB.getPicture(this.title);

		}catch(Exception e){
			System.out.println("Cannot connect to the database...");
		}
		
		try{
	
			image.setImage(new Image(path));
			image.setPreserveRatio(true);
			
		}catch(Exception e){
			System.out.println("Invalid URL...");
		}
	}
	
	/**
	 * Takes care about the event of clicking on a checkbox. It appends or deletes the checkbox's id from the list of
	 * {@link CBookingController#bookedSeats} depending on the current state of the checkbox. The list is also made
	 * visible to the customer by the update of the label {@link CBookingController#selected}.
	 * @param e
	 */
	public void checked(ActionEvent e){
		String temp = "Selected: ";
		
		if(((CheckBox) e.getSource()).isSelected()){
			bookedSeats.add(((CheckBox) e.getSource()).getId());
		}
		else{
			bookedSeats.remove(((CheckBox) e.getSource()).getId());
		}
		
		for(String s: bookedSeats){
			temp+=s+", ";
		}
		
		selected.setText(temp);
		
	}
	
	/**
	 * Takes care of the event of clicking on the Confirm Booking button. It loads the CBookingConfirmation page
	 * as well as passes the required arguments such as id,time,date,title, etc and finally loads some of the content
	 * of the next page.
	 * @param e
	 */
	public void confirmBooking(ActionEvent e){
		try{
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/CBookingConfirmation.fxml"));
		Parent root = loader.load();
		CBookingConfirmationController CBCCtll = loader.getController();
		Database dB = new Database();
		
		for (String s: bookedSeats){
			dB.addBooking(id,time,date,title,s);
			CBCCtll.addBooking(new Booking(title,date,time,s));
		}
		
		CBCCtll.setId(id);
		CBCCtll.setTitle(title);
		CBCCtll.setDate(date);
		CBCCtll.setTime(time);
		CBCCtll.loadBookings();
		
		((Button) e.getSource()).getScene().setRoot(root);
		}catch(Exception ex){
			System.out.println("Invalid fxml file...");
		}
	}
	
	/**
	 * Goes back to the previous page. In order to do so it passes the required arguments and initialises it.
	 */
	public void back(ActionEvent e){
		try{
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/CDayPj.fxml"));
		Parent root = loader.load();
		
		CDayPjController CDPCtll = loader.getController();
		
		CDPCtll.setId(id);
		CDPCtll.setDate(date);

		CDPCtll.setText();
		CDPCtll.loadProjections();
		
		((Button) e.getSource()).getScene().setRoot(root);
		}catch(Exception ex){
			System.out.println("Invalid fxml file...");
		}
	}
	
	/**
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
	
	
		
}
