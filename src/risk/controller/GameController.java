package risk.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;
import risk.model.Player;
import risk.model.PlayersList;

public class GameController implements Initializable {

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		for(Player p : PlayersList.getPlayers())
			System.out.println(p);

	}

}
