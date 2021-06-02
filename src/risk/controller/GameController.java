package risk.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.SVGPath;
import javafx.scene.text.Text;
import risk.model.Player;
import risk.model.PlayersList;

public class GameController implements Initializable {

	@FXML
	private Text territoryText;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		for(Player p : PlayersList.getPlayers())
			System.out.println(p);
	}
	
	@FXML
	private void handleSVGPathPressed(MouseEvent event) {
		event.consume();
		System.out.println(((SVGPath)event.getSource()).getId());
	}
	
	@FXML
	private void handleSVGPathHover(MouseEvent event) {
		event.consume();
		territoryText.setText(((SVGPath)event.getSource()).getId());
	}

}
