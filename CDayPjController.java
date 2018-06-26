package application;


import java.util.ArrayList;

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
 * This class is the controller for the CDayPj page
 * @author Kristiyan Tomov
 *
 */
public class CDayPjController {
	
	/**
	 * A reference to the Label with fx:id "message" in the corresponding fxml file
	 */
	@FXML
	private Label message;
	
	/**
	 * A reference to the Label with fx:id "dateLabel" in the corresponding fxml file
	 */
	@FXML
	private Label dateLabel;
	
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
	 * A reference to the TableColumn with fx:id "timeColumn" in the corresponding fxml file
	 */
	@FXML
	private TableColumn timeColumn;
	
	/**
	 * A reference to the TableColumn with fx:id "descriptionColumn" in the corresponding fxml file
	 */
	@FXML
	private TableColumn descriptionColumn;
	
	/**
	 * A reference to the TextField with fx:id "title" in the corresponding fxml file
	 */
	@FXML
	private TextField title;
	
	/**
	 * A reference to the TextField with fx:id "time" in the corresponding fxml file
	 */
	@FXML
	private TextField time;
	
	/**
	 * The id of the user(customer)
	 */
	private String id;
	
	/**
	 * The date of the projection that is being booked 
	 */
	private String date;
	
	/**
	 * The list of projections available on this date. It is going to be useful to check whether the input from the customer
	 * corresponds to a projection (for validation of the input).
	 */
	private ArrayList<Projection> projections = new ArrayList<Projection>(10);
	
	
	
	/**
	 * Set method for the customer id
	 */
	public void setId(String id){
		this.id=id;
	}
	
	/**
	 * Set method for the date
	 */
	public void setDate(String date){
		this.date = date;
	}
	
	/**
	 * Set method for the text
	 */
	public void setText(){
		dateLabel.setText("WHAT'S ON "+date);
	}
	
	/**
	 * Loads the projections from the database into the TableView {@link CDayPjController#table} and also populates
	 * the list of projections {@link CDayPjController#projections}
	 * 
	 */
	public void loadProjections(){
		
		try{
		Database dB = new Database();
		
		titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
		timeColumn.setCellValueFactory(new PropertyValueFactory<>("time"));
		descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
		
		for(Projection p: dB.getProjections(date)) {
			table.getItems().add(p);
			projections.add(p);
		}
		}catch(Exception e){
			System.out.println("Cannot connect to database...");
		}
		
	}
	
	/**
	 * Takes care of the action of clicking on the button Book seats. It performs validation of the input: first it checks
	 * whether there are any projections at all on the current day and secondly it verifies whether the input 
	 * corresponds to an existing projection for the current day. If all is well, it loads and sends the user to the
	 * next page which is CBooking.
	 * 
	 * @param e
	 */
	public void bookSeats(ActionEvent e){
		
		if(!table.getItems().isEmpty()){
		
			if(isInPjs(title.getText(),time.getText())){
				try{
		
					FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/CBooking.fxml"));
					Parent root = loader.load();
					CBookingController CBCtll = loader.getController();
		
					CBCtll.setId(id);
					CBCtll.setTitle(title.getText());
					CBCtll.setTime(time.getText());
					CBCtll.setDate(date);
					
					CBCtll.setText();
					CBCtll.setSeats();
					CBCtll.setMovieImage();
		
					((Button) e.getSource()).getScene().setRoot(root);
				}catch(Exception ex){
					System.out.println("Invalid fxml file...");
				}
			}
			
			else message.setText("Incorrect details");
		}
		
		else message.setText("There are no projections for this day.");
	}
	/**
	 * Checks whether the input corresponds to a real projection.
	 * @param title
	 * @param time
	 * @return
	 */
	public boolean isInPjs(String title, String time){
		for(Projection p: projections){
			if(p.getTitle().equals(title) && p.getTime().equals(time)) return true;
		}
		
		return false;
		
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
