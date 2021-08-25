package risk.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ResourceBundle;

import com.sun.javafx.geom.Rectangle;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.SVGPath;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import risk.model.Player;
import risk.model.PlayersList;
import risk.model.RisikoGame;
import risk.model.Territory;
import risk.model.util.GAME_PHASE;

public class GameController implements Initializable {
	
	@FXML
	private Pane rootPane;
	
	@FXML
	private Text territoryText, phaseText, userName1, userName2, userName3, userName4, userName5, userName6;
	
	@FXML
	private VBox usersBox;
	
	@FXML
	private HBox phaseGraphic0, phaseGraphic1, phaseGraphic2, phaseGraphic3;
	
	@FXML
	private Button phaseSwitch;
	
	@FXML
	private ImageView userImage1, userImage2, userImage3, userImage4, userImage5, userImage6, actualPlayerGraphic;
	
	@FXML
	private Circle circleAlaska, circleNorthWestTerritory, circleGreenland, circleAlberta, circleOntario, circleQuebec, circleWesternUnitedStates, circleEasternUnitedStates, circleCentralAmerica, circleVenezuela, circleBrazil, circlePeru, circleArgentina,
					circleIceland, circleScandinavia, circleGreatBritain, circleNorthernEurope, circleWesternEurope, circleSouthernEurope, circleUkraine, circleUral, circleAfghanistan, circleSiberia, circleYakutsk, circleIrkutsk, circleKamchatka, 
					circleMongolia, circleJapan, circleChina, circleSiam, circleIndia, circleMiddleEast, circleEgypt, circleNorthAfrica, circleEastAfrica, circleCongo, circleSouthAfrica, circleMadagascar, circleIndonesia, circleNewGuinea, circleWesternAustralia, circleEasternAustralia;
	
	@FXML
	private Label labelAlaska, labelNorthWestTerritory, labelGreenland, labelAlberta, labelOntario, labelQuebec, labelWesternUnitedStates, labelEasternUnitedStates, labelCentralAmerica, labelVenezuela, labelBrazil, labelPeru, labelArgentina,
					labelIceland, labelScandinavia, labelGreatBritain, labelNorthernEurope, labelWesternEurope, labelSouthernEurope, labelUkraine, labelUral, labelAfghanistan, labelSiberia, labelYakutsk, labelIrkutsk, labelKamchatka, 
					labelMongolia, labelJapan, labelChina, labelSiam, labelIndia, labelMiddleEast, labelEgypt, labelNorthAfrica, labelEastAfrica, labelCongo, labelSouthAfrica, labelMadagascar, labelIndonesia, labelNewGuinea, labelWesternAustralia, labelEasternAustralia;
	
	static RisikoGame game;
	static String terrFile = "src/risk/asset/territories.txt", continentsFile = "src/risk/asset/continents.txt", missionsFile = "src/risk/asset/missions.txt";

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		ArrayList<Player> playersList = PlayersList.getPlayers();
		Player[] playersArr = new Player[playersList.size()];
		playersArr = playersList.toArray(playersArr);
		
		try {
			game = new RisikoGame(playersArr, terrFile, continentsFile, missionsFile);
			phaseText.setText(""+game.getGamePhase());
		} catch (NumberFormatException | IOException e) {
			System.err.println("Impossible to load assets. Aborting...");
			System.out.println(e.getMessage());
			System.out.println(e.getStackTrace());
		}
		
		initializeUserBar();
		updateTerritoriesGraphic();
		switchPlayerGraphic();

	}


	
	/* Method that draw on the game scene the right number of colored users*/
	private void initializeUserBar() {
		// removing useless imageview and text
		for(int i = PlayersList.getPlayers().size()*2; i < 12; i++) {
			usersBox.getChildren().remove(usersBox.getChildren().size()-1);
		}

		initializeUserColorsAndNames();
		
		// place UserBar position to right-center 
		double windowHeight = rootPane.getPrefHeight();
		double usersBoxHeight = usersBox.getChildren().size()/2 * 59;	// children is a square 50x50 (ImageView) + text width is 9 = 59
		double height = (windowHeight - usersBoxHeight)/2;
		usersBox.setLayoutY(height);
	}
	
	/* Method to set the right color and name to gui elements for each player */
	private void initializeUserColorsAndNames() {
		try {
			Text[] userNames = {userName1, userName2, userName3, userName4, userName5, userName6};
			ImageView[] userImages = {userImage1, userImage2, userImage3, userImage4, userImage5, userImage6};
			
			// for every player get color and name to set gui elements
			for(int i = 0; i < 6; i++) {
				String name = PlayersList.getPlayers().get(i).getName();
				String color = PlayersList.getPlayers().get(i).getColor().toString().toLowerCase();

				userNames[i].setText(name);

				InputStream stream = new FileInputStream("src/risk/view/images/users/" + color + ".png");
				Image image = new Image(stream);
				userImages[i].setImage(image);
			}
		} catch(IndexOutOfBoundsException e) {
			// ...
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		}	
	}
	
	private void updateTerritoriesGraphic() {
		Circle[] circles = {circleAlaska, circleNorthWestTerritory, circleGreenland, circleAlberta, circleOntario, circleQuebec, circleWesternUnitedStates, circleEasternUnitedStates, circleCentralAmerica, circleVenezuela, circleBrazil, circlePeru, circleArgentina,
				circleIceland, circleScandinavia, circleGreatBritain, circleNorthernEurope, circleWesternEurope, circleSouthernEurope, circleUkraine, circleUral, circleAfghanistan, circleSiberia, circleYakutsk, circleIrkutsk, circleKamchatka, 
				circleMongolia, circleJapan, circleChina, circleSiam, circleIndia, circleMiddleEast, circleEgypt, circleNorthAfrica, circleEastAfrica, circleCongo, circleSouthAfrica, circleMadagascar, circleIndonesia, circleNewGuinea, circleWesternAustralia, circleEasternAustralia};
		Label[] labels = {labelAlaska, labelNorthWestTerritory, labelGreenland, labelAlberta, labelOntario, labelQuebec, labelWesternUnitedStates, labelEasternUnitedStates, labelCentralAmerica, labelVenezuela, labelBrazil, labelPeru, labelArgentina,
				labelIceland, labelScandinavia, labelGreatBritain, labelNorthernEurope, labelWesternEurope, labelSouthernEurope, labelUkraine, labelUral, labelAfghanistan, labelSiberia, labelYakutsk, labelIrkutsk, labelKamchatka, 
				labelMongolia, labelJapan, labelChina, labelSiam, labelIndia, labelMiddleEast, labelEgypt, labelNorthAfrica, labelEastAfrica, labelCongo, labelSouthAfrica, labelMadagascar, labelIndonesia, labelNewGuinea, labelWesternAustralia, labelEasternAustralia};
		
		ArrayList<Territory> territories = game.getTerritories();
		for(int i = 0; i < territories.size(); i++) {
			String color = territories.get(i).getOwner().getColorName();
			int tanks = territories.get(i).getTanks();
			
			circles[i].setFill(Color.web(color));
			labels[i].setText(String.valueOf(tanks));
			
			if(color.toLowerCase().equals("black") || color.toLowerCase().equals("blue"))
				labels[i].setTextFill(Color.WHITE);
		}
	}

	@FXML
	private void handleSVGPathPressed(MouseEvent event) {
		event.consume();

		Integer n = Integer.parseInt(labelAlberta.getText());

		if (((SVGPath) event.getSource()).getId().equals(labelAlberta.getId())) {
			n += 1;
			labelAlberta.setText("" + n);
		}

		System.out.println(((SVGPath) event.getSource()).getId());

		Territory temp;

		for (Iterator<Territory> it = game.getTerritories().iterator(); it.hasNext();) {
			temp = it.next();
			if (temp.getName().equals(territoryText.getText())) { // se il territorio ha lo stesso nome della label
				if (temp.getOwner().equals(game.getCurrentTurn())) { // se il territorio è del player del turno corrente
					// place tank
					temp.getOwner().placeTank(1);
					// add territory tanks
					game.addTerritoryTanks(temp);
					break;
				} else {
					System.out.println(territoryText.getText() + " is not owned by Current Player");
					System.out.println(temp.getOwner().getName() + " is the owner");
					break;
				}
			}
		}
		if (game.getCurrentTurn().getBonusTanks() == 0)
			System.out.println("success");

		System.out.println(((SVGPath)event.getSource()).getId());

	}


	
	@FXML
	private void handleSVGPathHover(MouseEvent event) {
		event.consume();
		territoryText.setText(((SVGPath)event.getSource()).getId());
	}
	
	@FXML
	private void handlePhaseSwitchPressed(MouseEvent event) {
		/* DA SISTEMARE */
		event.consume();
		if(game.getGamePhase().equals(GAME_PHASE.FIRSTTURN)) {
			game.nextPhase();
			phaseText.setText(""+game.getGamePhase());
			switchPhaseGraphic();
			return;
		}
		if(game.getGamePhase().equals(GAME_PHASE.DRAFT)) {
			game.nextTurn();
			switchPlayerGraphic();
		} 

		phaseText.setText(""+game.getGamePhase());
		
		switchPhaseGraphic();
	}
	
	private void switchPhaseGraphic() {
		switch(game.getGamePhase()) {
			case DRAFT:
				phaseGraphic0.setVisible(false);
				phaseGraphic1.setVisible(true);
				phaseGraphic2.setVisible(false);
				phaseGraphic3.setVisible(false);
				break;
			case ATTACK:
				phaseGraphic0.setVisible(false);
				phaseGraphic1.setVisible(false);
				phaseGraphic2.setVisible(true);
				phaseGraphic3.setVisible(false);
				break;
			case FORTIFY:
				phaseGraphic0.setVisible(false);
				phaseGraphic1.setVisible(false);
				phaseGraphic2.setVisible(false);
				phaseGraphic3.setVisible(true);
				break;
			default:
				phaseGraphic0.setVisible(true);
				phaseGraphic1.setVisible(false);
				phaseGraphic2.setVisible(false);
				phaseGraphic3.setVisible(false);
				break;
		}
	}
	
	private void switchPlayerGraphic() {
		String color = game.getCurrentTurn().getColorName().toLowerCase();
		String path = "src/risk/view/images/users/" + color + ".png";
        File file = new File(path);
        Image image = new Image(file.toURI().toString());
		actualPlayerGraphic.setImage(image);
	}
	
	/**
	 * Method that allows to load a scene in a new window
	 * @param scene is the path of the scene to load
	 * @param title is the title of the window
	 * @param cantclose specifies if the window can be closed until an event occurs (if TRUE, new window can't be closed)
	 * @throws IOException
	 */
	private void windowLoader(String scene, String title, boolean cantclose) throws IOException{
		Parent sceneParent = FXMLLoader.load(getClass().getResource(scene));
		Scene mScene = new Scene(sceneParent);
		Stage window = new Stage();
		window.setResizable(false);
		window.setTitle(title);
		window.setScene(mScene);
		
		if (cantclose) {
			window.setOnCloseRequest(new EventHandler<WindowEvent>() {
				@Override
				public void handle(WindowEvent event) {
					event.consume();
				}
			});
		} 
		window.initModality(Modality.APPLICATION_MODAL);
		window.showAndWait();
	}

	@FXML
	public void playerIconPressed(MouseEvent event){
		try {
			windowLoader("/risk/view/fxml/PlayerInfoWindow.fxml", "Player info", false);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	public void missionIconPressed(MouseEvent event){
		try {
			windowLoader("/risk/view/fxml/MissionImageWindow.fxml", "Mission", false);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	public void cardsIconPressed(MouseEvent event){
//		try {
//			windowLoader("/risk/view/fxml/PlayerInfoWindow.fxml", "Player info", false);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
	}

}
