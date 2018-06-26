package application;

import java.util.ArrayList;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;

public class ECinemaRoomController {

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
	@FXML
	private Label lblHeaderECR;
	@FXML
	private Label lblBookedSeatsECR;
	@FXML
	private Label lblAvailableSeatsECR;
	
	
	private int bookedSeats=0;
	
	private ArrayList<String> seatsBooked;
			
	public void checkEvent(ActionEvent e) {
		seatsBooked.add(((CheckBox) e.getSource()).getText());
		bookedSeats=seatsBooked.size();
		lblBookedSeatsECR.setText(new Integer(bookedSeats).toString());
		lblAvailableSeatsECR.setText(new Integer(bookedSeats-12).toString());
	}
	
}
