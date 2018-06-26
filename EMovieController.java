package application;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class EMovieController {
	


	@FXML
	private Label lblEMT;
	
	@FXML
	private Label lblChangeDesc;
	
	@FXML
	private TextArea description;
	
	@FXML
	private ImageView image2;
	
	@FXML
	private ComboBox dateCombo;
	
	@FXML
	private ComboBox timeCombo;

	
	private String date;
	private String title;
	
	public void setDate(String date) {
		this.date=date;
	}
	
	public void setTitle(String title) {
		this.title=title;
	}
	
	//set text of label to title
	public void setLabel() {
		lblEMT.setText(title);
	}
		
	//populate textArea from description in Database
	public void setTextArea(){
		
		try{
			
		Database dB = new Database();
		description.setText(dB.getDescription(this.title));
		
		}catch(Exception e){
			System.out.println("Cannot connect to the database...");
		}
	}
	
	//Set the image from DB path
	public void setMovieImage(){
		
		String path ="/application/";
		
		try{
			
			Database dB = new Database();
			path += dB.getPicture(this.title);

			
		}catch(Exception e){
			System.out.println("Cannot connect to the database...");
		}
		
		try{
			
			image2.setImage(new Image(path));
			image2.setPreserveRatio(true);
			
		}catch(Exception e){
			System.out.println("Invalid URL...");
		}
	}
	
	//fill date combo box
	public void fillCombo(){
		
		try{
			Database d = new Database();
		
			dateCombo.getItems().clear();
			for(String s: d.getPjDate(title)) dateCombo.getItems().add(s);
		}
		catch(Exception e){
			System.out.println("Cannot connect to the database...");
		}
    }
	
	//set showTimes on the selected date
	public void setTimes(ActionEvent e){
		
		try{
		String date = dateCombo.getValue().toString();
        this.setDate(date);
        
        Database d = new Database();
		
        for(String t: d.getPjTime(date, title)) timeCombo.getItems().add(t); 
		}catch(Exception ex){
			System.out.println("Cannot connect to the database...");
		}
	}
	
	//save changes to description
     public void updateDesc(ActionEvent e){
		
		Database dB = new Database();
		
		dB.updateDescription(title, description.getText());
		lblChangeDesc.setText("description successfully updated");
		
	}
	
	//go to the cinema room
	public void getRoom(ActionEvent e){
		
		try{
			
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/EBooking.fxml"));
		Parent root = loader.load();
		EBookingController EBCtll = loader.getController();
		
		
		
		EBCtll.setDate(date);
		EBCtll.setTitle(title);
		EBCtll.setTime(timeCombo.getValue().toString());
		EBCtll.setText();
		EBCtll.setSeats();
		
		((ComboBox) e.getSource()).getScene().setRoot(root);
		
		}catch(Exception ex){
			System.out.println("Invalid fxml file...");
		}
	}
	
		
	//add a new projection 
	public void addProjection(ActionEvent e){
		
		try{
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/EMovieAddProjection.fxml"));
		Parent root = loader.load();
		EMovieAddProjectionController EMAPCtll = loader.getController();
		
		EMAPCtll.setTitle(title);
		EMAPCtll.setLabel();
		
		((Button) e.getSource()).getScene().setRoot(root);
		}catch(Exception ex){
			System.out.println("Invalid fxml file...");
		}
		
	}
	
	//back button	
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
	
	//home button
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
