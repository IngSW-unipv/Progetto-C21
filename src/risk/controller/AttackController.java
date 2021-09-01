package risk.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import risk.model.DiceShaker;
import risk.model.RisikoGame;

public class AttackController implements Initializable {
	DiceShaker attackDices;
	DiceShaker defenderDices;
	
	@FXML
	private Label territoryAtkLabel, territoryDefLabel, attackerTanksLabel, defenderTanksLabel;
	
	@FXML
	private Button attackButton, cancelButton;
	
	@FXML
	private ToggleButton oneButton, twoButton,  threeButton;
	
	@FXML
	private ImageView RedDice1,RedDice2,RedDice3,BlueDice1,BlueDice2,BlueDice3;
	
	private int atkTank, defTank;	// numero di tank usati per l'attacco e per la difesa
	private int[] atkResults;		// risultati del lancio dei dadi dell'attaccante
	private int[] defResults;
	private RisikoGame game;
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
    	
    	attackButton.setDisable(true);
    	territoryAtkLabel.setText(GameController.getInstance().getAttacker().getName());
    	territoryDefLabel.setText(GameController.getInstance().getDefender().getName());
    	attackerTanksLabel.setText(String.valueOf(GameController.getInstance().getAttacker().getTanks()));
    	defenderTanksLabel.setText(String.valueOf(GameController.getInstance().getDefender().getTanks()));

    	setDefTank();
		attackDices = new DiceShaker();
		defenderDices = new DiceShaker();

		atkResults = new int[3];
		defResults = new int[3];
    	
    

		updateNumberButtons();
		
		
    	
//		menuHandler();
		

			

		
	}
	
    /**
     * sets the number of defending tanks
     */
    private void setDefTank() {
    	if(GameController.getInstance().getDefender().getTanks() > 2) {
    		defTank = 3;
    	}
    	else
    		defTank = GameController.getInstance().getDefender().getTanks();
    }

    /**
     * Manages the attack when the annulla button is pressed
     * @param e is the event
     * @throws IOException
     */
	@FXML
    public void cancelButtonPressed(ActionEvent e) throws IOException {
		// deseleziono i territori attacker e defender
		GameController.getInstance().setAttacker(null);
		GameController.getInstance().setDefender(null);

    	Stage window = (Stage)((Node)e.getSource()).getScene().getWindow();
		window.close();
    }
	
	@FXML	
	private void attackButtonPressed(ActionEvent e) throws IOException {
		ImageView[] attackerDiceImages = {RedDice1,RedDice2,RedDice3};
		ImageView[] defenderDiceImages = {BlueDice1,BlueDice2,BlueDice3};
		InputStream atkStream;
		Image image1; 
		InputStream defStream;
		Image image2; 
			
			atkResults = attackDices.rollDices(atkTank);
			defResults = defenderDices.rollDices(defTank);
			GameController.game.battle(atkResults, defResults, atkTank, defTank, GameController.getInstance().getAttacker(),
					GameController.getInstance().getDefender());
			
			if(atkTank == 2) {
				atkStream = new FileInputStream("src/risk/view/images/dice/" +0+"_red.png");
				image1 = new Image(atkStream);
				attackerDiceImages[2].setImage(image1);	
			}
			
			if(atkTank == 1) {
				atkStream = new FileInputStream("src/risk/view/images/dice/" +0+"_red.png");
				image1 = new Image(atkStream);
				attackerDiceImages[1].setImage(image1);	
				attackerDiceImages[2].setImage(image1);	
			}
			if(defTank == 2) {
				defStream = new FileInputStream("src/risk/view/images/dice/" +0+"_blue.png");
				image2 = new Image(defStream);
				defenderDiceImages[2].setImage(image2);	
			}
			
			if(defTank == 1) {
				defStream = new FileInputStream("src/risk/view/images/dice/" +0+"_blue.png");
				image2 = new Image(defStream);
				defenderDiceImages[1].setImage(image2);	
				defenderDiceImages[2].setImage(image2);	
			}
			for (int i = 0; i < Math.max(atkTank, defTank); i++) {
				
				System.out.println(atkResults[i] + "-------" + defResults[i]);
				
				
				
				atkStream = new FileInputStream("src/risk/view/images/dice/" + atkResults[i] +"_red.png");
				image1 = new Image(atkStream);
				attackerDiceImages[i].setImage(image1);	
				defStream = new FileInputStream("src/risk/view/images/dice/" + defResults[i] +"_blue.png");
				image2 = new Image(defStream);
				defenderDiceImages[i].setImage(image2);	

			}
				
	
			attackerTanksLabel.setText(Integer.toString(GameController.getInstance().getAttacker().getTanks()));
			defenderTanksLabel.setText(Integer.toString(GameController.getInstance().getDefender().getTanks()));
			updateNumberButtons();
			setDefTank();


			if(GameController.getInstance().getDefender().getOwner().equals(GameController.getInstance().getAttacker().getOwner())) {
				defenderTanksLabel.setText("CONQUERED");
				oneButton.setDisable(true);
				twoButton.setDisable(true);
				threeButton.setDisable(true);
				attackButton.setDisable(true);
				GameController.getInstance().updateTerritoriesGraphic();
				}


		}
	

	

	
	@FXML
	private void updateNumberButtons() {
		
		attackButton.setDisable(true);
		oneButton.setSelected(false);
		twoButton.setSelected(false);
		threeButton.setSelected(false);
		if(GameController.getInstance().getAttacker().getTanks() == 1) {
			oneButton.setDisable(true);
    		twoButton.setDisable(true);
    		threeButton.setDisable(true);
		}
		if(GameController.getInstance().getAttacker().getTanks() == 2) {
    		twoButton.setDisable(true);
    		threeButton.setDisable(true);
    	} else if(GameController.getInstance().getAttacker().getTanks() == 3)
    		threeButton.setDisable(true);
    	
    	oneButton.setOnAction(e -> {
    		if(oneButton.isSelected()) {
        		atkTank = 1;
        		attackButton.setDisable(false);
    		} else
    			attackButton.setDisable(true);
		});
    	
    	twoButton.setOnAction(e -> {
    		if(twoButton.isSelected()) {
        		atkTank = 2;
        		attackButton.setDisable(false);
    		} else
    			attackButton.setDisable(true);
		});
    	
    	threeButton.setOnAction(e -> {
    		if(threeButton.isSelected()) {
        		atkTank = 3;
        		attackButton.setDisable(false);
    		} else
    			attackButton.setDisable(true);
		});
		
	}

	// update grafica dadi (cambiare amche i nomi delle immagini dei dadi per fare prima) 
	// update textFill colore della label del difensore quando perde il territorio

}
