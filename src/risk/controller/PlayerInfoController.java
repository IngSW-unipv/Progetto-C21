package risk.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import risk.model.Player;
import risk.model.PlayersList;

public class PlayerInfoController implements Initializable {

	@FXML
	private VBox playerInfo;
	//private VBox playerInfo1, playerInfo2, playerInfo3, playerInfo4, playerInfo5, playerInfo6;
	
	@FXML
	private HBox playerInfoColor1, playerInfoColor2, playerInfoColor3, playerInfoColor4, playerInfoColor5, playerInfoColor6;
	
	@FXML
	private Text playerInfoName1, playerInfoName2, playerInfoName3, playerInfoName4, playerInfoName5, playerInfoName6;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		loadPlayersInfo();

	}
	
	private void loadPlayersInfo() {
		
		int playersNum = PlayersList.getPlayers().size();
		HBox[] playerInfoColorList = {playerInfoColor1, playerInfoColor2, playerInfoColor3, playerInfoColor4, playerInfoColor5, playerInfoColor6};
		Text[] playerInfoNameList = {playerInfoName1, playerInfoName2, playerInfoName3, playerInfoName4, playerInfoName5, playerInfoName6};
		
		// set the right number of players info boxes
		for(int i = playersNum; i < 6; i++) {
			playerInfo.getChildren().remove(playerInfo.getChildren().size()-1);
		}

		// load players infos
		for(int i = 0; i < playersNum; i++) {
			try {
				String name = PlayersList.getPlayers().get(i).getName();
				String color = PlayersList.getPlayers().get(i).getColor().toString().toLowerCase();
				int tanks = PlayersList.getPlayers().get(i).getTanks();

				playerInfoNameList[i].setText(name);
				playerInfoColorList[i].setStyle("-fx-background-color: " + color + ";");
				
				if(color.equals("yellow"))
					playerInfoNameList[i].setStyle("-fx-stroke: black;");
				
				// mancano da settare TERRITORI / TRUPPE / CARTE
				
			} catch(IndexOutOfBoundsException e){

			}
		}

	}

}
