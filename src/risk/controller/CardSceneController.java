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
	public static int bonus = 0;
	private SoundController soundController = new SoundController();

	
	
	
	
	/**
	 * Method called to initialize cards and buttons in window
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		/* variables initialize */
		cardSet = new Card[3];
		cardSet[0] = null;
		cardSet[1] = null;
		cardSet[2] = null;
		

		/* graphic initialize */
		tradeButton.setDisable(true);
		handlePlayerFigureNumber();
		

	}
	
	/**
	 * Method called to handle cards in CardScene window
	 */
	private void handlePlayerFigureNumber() {
		/* counters reset  */
		artilleryNum = 0; 
		infantryNum = 0; 
		cavalryNum = 0; 
		jollyNum = 0;
		
		/* counting number of figures */
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
		
		/* graphic set */
		artilleryLeftLabel.setText(String.valueOf(artilleryNum) + " left");
		infantryLeftLabel.setText(String.valueOf(infantryNum) + " left");
		cavalryLeftLabel.setText(String.valueOf(cavalryNum) + " left");
		jollyLeftLabel.setText(String.valueOf(jollyNum) + " left");
		
		/* enable/disable buttons */
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
	
	/**
	 * Method called to check cards added by players 
	 */
	private void handleTradeButton() {
		/* check if 3 cards are added,
		 * then check if tris is corrected and enable trade button */
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
	
	/**
	 * Method to remove a chosen card from the view
	 * @param index of the card to remove from the scene
	 * @param cardImg ImageView of the card to remove
	 */
	private void removeCardToSet(int index, ImageView cardImg) {
		// card given back to player if it is not null
		if(cardSet[index] != null)
			GameController.game.getCurrentTurn().giveCard(cardSet[index]);
		// update position
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
	
	
	/**
	 * Method to trade card pressing the button trade
	 * @param event ActionEvent button pressed
	 */
	@FXML
	public void tradeButtonPressed(ActionEvent event){
		if(GameController.getInstance().game.getGamePhase().equals(GAME_PHASE.DRAFT)) {
			
			if(cardSet[0] != null && cardSet[1] != null && cardSet[2] != null) {
				bonus = GameController.game.checkTris(cardSet[0], cardSet[1], cardSet[2]);
			}
			if(GameController.getInstance().getMusic())soundController.tradeSound();
			GameController.game.getCurrentTurn().giveBonusTanks(bonus);
			GameController.getInstance().setPhaseSwitchText(String.valueOf(GameController.getInstance().game.getCurrentTurn().getBonusTanks()));
			GameController.getInstance().setPhaseTextArea(GameController.game.getCurrentTurn().getName() + " received " + bonus + " bonus armies");
			GameController.getInstance().updateCardsNumber();
			try {
				GameController.getInstance().windowLoader("/risk/view/fxml/InfosWindow.fxml", "Territory conquered", true, true);
				
			} catch (IOException ex) {
				ex.printStackTrace();
			}

	    	Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
			window.close();
		} else {
			try {
				GameController.getInstance().windowLoader("/risk/view/fxml/InfosWindow.fxml", "Territory conquered", true, true);
				
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}

	}
	
	/**
	 * Method to exit from the Card Scene window
	 * @param event ActionEvent cancel button pressed
	 */
	@FXML
	public void cancelButtonPressed(ActionEvent event){
		// give back cards and close
		removeCardToSet(0, cardImg1);
		removeCardToSet(1, cardImg2);
		removeCardToSet(2, cardImg3);
		
    	Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
		window.close();
	}
	
	/* Every time add is pressed:
	 * 		1) check if a card can be put
	 * 		2) if there is a blank, i can insert it
	 * 		3) after inserted, check again if there are 3 cards
	 * 		4) 3 cards-> button trade enabled  */
	@FXML
	public void addArtilleryButtonPressed(ActionEvent event){
		if(GameController.getInstance().getMusic())soundController.cardSound();
		addCardToSet(FIGURE.ARTILLERY);
	}
	
	@FXML
	public void addInfantryButtonPressed(ActionEvent event){
		if(GameController.getInstance().getMusic())soundController.cardSound();
		addCardToSet(FIGURE.INFANTRY);
	}
	@FXML
	public void addCavalryButtonPressed(ActionEvent event){
		if(GameController.getInstance().getMusic())soundController.cardSound();
		addCardToSet(FIGURE.CAVALRY);
	}
	@FXML
	public void addJollyButtonPressed(ActionEvent event){
		if(GameController.getInstance().getMusic())soundController.cardSound();
		addCardToSet(FIGURE.JOLLY);
	}
	/**
	 * Method to add cards to set View
	 * @param figure ImageView of cards
	 */
	private void addCardToSet(FIGURE figure) {
		ImageView[] cardImgArr = {cardImg1, cardImg2, cardImg3};
		/* check the first ARTILLERY card in player's hand
		 * when found insert in another loop, then exit */
		for(Card c : GameController.game.getCurrentTurn().getCards()) {
			if(c.getFigure().equals(figure)) {
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
		
		// graphic update
		handlePlayerFigureNumber();
		//trade Button enabled
		handleTradeButton();
			
	}
	
	


}
