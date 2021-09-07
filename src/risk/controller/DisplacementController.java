package risk.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import risk.model.RisikoGame;

public class DisplacementController implements Initializable {
	private RisikoGame game;
	/* queste due label mostrano i nomi dei due territori scelti */
	@FXML
	private Label territory1Label, territory2Label;
	
	/* le label newTank mostrano i valori aggiornati dei tank sui due territori;
	con aggiornati si intende che ogni volta che cambio il numero di carri da spostare
	questi due valori cambiano (es. terr1 5 tank, terr2 10 tank -> sto per spostare
	3 tank -> i valori delle due label saranno 5-3=2 e 10+3=13).
	La label depTank indica il numero di tank che sto per spostare, nell'esempio 3 */
	@FXML
	private Label newTank1Label, newTank2Label, depTankLabel;
	
	/* i pulsanti min e max spostano lo slider al min/max e di conseguenza il numero
	di tank che sto per spostare dal min, cioe' 1, al max, cioe' N-1.
	I pulsanti plus e minus incrementano e decrementano il numero di carri che sto per spostare */
	@FXML
	private Button minButton, maxButton, minusButton, plusButton;
	
	/* lo slider permette di modificare il numero di tank che voglio spostare, 
	e va da 1 a N-1 */
	@FXML
	private Slider slider;
	
	/*** N.B. ogni volta che premo uno dei 4 pulsanti o muovo lo slider, devo aggiornare le tre label ***/
	
	
	/* Il pulsante depButton esegue lo spostamento a livello di gioco, scrive nella textArea 
	 * del gioco lo spostamento avvenuto, deseleziona i territori e chiude la schermata di spostamento.
	Il pulsante cancel deve azzerare la selezione dei due territori e chiudere la schermata */
	@FXML
	private Button depButton, cancelButton;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		territory1Label.setText(GameController.getInstance().getTerritory1().getName());
		territory2Label.setText(GameController.getInstance().getTerritory2().getName());
	}

}
