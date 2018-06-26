package application;


import java.util.Map;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * This class is the controller for the CUpdateProfile page
 * @author Kristiyan Tomov
 *
 */
public class CUpdateProfileController {
	
	/**
	 * A reference to the Label with fx:id "message" in the corresponding fxml file
	 */
	@FXML
	private Label message;
	
	/**
	 * A reference to the TextField with fx:id "forename" in the corresponding fxml file
	 */
	@FXML
	private TextField forename;
	
	/**
	 * A reference to the TextField with fx:id "surname" in the corresponding fxml file
	 */
	@FXML
	private TextField surname;
	
	/**
	 * A reference to the TextField with fx:id "email" in the corresponding fxml file
	 */
	@FXML
	private TextField email;
	
	/**
	 * A reference to the TextField with fx:id "password" in the corresponding fxml file
	 */
	@FXML
	private TextField password;
	
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
	 * Looks up the details for the given user(customer) by their id in the database. Then it fills the textfields
	 * with the recieved information, so that the user sees what their current details are.
	 */
	public void setDetails(){
		try{
		Database dB = new Database();
		Map<String, String> details = dB.getUserDetails(id);
		
		password.setText(details.get("pass"));
		surname.setText(details.get("surname"));
		forename.setText(details.get("firstname"));
		email.setText(details.get("email"));
		}catch(Exception e){
			System.out.println("Cannot connect to the database...");
		}
	}
	
	/**
	 * Takes care of the event of clicking the Update button. It makes sure to update the current details on the
	 * database with the new ones.  
	 * @param e
	 */
	public void update(ActionEvent e){
		
		try{
		Database dB = new Database();
		
		dB.updateUser(id, password.getText(), surname.getText(), forename.getText(), email.getText());
		
		message.setText("Profile successfully updated !");
		
		}catch(Exception ex){
			message.setText("Cannot connect to the database...");
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
	
	
}
