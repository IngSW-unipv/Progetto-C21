package risk.controller;

import risk.model.DiceShaker;
import java.net.URL;

import java.io.File;
import java.io.IOException;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;



import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;
import risk.model.Territory;

public class AttackController implements Initializable {
	DiceShaker attack;
	DiceShaker defende;
	
	@FXML
	private Label territoryAtkLabel, territoryDefLabel, attackerTanksLabel, defenderTanksLabel;
	
	@FXML
	private Button attackButton, cancelButton;
	
	@FXML
	private ToggleButton oneButton, twoButton,  threeButton;
	
	private int atkTank, defTank;	// numero di tank usati per l'attacco e per la difesa
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
    	
    	attackButton.setDisable(true);
    	territoryAtkLabel.setText(GameController.getInstance().getAttacker().getName());
    	territoryDefLabel.setText(GameController.getInstance().getDefender().getName());
    	attackerTanksLabel.setText(String.valueOf(GameController.getInstance().getAttacker().getTanks()));
    	defenderTanksLabel.setText(String.valueOf(GameController.getInstance().getDefender().getTanks()));
    	
    	setDefTank();
    	
//    	atkResults = new int[3];
//    	defResults = new int[3];
    	
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
		// update grafico

    	Stage window = (Stage)((Node)e.getSource()).getScene().getWindow();
		window.close();
    }
	
}
