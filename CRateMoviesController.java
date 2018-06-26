package application;

import java.io.IOException;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * This class is the controller for the CRateMovies page
 * @author Kristiyan Tomov
 *
 */
public class CRateMoviesController {
	
	/**
	 * A reference to the TextField with fx:id "movieTitle" in the corresponding fxml file
	 */
	@FXML 
	private TextField movieTitle;
	
	/**
	 * A reference to the TextField with fx:id "leaveRating" in the corresponding fxml file
	 */
	@FXML
	private TextField leaveRating;
	
	/**
	 * A reference to the Label with fx:id "messageRating" in the corresponding fxml file
	 */
	@FXML
	private Label messageRating;
	
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
	 * Takes care of the event of clicking on the Submit Rating button. It basically verifies whether the input
	 * movie is in the database and whether the user(customer) hasn't already rated it. If not, it is uploaded to the
	 * database. Also the program makes sure that the input for the rating itself is one of the 6 options:
	 * 0,1,2,3,4,5. Otherwise, it throws and error message.
	 * @param e
	 */
	public void submitRating(ActionEvent e){
		
		Database dB = null;
		ArrayList<String> movies = null;
		
		try{
			
			dB = new Database();
			movies = dB.getMovies();
			
		}catch(Exception ex){
			System.out.println("Cannot connect to database...");
		}
		
		String title = movieTitle.getText();
		Double rating = 0.0;
		
		switch(leaveRating.getText()){
		
			case "0":
				rating = 0.0;
				break;
			case "1":
				rating = 1.0;
				break;
			case "2":
				rating = 2.0;
				break;
			case "3":
				rating = 3.0;
				break;
			case "4":
				rating = 4.0;
				break;
			case "5":
				rating = 5.0;
				break;
			default:
				messageRating.setText("Invalid input");
				return;
		}
		
		if (!movies.contains(title)){
			messageRating.setText("Movie not in the database.");
			return;
		}
		
		try{
			
			if(dB.getRatedMovies(id).contains(title)){
				
				messageRating.setText("Movie already rated.");
				
			}
			
			else{
				
				dB.addRating(id, title, rating);
				
				messageRating.setText("Rating successfully saved.");
				
			}
			
		}catch(Exception ex){
			messageRating.setText("Cannot connect to the database...");
		}
	}
	
	/**
	 * Goes back to the previous page. In order to do so it passes the required arguments and initialises it.
	 */
	public void back(ActionEvent e) throws IOException{
		
		try{
			
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/CHome.fxml"));
			Parent root = loader.load();
			CHomeController homeCustCtll = loader.getController();
			homeCustCtll.setId(id);
			((Button) e.getSource()).getScene().setRoot(root);
		
		}catch(Exception ex){
			System.out.println("Invalid fxml file ...");
		}
	}
	
	/**
	 * Goes back to the home page. In order to do so it passes the required arguments and initialises it.
	 */
	public void home(ActionEvent e) throws IOException{
		
		try{
			
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/CHome.fxml"));
			Parent root = loader.load();
			CHomeController homeCustCtll = loader.getController();
			homeCustCtll.setId(id);
			((Button) e.getSource()).getScene().setRoot(root);
		
		}catch(Exception ex){
			System.out.println("Invalid fxml file ...");
		}
		
		
	}
	
	

}