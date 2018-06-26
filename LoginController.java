package application;

import java.io.IOException;
import java.sql.SQLException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 * This class is the controller for the Login page
 * @author Kristiyan Tomov
 *
 */

public class LoginController {
	
	/**
	 * A reference to the TextField with fx:id "username" in the corresponding fxml file
	 */
	@FXML
	private TextField username;
	
	/**
	 * A reference to the PasswordField with fx:id "password" in the corresponding fxml file
	 */
	@FXML
	private PasswordField password;
	
	/**
	 * A reference to the Label with fx:id "message" in the corresponding fxml file
	 */
	@FXML
	private Label message;

	/**
	 * A reference to the HyperLink with fx:id "signUp" in the corresponding fxml file
	 */
	@FXML
	private Hyperlink signUp;
	
	
	public void signUp(ActionEvent e){
		//message.setText("Sign up");
	}
	
	/**
	 * Checks whether the give username/pass pair exist in the database. Three options are possible. The user gets
	 * Incorrect credentials error message, they are let in as customer, or they are let in as employee.
	 * @param e
	 * @throws IOException
	 * @throws SQLException
	 */
	public void signIn(ActionEvent e) throws IOException, SQLException{
		String id = username.getText();
		String pass = password.getText();
		Database data = new Database();
		FXMLLoader loader = null;
		Parent root = null;
		
		switch(data.isValid(id, pass)){
		case 0:
			message.setText("Incorrect credentials");
			break;
		case 1:
			loader = new FXMLLoader(getClass().getResource("/application/CHome.fxml"));
			root = loader.load();
			CHomeController CHCtll = loader.getController();
			
			CHCtll.setId(id);
			CHCtll.setText();
		
			
			((Button) e.getSource()).getScene().setRoot(root);
			break;
		case 2:
			loader = new FXMLLoader(getClass().getResource("/application/EHome.fxml"));
			root = loader.load();
			EHomeController eHomeCtll = loader.getController();
			eHomeCtll.fillCombo();
			((Button) e.getSource()).getScene().setRoot(root);
			break;
		}

	}
}
