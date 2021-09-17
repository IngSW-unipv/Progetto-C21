package risk.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import risk.model.Territory;

public class DisplacementController implements Initializable {
	

	/* root pane */
	@FXML
	private AnchorPane rootPane;
	
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
	private Territory territory1;
	private Territory territory2;
	private SoundController soundController;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		territory1 = GameController.getInstance().getTerritory1();
		territory2 = GameController.getInstance().getTerritory2();
		DoubleProperty d1 =new SimpleDoubleProperty(territory1.getTanks());
		DoubleProperty d2 =new SimpleDoubleProperty(territory2.getTanks());
		soundController = new SoundController();
		territory1Label.setText(territory1.getName());
		territory2Label.setText(territory2.getName());
		newTank1Label.setText(""+territory1.getTanks());
		newTank2Label.setText(""+territory2.getTanks());
		slider.setValue(1);
		slider.setMax(((int)territory1.getTanks()-1));
		slider.setMin(((int)1));
		depTankLabel.textProperty().bind(Bindings.format("%.0f",slider.valueProperty()));
		newTank1Label.textProperty().bind(Bindings.format("%.0f",d1.subtract(slider.valueProperty())));
		newTank2Label.textProperty().bind(Bindings.format("%.0f",d2.add(slider.valueProperty())));
		
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
	
	@FXML
    public void cancelButtonPressed(MouseEvent e){
		
		//GameController.getInstance().clearSelectedTerritory(GameController.getInstance().getAttacker());
		//GameController.getInstance().clearSelectedTerritory(GameController.getInstance().getAttacker());
		// deseleziono i territori attacker e defender
		resetAll();
    	Stage window = (Stage)((Node)e.getSource()).getScene().getWindow();
		window.close();
    }
	
	@FXML
    public void minButtonPressed(MouseEvent e){
		
		slider.setValue(1);
		
    }

	@FXML
	public void maxButtonPressed(MouseEvent e) {

		slider.setValue(slider.getMax());

	}

	@FXML
	public void minusButtonPressed(MouseEvent e) {

		slider.setValue(slider.getValue() - 1);

	}

	@FXML
	public void plusButtonPressed(MouseEvent e) {

		slider.setValue(slider.getValue()+1);

	}
	
	@FXML
    public void displacementButtonPressed(MouseEvent e){
		
		GameController.getInstance().stopMusic();
		GameController.game.moveTanks(territory1, territory2, Integer.parseInt(depTankLabel.getText()));
		GameController.getInstance().updateTerritoriesGraphic();
		GameController.getInstance().setFortified(true);
		if(GameController.getInstance().getMusic())soundController.displacementSound();
		resetAll();
		Stage window = (Stage)((Node)e.getSource()).getScene().getWindow();
		window.close();
		GameController.getInstance().playMusic();
    }
	
	public void resetAll() {
		
		GameController.getInstance().setTerritory1(null);
		GameController.getInstance().setTerritory2(null);
		GameController.getInstance().clearAllTerritories();
	}

}
