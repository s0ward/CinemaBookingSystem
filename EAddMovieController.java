package application;

import java.io.File;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

public class EAddMovieController {
	
	@FXML
	private TextField title;
	
	@FXML
	private TextArea description;
	
	@FXML
	private ImageView image;
	
	@FXML
	private Button browseButton;
	
	@FXML
	private Label lblMessage;
		
	private String imgName="";
	
	public void browsePicture(ActionEvent e){
		
		String URL = "";
		FileChooser fc = new FileChooser();
		File f = fc.showOpenDialog(null);
		imgName = f.getName();
		
		if (f != null){
			
			URL = "file:///" + f.getAbsolutePath().replace("\\","/");
			
			try{
				
				image.setImage(new Image(URL));
				image.setPreserveRatio(true);
	
			}catch(Exception ex){
				System.out.println("Invalid URL. Try again!");
			}
			
		}
	
	}
	
	//save the new movie	
	public void saveMovie(ActionEvent e) {
		
		try{
			
			Database dB = new Database();
			dB.addMovie(title.getText(), description.getText(), imgName);
			lblMessage.setText("New Movie successfully added. Access all movies from Home button");
		
		}catch(Exception ex){
			System.out.println("Cannot connect to database...");
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
