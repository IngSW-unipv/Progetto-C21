package risk.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import risk.model.Player;


public class InfosWindowController implements Initializable{

	// sfondo del titolo da colorare del colore del player attuale (oppure si puo' lasciare arancione)
	@FXML
	private AnchorPane titleBg;
	
    @FXML
    private Label titleLabel;	// titolo (es. Turno di player1 / es. Territorio conquistato)

    // label contenente il messaggio (es. hai ottenuto 100 carri / 
    //		es. Hai conquistato l'Afghanistan, scegli quanti carri movere li')
    @FXML
    private Label subtitleLabel;	

    @FXML
    private Button exitButton;	// pulsante per chiudere il pop-up
    
    private String color;
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		
		
		/*** Parte generale da fare sempre ***/
		setColor(GameController.game.getCurrentTurn());
		
		
		switch (GameController.game.getGamePhase()) {
		case DRAFT:
			
			
			if(GameController.getInstance().isCardSceneOpen()) {
				titleLabel.setText("Cards traded");
				subtitleLabel.setText("You received " + CardSceneController.bonus + " bonus armies!");
			} else {
				titleLabel.setText(GameController.game.getCurrentTurn().getName()+"'s turn");			
				subtitleLabel.setText("You received "+GameController.game.getCurrTurnBonusTanks()+" for the possesion of  "
						+GameController.game.getCurrentTurn().getTerritories()+" territories and " + GameController.game.getCurrentTurn().getContinents() + " continents");
			}
			break;
		case ATTACK:
			if(GameController.getInstance().isCardSceneOpen()) {
				titleLabel.setText("Warning");
				subtitleLabel.setText("You can trade in cards only during DRAFT phase");
			} else {
			titleLabel.setText(GameController.getInstance().getTerritory2().getName() +" conquered");
			subtitleLabel.setText("You conquered "+GameController.getInstance().getTerritory2()+" attacking from "+GameController.getInstance().getTerritory1());
			}
			break;
		case FORTIFY:
			if(GameController.getInstance().isCardSceneOpen()) {
				titleLabel.setText("Warning");
				subtitleLabel.setText("You can trade in cards only during DRAFT phase");
			}
			break;
		}
		
	}
	
	@FXML
	public void okButtonClicked(MouseEvent e){
		Stage window = (Stage)((Node)e.getSource()).getScene().getWindow();
		window.close();
	}
	public void setColor(Player p) {
		color = p.getColorName();
		titleBg.setStyle("-fx-background-color:" + color + ";");
		exitButton.setStyle(exitButton.getStyle() + "-fx-base:" + color + ";");
		
		if(color.toLowerCase().equals("pink") || (color.toLowerCase().equals("yellow"))) {
			titleLabel.setStyle(titleLabel.getStyle() + "-fx-text-fill: black;");
			exitButton.setStyle(exitButton.getStyle() + "-fx-text-fill: black;");
		}
	}
}