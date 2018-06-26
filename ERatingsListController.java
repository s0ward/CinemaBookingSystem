package application;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class ERatingsListController {
	
	@FXML
	private TableView tableRatings;
	@FXML
	private TableColumn columnTitle;
	@FXML
	private TableColumn columnRating;
	
	public void loadRatings(){
		
		columnTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
		columnRating.setCellValueFactory(new PropertyValueFactory<>("rating"));
		
		try{
			
			Database dB = new Database();
		
			for(String title: dB.getAllRatedMovies()){
			
				tableRatings.getItems().add(new Rating(title,dB.getMovieRating(title)));
				
			}
		
		}catch(Exception e){
			System.out.println("Cannot connect to the database...");
		}
	}

	

	public void goHome(ActionEvent e) {
		
		try{
			
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/EHome.fxml"));
			Parent root = loader.load();
			EHomeController eHomeCtll = loader.getController();
			eHomeCtll.fillCombo();
			((Button) e.getSource()).getScene().setRoot(root);
			
		}catch(Exception ex){
			System.out.println("Invalid fxml file...");
		}
	}
	}