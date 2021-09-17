package risk.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import risk.model.PlayersList;

public class PlayerInfoController implements Initializable {

	@FXML
	private VBox playerInfo;
	
	@FXML
	private HBox playerInfoColor1, playerInfoColor2, playerInfoColor3, playerInfoColor4, playerInfoColor5, playerInfoColor6;
	
	@FXML
	private Text playerInfoName1, playerInfoName2, playerInfoName3, playerInfoName4, playerInfoName5, playerInfoName6;
	
	@FXML
	private Text playerInfoTerritories1, playerInfoTerritories2, playerInfoTerritories3, playerInfoTerritories4, playerInfoTerritories5, playerInfoTerritories6;
	
	@FXML
	private Text playerInfoTanks1, playerInfoTanks2, playerInfoTanks3, playerInfoTanks4, playerInfoTanks5, playerInfoTanks6;
	
	@FXML
	private Text playerInfoCards1, playerInfoCards2, playerInfoCards3, playerInfoCards4, playerInfoCards5, playerInfoCards6;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		loadPlayersInfo();

	}
	
	private void loadPlayersInfo() {
		
		int playersNum = PlayersList.getPlayers().size();
		HBox[] playerInfoColorList = {playerInfoColor1, playerInfoColor2, playerInfoColor3, playerInfoColor4, playerInfoColor5, playerInfoColor6};
		Text[] playerInfoNameList = {playerInfoName1, playerInfoName2, playerInfoName3, playerInfoName4, playerInfoName5, playerInfoName6};
		Text[] playerInfoTerritoriesList = {playerInfoTerritories1, playerInfoTerritories2, playerInfoTerritories3, playerInfoTerritories4, playerInfoTerritories5, playerInfoTerritories6};
		Text[] playerInfoTanksList = {playerInfoTanks1, playerInfoTanks2, playerInfoTanks3, playerInfoTanks4, playerInfoTanks5, playerInfoTanks6};
		Text[] playerInfoCardsList = {playerInfoCards1, playerInfoCards2, playerInfoCards3, playerInfoCards4, playerInfoCards5, playerInfoCards6};
		
		// set the right number of players info boxes
		for(int i = playersNum; i < 6; i++) {
			playerInfo.getChildren().remove(playerInfo.getChildren().size()-1);
		}

		// load players infos
		for(int i = 0; i < playersNum; i++) {
			try {
				String name = PlayersList.getPlayers().get(i).getName();
				String color = PlayersList.getPlayers().get(i).getColor().toString().toLowerCase();
				int territories = PlayersList.getPlayers().get(i).getTerritories();
				int tanks = PlayersList.getPlayers().get(i).getTanks();
				int cards = PlayersList.getPlayers().get(i).getCards().size();
				
				playerInfoNameList[i].setText(name);
				playerInfoColorList[i].setStyle("-fx-background-color: " + color + ";");
				
				if(color.equals("yellow"))
					playerInfoNameList[i].setStyle("-fx-stroke: black;");
				
				playerInfoTerritoriesList[i].setText(String.valueOf(territories));
				playerInfoTanksList[i].setText(String.valueOf(tanks));
				playerInfoCardsList[i].setText(String.valueOf(cards));

				
			} catch(IndexOutOfBoundsException e){

			}
		}

	}

	
}
