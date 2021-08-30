package risk.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ResourceBundle;

import com.sun.tools.javac.code.Attribute.Array;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.stage.Stage;
import risk.model.DiceShaker;
import risk.model.Player;
import risk.model.PlayersList;
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
	
	private int atkTank, defTank;	// numero di tank usati per l'attacco e per la difesa
	private int[] atkResults;
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
	public void attackButtonPressed(ActionEvent e) throws IOException {
		atkResults = attackDices.rollDices(atkTank);
		defResults = defenderDices.rollDices(atkTank);
	}

}
