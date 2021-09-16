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
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import risk.model.DiceShaker;
import risk.model.RisikoGame;
import risk.model.Territory;

/**
 * @author utente
 *
 */
public class AttackController implements Initializable {

	/**
	 * Dices of attacker
	 */
	DiceShaker attackDices;
	
	/**
	 * Dices of defender
	 */
	DiceShaker defenderDices;

	/**
	 * Pane
	 */
	@FXML
	private AnchorPane rootPane;

	/**
	 * Label
	 */
	@FXML
	private Label territoryAtkLabel, territoryDefLabel, attackerTanksLabel, defenderTanksLabel;

	/**
	 * AttackScene button
	 */
	@FXML
	private Button attackButton, cancelButton, exitButton;

	/**
	 * number of tanks button
	 */
	@FXML
	private ToggleButton oneButton, twoButton,  threeButton;

	/**
	 * ImageView of dice
	 */
	@FXML
	private ImageView RedDice1,RedDice2,RedDice3,BlueDice1,BlueDice2,BlueDice3;

	/**
	 * number of tanks
	 */
	private int atkTank, defTank;
	
	/**
	 * attacker dices results 
	 */
	private int[] atkResults;
	/**
	 * defender dices results
	 */
	private int[] defResults;		
	
	/**
	 * Risiko Game 
	 */
	static RisikoGame game;
	
	/**
	 * Territory 1
	 */
	private Territory territory1= GameController.getInstance().getTerritory1();
	
	/**
	 * Territory 2
	 */
	private Territory territory2= GameController.getInstance().getTerritory2();
	
	/**
	 * SoundController
	 */
	private SoundController soundController;






	/**
	 * Method to initialize the window
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		attackButton.setDisable(true);
		territoryAtkLabel.setText(territory1.getName());
		territoryDefLabel.setText(territory2.getName());
		attackerTanksLabel.setText(String.valueOf(territory1.getTanks()));
		defenderTanksLabel.setText(String.valueOf(territory2.getTanks()));
		soundController = new SoundController();
		if(GameController.getInstance().getMusic()) soundController.battleMusic(); 
		setDefTank();
		attackDices = new DiceShaker();
		defenderDices = new DiceShaker();

		atkResults = new int[3];
		defResults = new int[3];

		updateNumberButtons();

		// hide window on H hold pressed
		rootPane.setOnKeyPressed((event) -> {
			if (event.getCode() == KeyCode.H) {
				//rootPane.getScene().getWindow().hide();
				rootPane.getScene().getWindow().setOpacity(0);
			}
		});
		rootPane.setOnKeyReleased((event) -> {
			if (event.getCode() == KeyCode.H) {
				//rootPane.getScene().getWindow().show();
				rootPane.getScene().getWindow().setOpacity(1);
			}
		});
	}

	/**
	 * sets the number of defending tanks
	 */
	private void setDefTank() {
		if(territory2.getTanks() > 2) {
			defTank = 3;
		}
		else
			defTank = territory2.getTanks();
	}

	/**
	 * Method that implements AI attack
	 * @param t1 Territory attacker 
	 * @param t2 Territory defender 
	 */
	public void aiAttack(Territory t1,Territory t2) {

		int atNumber;
		int deNumber;

		if(t1.getTanks() > 3) {
			atNumber = 3;
		}
		else atNumber = t1.getTanks() -1;

		if(t2.getTanks() > 2) {
			deNumber = 3;
		} else {
			deNumber = t2.getTanks();
		}
		System.out.println("atnumber "+atNumber);
		System.out.println("denumber "+deNumber);
		System.out.println("t1 "+t1.getName());
		System.out.println("t2 "+t2.getName());

		DiceShaker attackDices2 = new DiceShaker();
		DiceShaker defenderDices2 = new DiceShaker();

		int[] atkResults2 = new int[3];
		int[] defResults2 = new int[3];

		atkResults2 = attackDices2.rollDices(atNumber);
		defResults2 = defenderDices2.rollDices(deNumber);

		GameController.game.battle(atkResults2,defResults2, atNumber, deNumber,t1,t2);
		for(int i = 0 ; i < Math.max(atNumber, deNumber); i++) {
			System.out.println(""+atkResults2[i]);
			System.out.println(""+defResults2[i]);
		}

		GameController.getInstance().setPhaseTextArea(GameController.getInstance().getCurrentPlayer().getName()
				+" attacked "+t2.getName()+" from "+t1.getName());


		if(t2.getOwner().equals(t1.getOwner())) {
			GameController.getInstance().setPhaseTextArea(GameController.getInstance().getCurrentPlayer().getName()
					+" conquered "+t2.getName());

			GameController.game.giveCard();
			//			GameController.getInstance().setTerritory1(null);
			//	    	GameController.getInstance().setTerritory2(null);
			GameController.getInstance().updateCardsNumber();


		}

	}

	
	/**
	 * Method that close the window and resets all
	 */
	private void onClosing() {
		GameController.getInstance().setTerritory1(null);
		GameController.getInstance().setTerritory2(null);
		GameController.getInstance().clearAllTerritories();
		GameController.getInstance().setAttackButtonDisable(true);
		GameController.getInstance().setPhaseSwitchButtonDisable(false);
		GameController.getInstance().updateTerritoriesGraphic();
		GameController.getInstance().playMusic();
		if(GameController.getInstance().getMusic())soundController.stopMusic();
	}
	/**
	 * Manages the attack when the cancel button is pressed
	 * @param e ActionEvent ActionEvent cancel button pressed
	 * @throws IOException if there is no file
	 */
	@FXML
	public void cancelButtonPressed(ActionEvent e) throws IOException {

		//GameController.getInstance().clearSelectedTerritory(GameController.getInstance().getAttacker());
		//GameController.getInstance().clearSelectedTerritory(GameController.getInstance().getAttacker());
		// deseleziono i territori attacker e defender
		onClosing();
		Stage window = (Stage)((Node)e.getSource()).getScene().getWindow();
		window.close();
	}

	/**
	 * Method called to attack Territory 2 from Territory 1
	 * @param e ActionEvent attack button pressed
	 * @throws IOException if there are no dice images
	 */
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
		GameController.game.battle(atkResults, defResults, atkTank, defTank, territory1,
				territory2);

		GameController.getInstance().setPhaseTextArea(GameController.getInstance().getCurrentPlayer().getName()
				+" attacked "+territory2.getName()+" from "+territory1.getName());

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


		attackerTanksLabel.setText(Integer.toString(territory1.getTanks()));
		defenderTanksLabel.setText(Integer.toString(territory2.getTanks()));
		updateNumberButtons();
		setDefTank();


		if(GameController.getInstance().getTerritory2().getOwner().equals(GameController.getInstance().getTerritory1().getOwner())) {
			defenderTanksLabel.setText("CONQUERED");
			if(GameController.getInstance().getMusic())soundController.stopMusic();
			if(GameController.getInstance().getMusic())soundController.conqueredSound();
			GameController.game.giveCard();
			GameController.getInstance().updateCardsNumber();
			oneButton.setDisable(true);
			twoButton.setDisable(true);
			threeButton.setDisable(true);
			attackButton.setDisable(true);
			GameController.getInstance().updateTerritoriesGraphic();

			try {
				GameController.getInstance().windowLoader("/risk/view/fxml/InfosWindow.fxml", "Territory conquered", true, true);

			} catch (IOException ex) {
				ex.printStackTrace();
			}


			rootPane.getScene().getWindow().setOpacity(0);
			if (GameController.getInstance().getTerritory1().getTanks() > 1) {
				try {
					GameController.getInstance().windowLoader("/risk/view/fxml/DisplacementScene.fxml", "Displacement", true, true);
				} catch (IOException ex) {
					ex.printStackTrace();
				}
			}
			onClosing();
			Stage window = (Stage)((Node)e.getSource()).getScene().getWindow();
			window.close();
		}


	}

	/**
	 * Method that permits to attack until the conquest or if there are not enough tanks
	 * @param e ActionEvent blitz button pressed
	 */
	@FXML	
	private void blitzButtonPressed(ActionEvent e) {
		
	}



	/**
	 * Method that updates the number buttons 
	 */
	@FXML
	private void updateNumberButtons() {

		attackButton.setDisable(true);
		oneButton.setSelected(false);
		twoButton.setSelected(false);
		threeButton.setSelected(false);
		if(territory1.getTanks() == 1) {
			oneButton.setDisable(true);
			twoButton.setDisable(true);
			threeButton.setDisable(true);
		}
		if(territory1.getTanks() == 2) {
			twoButton.setDisable(true);
			threeButton.setDisable(true);
		} else if(territory1.getTanks() == 3)
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





}
