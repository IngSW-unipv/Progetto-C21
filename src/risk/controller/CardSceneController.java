package risk.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

public class CardSceneController implements Initializable{
	
	@FXML
	private ImageView cardImg1, cardImg2, cardImg3;
	
	@FXML
	private Button remove1, remove2, remove3;
	
	@FXML
	private Label artilleryLeftLabel, infantryLeftLabel, cavalryLeftLabel, jollyLeftLabel;
	
	@FXML
	private Button addArtilleryButton, addInfantryButton, addCavalryButton, addJollyButton;
	

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		
	}

}
