package risk.controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import risk.model.Card;
import risk.model.util.FIGURE;
import risk.model.util.GAME_PHASE;

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
	

	private Card[] cardSet;	
	private int artilleryNum, infantryNum, cavalryNum, jollyNum;
	private SoundController soundController = new SoundController();
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		/* Inizializzazione variabili */
		cardSet = new Card[3];
		cardSet[0] = null;
		cardSet[1] = null;
		cardSet[2] = null;
		

		/* Inizializzazione grafica */
		tradeButton.setDisable(true);
		handlePlayerFigureNumber();
		

	}
	
	private void handlePlayerFigureNumber() {
		/* Azzero i contatori */
		artilleryNum = 0; 
		infantryNum = 0; 
		cavalryNum = 0; 
		jollyNum = 0;
		
		/* Conto il numero di figure che il giocatore possiede */
		for(Card c : GameController.game.getCurrentTurn().getCards()) {
			switch (c.getFigure()) {
			case ARTILLERY:
				artilleryNum++;
				break;
			case INFANTRY:
				infantryNum++;
				break;
			case CAVALRY:
				cavalryNum++;
				break;
			case JOLLY:
				jollyNum++;
				break;
			}
		}
		
		/* Setto la grafica */
		artilleryLeftLabel.setText(String.valueOf(artilleryNum) + " left");
		infantryLeftLabel.setText(String.valueOf(infantryNum) + " left");
		cavalryLeftLabel.setText(String.valueOf(cavalryNum) + " left");
		jollyLeftLabel.setText(String.valueOf(jollyNum) + " left");
		
		/* Abilito/Disabilito i pulsanti */
		if(artilleryNum <= 0)
			addArtilleryButton.setDisable(true);
		else
			addArtilleryButton.setDisable(false);
		if(infantryNum <= 0)
			addInfantryButton.setDisable(true);
		else
			addInfantryButton.setDisable(false);
		if(cavalryNum <= 0)
			addCavalryButton.setDisable(true);
		else
			addCavalryButton.setDisable(false);
		if(jollyNum <= 0)
			addJollyButton.setDisable(true);
		else
			addJollyButton.setDisable(false);
	}
	
	private void handleTradeButton() {
		/* Controllo se sono state inserite almeno tre carte, in caso affermativo
		allora controllo se il tris è valido e attivo il pulsante trade */
		if(cardSet[0] != null && cardSet[1] != null && cardSet[2] != null) {
			if(GameController.game.checkTris(cardSet[0], cardSet[1], cardSet[2]) != 0) {
				tradeButton.setDisable(false);
			}
		} else
			tradeButton.setDisable(true);
	}
	
	@FXML
	public void remove1ButtonPressed(ActionEvent event){
		removeCardToSet(0, cardImg1);
	}
	@FXML
	public void remove2ButtonPressed(ActionEvent event){
		removeCardToSet(1, cardImg2);
	}
	@FXML
	public void remove3ButtonPressed(ActionEvent event){
		removeCardToSet(2, cardImg3);
	}
	
	private void removeCardToSet(int index, ImageView cardImg) {
		// rido' la carta al player se non e' null
		if(cardSet[index] != null)
			GameController.game.getCurrentTurn().giveCard(cardSet[index]);
		// aggiorno la posizione come vuota
		cardSet[index] = null;
		try {
			InputStream stream = new FileInputStream("src/risk/view/images/cards/empty.png");
			Image img = new Image(stream);
			cardImg.setImage(img);	
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		handlePlayerFigureNumber();
		handleTradeButton();
	}
	
	
	@FXML
	public void tradeButtonPressed(ActionEvent event){
		if(GameController.getInstance().game.getGamePhase().equals(GAME_PHASE.DRAFT)) {
			int bonus = 0;
			if(cardSet[0] != null && cardSet[1] != null && cardSet[2] != null) {
				bonus = GameController.game.checkTris(cardSet[0], cardSet[1], cardSet[2]);
			}
			soundController.tradeSound();
			GameController.game.getCurrentTurn().giveBonusTanks(bonus);
			GameController.getInstance().setPhaseSwitchText(String.valueOf(GameController.getInstance().game.getCurrentTurn().getBonusTanks()));
			GameController.getInstance().setPhaseTextArea(GameController.game.getCurrentTurn().getName() + " received " + bonus + " bonus armies");
			GameController.getInstance().updateCardsNumber();
			/*
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Bonus");
			alert.setHeaderText(null);
			alert.setContentText("You received " + bonus + " bonus armies!");
			alert.showAndWait();
			*/
			try {
				GameController.getInstance().windowLoader("/risk/view/fxml/InfosWindow.fxml", "Territory conquered", true, true);
				
			} catch (IOException ex) {
				ex.printStackTrace();
			}

	    	Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
			window.close();
		} else {
			/*
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Warning");
			alert.setHeaderText("You can trade in cards only during DRAFT phase");
			alert.setContentText(null);
			alert.showAndWait();
			*/
			try {
				GameController.getInstance().windowLoader("/risk/view/fxml/InfosWindow.fxml", "Territory conquered", true, true);
				
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}

	}
	
	@FXML
	public void cancelButtonPressed(ActionEvent event){
		// rido' le carte al giocatore e poi chiudo la finestra
		removeCardToSet(0, cardImg1);
		removeCardToSet(1, cardImg2);
		removeCardToSet(2, cardImg3);
		
    	Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
		window.close();
	}
	
	/* Ogni volta che viene premuto un tasto add:
	 * 		1) controllo che ci sia spazio per inserire la carta
	 * 		2) se c'e' spazio la inserisco, altrimenti non faccio niente
	 * 		3) dopo averla inserita (o dopo il controllo) controllo nuovamente se ci sono 3 carte
	 * 		4) se ci sono 3 carte attivo il button Trade altrimenti lo lascio disabilitato */
	@FXML
	public void addArtilleryButtonPressed(ActionEvent event){
		soundController.cardSound();
		addCardToSet(FIGURE.ARTILLERY);
	}
	
	@FXML
	public void addInfantryButtonPressed(ActionEvent event){
		soundController.cardSound();
		addCardToSet(FIGURE.INFANTRY);
	}
	@FXML
	public void addCavalryButtonPressed(ActionEvent event){
		soundController.cardSound();
		addCardToSet(FIGURE.CAVALRY);
	}
	@FXML
	public void addJollyButtonPressed(ActionEvent event){
		soundController.cardSound();
		addCardToSet(FIGURE.JOLLY);
	}
	
	private void addCardToSet(FIGURE figure) {
		ImageView[] cardImgArr = {cardImg1, cardImg2, cardImg3};
		/* cerco la prima carta di tipo ARTILLERY tra le carte del player
		 * quando la trovo la inserisco usando un altro ciclo, dopodiche' esco dal ciclo */
		for(Card c : GameController.game.getCurrentTurn().getCards()) {
			if(c.getFigure().equals(figure)) {
				/* eseguo un ciclo sui tre slot disponibili, ma appena ne trovo uno libero
			     * inserisco la carta nello slot (array + grafica) e dopo esco dal ciclo */
				for(int i = 0; i < 3; i++) {
					if(cardSet[i] == null) {
						cardSet[i] = c;
						GameController.game.getCurrentTurn().playCard(c);
						try {
							String name;
							try {
								name = c.getTerritory().getName();
							} catch(RuntimeException e) {
								name = "Jolly1";
							}
							InputStream stream = new FileInputStream("src/risk/view/images/cards/" + name + ".png");
							Image img = new Image(stream);
							cardImgArr[i].setImage(img);	
						} catch (FileNotFoundException e) {
							e.printStackTrace();
						}
						break;
					}
				} // END for(cardSet)
				break;
			}
		} // END for(playerCards)
		
		// aggiornamento grafica
		handlePlayerFigureNumber();
		// check per attivazione tradeButton
		handleTradeButton();
			
	}
	
	


}
