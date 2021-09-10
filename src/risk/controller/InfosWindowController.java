package risk.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;


public class InfosWindowController implements Initializable{

	// sfondo del titolo da colorare del colore del player atturale (oppure si puo' lasciare arancione)
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
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		/*** Parte generale da fare sempre ***/
		String color = GameController.getInstance().game.getCurrentTurn().getColorName();
		titleBg.setStyle("-fx-background-color:" + color + ";");
		exitButton.setStyle(exitButton.getStyle() + "-fx-base:" + color + ";");
		
		
		/*** Parti specializzate ***/
		
		/*	nuovo turno:
				title -> nomePlayer's turno
				subtitle -> hai ricevuto n carri
				exitButton -> chiude il pop-up
				
			scambio carte non eseguito nel draft (da implementare nel tradeButtonPressed del CardController):
				title -> Attenzione
				subtitle -> puoi eseguire scambi solo durante la fase di draft
				exitButton -> chiude il pop-up
				
			conquista di un territorio:
				title -> nomeTerritorio conquistato
				subtitle -> puoi scegliere quanti carri spostare sul territorio conquistato, oltre a quelli usati durante l'ultimo attacco
				exitButton -> chiude il pop-up e apre la DisplacementWindow
								dato che suppongo che se tu apra la DisplacementWindow da qui, rimarrebbere sotto sia il pop-up che la 
								schermata di attcco, io proverei a fare in questo modo: alla pressione del pulsante chiudo il pop-up, 
								dopodiche il flusso di istruzioni torna all'AttackController che... puo' fare in due modi: il primo è aprire 
								lui stesso la DisplacementScene e una volta chiusa anch'essa si chiudera' anche l'AttackScene; il secondo
								e' chiudersi e aprire la DispacementWindow dal GameController mediante un controllo. (opterei per la prima
								di queste due versioni per semplicità). Quando hai letto sta pappardella poi cancella pure Zanni!
		*/
		
	}

}