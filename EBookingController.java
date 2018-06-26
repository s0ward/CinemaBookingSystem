package application;


import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;

public class EBookingController {



	
	@FXML
	private Label projection;
	
	@FXML
	private Label available;
	
	@FXML
	private Label unavailable;
	
	@FXML
	private CheckBox A1;
	@FXML
	private CheckBox A2;
	@FXML
	private CheckBox A3;
	@FXML
	private CheckBox A4;
	@FXML
	private CheckBox B1;
	@FXML
	private CheckBox B2;
	@FXML
	private CheckBox B3;
	@FXML
	private CheckBox B4;
	@FXML
	private CheckBox C1;
	@FXML
	private CheckBox C2;
	@FXML
	private CheckBox C3;
	@FXML
	private CheckBox C4;
	
	
	private String title;
	private String date;
	private String time;
	private ArrayList<String> bookedSeats = new ArrayList<String>(12);
	
	
	
	
	


	public void setTime(String time){
		this.time = time;
	}
	
	public void setTitle(String title){
		this.title = title;
	}
	
	public void setDate(String date){
		this.date = date;
	}
	
	public void setText(){
		projection.setText(title + " AT " + time);
	}
	
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
	

	public void back(ActionEvent e){
		try{
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/EMovie.fxml"));
		Parent root = loader.load();
		EMovieController EMCtll = loader.getController();
		
		EMCtll.setTitle(title);
		EMCtll.setLabel();
		EMCtll.setTextArea();
		EMCtll.setMovieImage();
		
		((Button) e.getSource()).getScene().setRoot(root);
		}catch(Exception ex){
			System.out.println("Invalid fxml file...");
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
    }
