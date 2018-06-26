package application;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

public class EHomeController {


	@FXML
	private ComboBox movies;
	
	@FXML
	private Label export;
	
	private String title;
	
	
	public void setTitle(String title) {
		this.title=title;
	}

	public void fillCombo(){
		
		try{
		Database d = new Database();
		
		for(String s: d.getMovies()) movies.getItems().add(s);
		}catch(Exception e){
			System.out.println("Cannot connect to the database...");
		}
		
	}
	
	public void comboChange(ActionEvent e){
		
		try{
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/EMovie.fxml"));
		Parent root = loader.load();
		EMovieController EMCtll = loader.getController();
		
		EMCtll.setTitle(((ComboBox) e.getSource()).getValue().toString());
		EMCtll.setLabel();
		EMCtll.setTextArea();
		EMCtll.setMovieImage();
		EMCtll.fillCombo();
		
		((ComboBox) e.getSource()).getScene().setRoot(root);
		}catch(Exception ex){
			System.out.println("Invalid fxml file...");
		}
	}
	
	
	public void addMovie(ActionEvent e){
		
		try{
		Parent root = FXMLLoader.load(getClass().getResource("/application/EAddMovie.fxml"));
		((Button) e.getSource()).getScene().setRoot(root);
		}catch(Exception ex){
			System.out.println("Invalid fxml file...");
		}
		
	}
	
	//export projections
	public void exportProjections(ActionEvent e){
		
		ArrayList<String> lines = new ArrayList<String>(100);
		
		try{
			
			Database dB = new Database();
			lines = dB.export();
		
		}catch (Exception ex){
			System.out.println("Cannot connect to the database...");
		}
			
		BufferedWriter bw = null;
		FileWriter fw = null;

		try {
			
			fw = new FileWriter("src\\application\\export.txt");
			bw = new BufferedWriter(fw);
			
			for(String line: lines){
				bw.write(line+",");
				bw.newLine();
			}
			
			export.setText("Projections successfully exported!");

		} catch (IOException ex) {

			System.out.println("Invalid file");

		} finally {

			try {

				if (bw != null)
					bw.close();

				if (fw != null)
					fw.close();

			} catch (IOException ex) {

				ex.printStackTrace();

			}

		}
		
	}
	
	//add projection button 
	public void addProjection(ActionEvent e){
		
		try{
			
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/EAddProjection.fxml"));
		Parent root = loader.load();
		EAddProjectionController EAPCtll = loader.getController();
		EAPCtll.fillCombo();
		EAPCtll.setTitle(title);
		((Button) e.getSource()).getScene().setRoot(root);
		
		}catch(Exception ex){
			System.out.println("Invalid fxml file...");
		}
		
	}
	
	public void seeRatings(ActionEvent e) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/ERatingsList.fxml"));
		Parent root = loader.load();
		ERatingsListController ERLCtll = loader.getController();
		
		ERLCtll.loadRatings();
		
		((Button) e.getSource()).getScene().setRoot(root);
	}
	
	public void logOut(ActionEvent e){
		
		try{
			
		Parent root = FXMLLoader.load(getClass().getResource("/application/Login.fxml"));
		((Button) e.getSource()).getScene().setRoot(root);
		
		}catch(Exception ex){
			System.out.println("Invalid fxml file...");
		}
		
	}
}
