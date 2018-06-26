package application;

import java.util.Date;

import java.util.HashMap;
import java.util.Map;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
/**
 * This class is the controller for the CMyBookings page
 * @author Kristiyan Tomov
 *
 */
public class CMyBookingsController {
	
	
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
	 * A reference to the TableColumn with fx:id "posterColumn" in the corresponding fxml file
	 */
	@FXML
	private TableColumn posterColumn;
	
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
	 * A reference to the TextField with fx:id "cancelDate" in the corresponding fxml file
	 */
	@FXML
	private TextField cancelDate;
	
	/**
	 * A reference to the TextField with fx:id "cancelTime" in the corresponding fxml file
	 */
	@FXML
	private TextField cancelTime;
	
	@FX
	/**
	 * A reference to the TextField with fx:id "cancelSeat" in the corresponding fxml file
	 */ML
	private TextField cancelSeat;
	
	/**
	 * A reference to the Label with fx:id "message" in the corresponding fxml file
	 */
	@FXML 
	private Label message;

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
	 * Loads into the the TableView {@link CMyBookingsController#table} the bookings that are retrieved from the database
	 * for the current user by their id.
	 */
	public void loadBookings(){
		
		try{
			Database dB = new Database();
		
			titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
			dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
			timeColumn.setCellValueFactory(new PropertyValueFactory<>("time"));
			seatColumn.setCellValueFactory(new PropertyValueFactory<>("seat"));
		
			for(Booking b: dB.getBookings(id)) table.getItems().add(b);
		
			}catch(Exception e){
				System.out.println("Cannot connect to the database...");
			}
	}
	
	
	/**
	 * Goes back to the previous page. In order to do so it passes the required arguments and initialises it.
	 */
	public void back(ActionEvent e){
		try{
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/CHome.fxml"));
		Parent root = loader.load();
		CHomeController homeCustCtll = loader.getController();
		homeCustCtll.setId(id);
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
		CHomeController homeCustCtll = loader.getController();
		homeCustCtll.setId(id);
		((Button) e.getSource()).getScene().setRoot(root);
		}catch(Exception ex){
			System.out.println("Invalid fxml file...");
		}
	}
	/**
	 * Takes care of the event of clicking on the Cancel Booking button. It verifies that the booking that the user
	 * wishes to delete is in the future. In other words, it gives an error message if the user wants to delete a 
	 * past booking. Otherwise, it deletes it from the database and reloads the TableView {@link CMyBookingsController#table}
	 * with the deleted one missing.
	 * @param e
	 */
	public void cancelBooking(ActionEvent e){
		
		String date = cancelDate.getText();
		String time = cancelTime.getText();
		String seatnumber = cancelSeat.getText();
		
		if(isValidDate(date)){
			
			try{
		
				Database dB = new Database();
		
				dB.deleteBooking(id, time, date, seatnumber);
		
				table.getItems().clear();
				this.loadBookings();
		
				message.setText("Booking succesfully deleted!");
		
			}catch(Exception ex){
			message.setText("Cannot connect to the database...");
			}
		}
		
		else message.setText("You can only cancel future bookings!");
		
	}
	
	/**
	 * Compares a date (s) to the current date and returns true if the date(s) is in the future and false otherwise.
	 * Very important is that the date is in the format "yyyy-mm-dd". It is used in {@link CMyBookingsController#cancelBooking(ActionEvent)}
	 * 
	 * @param s
	 * @return boolean
	 */
	public boolean isValidDate(String s){
		
		int day = Integer.parseInt(s.split("-")[2]);
		int month = Integer.parseInt(s.split("-")[1]);
		int year = Integer.parseInt(s.split("-")[0]);
		
		
		Date date = new Date();
		int cDay = -1;
		int cMonth = -1;
		int cYear = -1;
		
		Map<String, Integer> months = new HashMap<String, Integer>(12);
		
		
		months.put("Jan", 1);
		months.put("Feb", 2);
		months.put("Mar", 3);
		months.put("Apr", 4);
		months.put("May", 5);
		months.put("Jun", 6);
		months.put("Jul", 7);
		months.put("Aug", 8);
		months.put("Sep", 9);
		months.put("Oct", 10);
		months.put("Nov", 11);
		months.put("Dec", 12);
		

		
		cMonth = months.get(date.toString().split(" ")[1]);
		cDay = Integer.parseInt(date.toString().split(" ")[2]);
		cYear = Integer.parseInt(date.toString().split(" ")[5]);
		
		
		if(cYear>year) return false;
		
		else{
			if(cYear < year) return true;
			if(cYear == year) {
				if(cMonth > month) return false;
				else{
					if(cMonth < month) return true;
					if(cMonth == month) {
						if(cDay > day) return false;
						else{
							if(cDay < day) return true;
							if(cDay == day) return false;
						}
					}
				}
			}
			
		}
		return false;
	}
}
