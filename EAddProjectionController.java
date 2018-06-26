package application;

import java.util.ArrayList;
import java.util.Arrays;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;

public class EAddProjectionController {

	@FXML
	private ComboBox titlePj;
	
	@FXML
	private Label lblMovieTitle;
	
	@FXML
	private Label confirmationAddPj;
	
	@FXML
	private ComboBox timePicker;
	
	@FXML
	private DatePicker datePicker;
	
	private String title;
	
	public void setTitle(String title) {
		this.title=title;
	}
	
	public void setLabel(){
		this.lblMovieTitle.setText(title);
	}
	
	public void fillCombo(){
		
		try{
		Database d = new Database();
		
		for(String s: d.getMovies()) titlePj.getItems().add(s);
		}catch(Exception e){
			System.out.println("Cannot connect to the database...");
		}
	}
	


	public void fillTimes(ActionEvent e){
		
		try{
        ArrayList<String> times = new ArrayList<String>(Arrays.asList("10am", "12pm", "2pm", "4pm", "6pm", "8pm", "10pm"));    
        Database d = new Database();
		
        for(String s: d.getPjTime(datePicker.getValue().toString(), titlePj.getValue().toString())) times.remove(s);
        
        timePicker.getItems().clear();
        for (String s : times) timePicker.getItems().add(s);
		}catch(Exception ex){
			System.out.println("Cannot connect to the database...");
		}
        
        
	}
	
	public void savePj(ActionEvent e) {
			
			try{
			Database dB = new Database();
			dB.addPj(titlePj.getValue().toString(), datePicker.getValue().toString(), timePicker.getValue().toString());
		    confirmationAddPj.setText("Projection successfully added");
			}catch(Exception ex){
				System.out.println("Cannot connect to the database...");
			}
	}
	
	
	
	public void home(ActionEvent e){
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
	public void back(ActionEvent e){
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
