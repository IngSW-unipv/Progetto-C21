package risk.controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import risk.model.Card;

public class CardSceneController implements Initializable{
	
	@FXML
	private ImageView cardImg1, cardImg2, cardImg3;
	
	@FXML
	private Button remove1, remove2, remove3;
	
	@FXML
	private Label artilleryLeftLabel, infantryLeftLabel, cavalryLeftLabel, jollyLeftLabel;
	
	@FXML
	private Button addArtilleryButton, addInfantryButton, addCavalryButton, addJollyButton;
	
	@FXML
	private Button tradeButton, cancelButton;
	

	private Card card1, card2, card3;
	private boolean[] cardSet = new boolean[3];		// true se c'è una carta, false altrimenti
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		cardSet[0] = false; cardSet[1] = false; cardSet[2] = false;
		tradeButton.setDisable(true);
		
	}
	
	@FXML
	public void remove1ButtonPressed(MouseEvent event){
		// aggiorno la prima posizione (vuota)
		cardSet[0] = false;
		try {
			InputStream stream = new FileInputStream("src/risk/view/images/cards/empty.png");
			Image img = new Image(stream);
			cardImg1.setImage(img);	
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	@FXML
	public void remove2ButtonPressed(MouseEvent event){
		// aggiorno la seconda posizione (vuota)
		cardSet[1] = false;
		try {
			InputStream stream = new FileInputStream("src/risk/view/images/cards/empty.png");
			Image img = new Image(stream);
			cardImg2.setImage(img);	
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	@FXML
	public void remove3ButtonPressed(MouseEvent event){
		// aggiorno la terza posizione (vuota)
		cardSet[2] = false;
		try {
			InputStream stream = new FileInputStream("src/risk/view/images/cards/empty.png");
			Image img = new Image(stream);
			cardImg3.setImage(img);	
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	
	@FXML
	public void tradeButtonPressed(MouseEvent event){
		
	}
	
	@FXML
	public void cancelButtonPressed(MouseEvent event){
    	Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
		window.close();
	}
	
	/* Ogni volta che viene premuto un tasto add:
	 * 		1) controllo che ci sia spazio per inserire la carta
	 * 		2) se c'e' spazio la inserisco, altrimenti non faccio niente
	 * 		3) dopo averla inserita (o dopo il controllo) controllo nuovamente se ci sono 3 carte
	 * 		4) se ci sono 3 carte attivo il button Trade altrimenti lo lascio disabilitato */
	@FXML
	public void addArtilleryButtonPressed(MouseEvent event){
		ImageView[] cardImgArr = {cardImg1, cardImg2, cardImg3};
		
		// eseguo un ciclo sui tre slot disponibili, ma appena ne trovo uno libero
		// eseguo il codice e dopo esco dal ciclo
		for(int i = 0; i < 3; i++) {
			if(!cardSet[i]) {
				cardSet[i] = true;
				try {
					InputStream stream = new FileInputStream("src/risk/view/images/cards/Afghanistan.png");
					Image img = new Image(stream);
					cardImgArr[i].setImage(img);	
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
				break;
			}
		}
	}
	
	


}
