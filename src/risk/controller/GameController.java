package risk.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.event.ActionEvent;
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
import javafx.scene.shape.Rectangle;
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
	private HBox phaseGraphic;
	
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
	
	@FXML
	private SVGPath alaska, northWestTerritory, greenland, alberta, ontario, quebec, westernUnitedStates, easternUnitedStates, centralAmerica, venezuela, brazil, peru, argentina,
					iceland, scandinavia, greatBritain, northernEurope, westernEurope, southernEurope, ukraine, ural, afghanistan, siberia, yakutsk, irkutsk, kamchatka, 
					mongolia, japan, china, siam, india, middleEast, egypt, northAfrica, eastAfrica, congo, southAfrica,madagascar, indonesia, newGuinea, westernAustralia, easternAustralia;
	
	static RisikoGame game;
	static String terrFile = "src/risk/asset/territories.txt", continentsFile = "src/risk/asset/continents.txt", missionsFile = "src/risk/asset/missions.txt";
	private int counterConsecutiveClicks = 0;
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
		
		
		
//		// lambda su ogni territorio -> studiare un metodo migliore perchÃ¨ tutte le espressioni eseguono lo stesso codice, cambia solo il nome dello stato
//		alaska.setOnMousePressed((event) -> {
//			Territory t = game.getTerritory("alaska");
//			if(game.getCurrentTurn().equals(t.getOwner()))
//			t.addTanks(1);
//			updateTerritoriesGraphic();
//		});
//		northWestTerritory.setOnMousePressed((event) -> {
//			Territory t = game.getTerritory("northWestTerritory");
//			if(game.getCurrentTurn().equals(t.getOwner()))
//			t.addTanks(1);
//			updateTerritoriesGraphic();
//		});
//		greenland.setOnMousePressed((event) -> {
//			Territory t = game.getTerritory("greenland");
//			if(game.getCurrentTurn().equals(t.getOwner()))
//			t.addTanks(1);
//			updateTerritoriesGraphic();
//		});
	}


	
	/* Method that draw on the game scene the right number of colored users*/
	private void initializeUserBar() {
		// removing useless imageview and text
		for(int i = game.getPlayers().length*2; i < 12; i++) {
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
				String name = game.getPlayers()[i].getName();
				String color = game.getPlayers()[i].getColor().toString().toLowerCase();

				userNames[i].setText(name);

				InputStream stream = new FileInputStream("src/risk/view/images/users/" + color + ".png");
				Image image = new Image(stream);
				userImages[i].setImage(image);
				
				if(i == 0)
					userNames[i].setUnderline(true);
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
		boolean enter = true;
		Territory t = game.getTerritory(((SVGPath) event.getSource()).getId().replace(" ", ""));

		if (game.getCurrentTurn().equals(t.getOwner()) && counterConsecutiveClicks < 3) {
			if (t.getOwner().getBonusTanks() > 0) {
				t.getOwner().placeTank(1);
				t.addTanks(1);
				counterConsecutiveClicks++;
				
				if(game.getCurrentTurn().getColorName().toLowerCase().equals("yellow"))
					phaseSwitch.setTextFill(Color.BLACK);
				else
					phaseSwitch.setTextFill(Color.WHITE);
				phaseSwitch.setText(String.valueOf(game.getCurrentTurn().getBonusTanks()));
				
				// creare un metodo da qui in poi
				if(game.getGamePhase().equals(GAME_PHASE.FIRSTTURN)) {
					if (game.getCurrentTurn().getBonusTanks() == 0) {
						nextPhase(); // problema che il primo va nella prossima fase e fa cambiare turno e quindi non devo farlo cambiare sotto
					enter = false;
				}
			}


			}
		}
		if (counterConsecutiveClicks >= 3 && game.getGamePhase().equals(GAME_PHASE.FIRSTTURN)) {
			counterConsecutiveClicks = 0;
			if (enter) {
				nextTurn();
			phaseSwitch.setText(String.valueOf(game.getCurrentTurn().getBonusTanks()));
		}
	}

		updateTerritoriesGraphic();

//		System.out.println(((SVGPath) event.getSource()).getId());
//
//		Territory temp;
//
//		for (Iterator<Territory> it = game.getTerritories().iterator(); it.hasNext();) {
//			temp = it.next();
//			if (temp.getName().equals(territoryText.getText())) { // se il territorio ha lo stesso nome della label
//				if (temp.getOwner().equals(game.getCurrentTurn())) { // se il territorio ï¿½ del player del turno corrente
//					// place tank
//					temp.getOwner().placeTank(1);
//					// add territory tanks
//					game.addTerritoryTanks(temp);
//					break;
//				} else {
//					System.out.println(territoryText.getText() + " is not owned by Current Player");
//					System.out.println(temp.getOwner().getName() + " is the owner");
//					break;
//				}
//			}
//		}
//		if (game.getCurrentTurn().getBonusTanks() == 0)
//			System.out.println("success");
//
//		System.out.println(((SVGPath) event.getSource()).getId());

	}


	
	@FXML
	private void handleSVGPathHover(MouseEvent event) {
		event.consume();
		territoryText.setText(((SVGPath)event.getSource()).getId());
	}
	
	@FXML
	private void handlePhaseSwitchPressed(MouseEvent event) {
		/* DA SISTEMARE */
		System.out.println(game.firstPhaseEnded());
		event.consume();
		if (game.firstPhaseEnded()) {
			// non entra mai qua pur cambiando fase sotto e non so perchè???
			System.out.println("success");
		nextPhase();
	}
}
	
	/**
	 * Switches the game turn to the next one
	 */
	public void nextTurn() {
	
		// passa il turno al giocatore successivo
		game.nextTurn();
		switchPhaseGraphic();
		switchPlayerGraphic();
//		if(game.getGamePhase() == GAME_PHASE.FIRSTTURN) {	// circlo per il first turn
//			int i = 0;
//			while(game.getCurrentTurn().getBonusTanks() == 0) {
//				if(i == game.getPlayers().length) {
//					break;
//				}
//				game.nextTurn();
//				i++;
//			}
//		}
		
		// Ogni volta che il turno passa ad un altro giocatore, il suo nome viene sottolineato
		Text[] userNames = {userName1, userName2, userName3, userName4, userName5, userName6};
		for(Text t : userNames) {
			t.setUnderline(false);
		}
		userNames[game.getTurnCounter()].setUnderline(true);
		
//		if(!(game.getGamePhase() == GAME_PHASE.FIRSTTURN))
//			nextPhase();
//		
//		territory1 = null;
//		territory2 = null;
//		if(game.getGamePhase() != GAME_PHASE.FIRSTTURN && game.getCurrentTurn().isAI()) {
//			game.getCurrentTurn().playTurn();
//		}
	}
	
	/**
	 * Switches the game phase to the next one
	 */
	public void nextPhase() {
//		switch(game.getGamePhase()) {
//			case FORTIFY:
//				nextPhase.setText("POSIZIONAMENTO");
//				endTurn.setDisable(true);
//				nextPhase.setDisable(true);
//				break;
//			case DRAFT:
//				nextPhase.setText("SPOSTAMENTO");
//				nextPhase.setDisable(false);
//				endTurn.setDisable(true);
//				break;
//			case ATTACK:
//				nextPhase.setDisable(true);
//				endTurn.setDisable(false);
//				break;
//		}
		
		if(game.getGamePhase().equals(GAME_PHASE.FIRSTTURN) && !game.firstPhaseEnded()) {
			game.nextTurn();
			
			if(game.getCurrentTurn().getColorName().toLowerCase().equals("yellow"))
				phaseSwitch.setTextFill(Color.BLACK);
			else
				phaseSwitch.setTextFill(Color.WHITE);
			phaseSwitch.setText(String.valueOf(game.getCurrentTurn().getBonusTanks()));
			
			switchPhaseGraphic();
			switchPlayerGraphic();
			System.out.println(game.firstPhaseEnded());
			return;
		} else if (game.firstPhaseEnded()) {
			System.out.println("CAMBIO FASE");
			game.nextPhase();
			phaseText.setText(game.getGamePhase().toString());
			if(game.getGamePhase().equals(GAME_PHASE.DRAFT))
				System.out.println("bella");
			switchPhaseGraphic();
			switchPlayerGraphic();
		}
	}
	
	private void switchPhaseGraphic() {
		ArrayList<Rectangle> rectangles = getRectangles(phaseGraphic);
		switch(game.getGamePhase()) {
			case DRAFT:
				for(int i = 0; i < rectangles.size(); i++) {
					if(i == 0)
						rectangles.get(i).setFill(Color.web(game.getCurrentTurn().getColorName().toLowerCase()));
					else
						rectangles.get(i).setFill(Color.TRANSPARENT);
				}
				break;
			case ATTACK:
				for(int i = 0; i < rectangles.size(); i++) {
					if(i == 1)
						rectangles.get(i).setFill(Color.web(game.getCurrentTurn().getColorName().toLowerCase()));
					else
						rectangles.get(i).setFill(Color.TRANSPARENT);
				}
				break;
			case FORTIFY:
				for(int i = 0; i < rectangles.size(); i++) {
					if(i == 2)
						rectangles.get(i).setFill(Color.web(game.getCurrentTurn().getColorName().toLowerCase()));
					else
						rectangles.get(i).setFill(Color.TRANSPARENT);
				}
				break;
			default:
				for(int i = 0; i < rectangles.size(); i++) {
					rectangles.get(i).setFill(Color.TRANSPARENT);
				}
				break;
		}
	}
	
	private void switchPlayerGraphic() {
		String color = game.getCurrentTurn().getColorName().toLowerCase();
		String path = "src/risk/view/images/users/" + color + ".png";
        File file = new File(path);
        Image image = new Image(file.toURI().toString());
		actualPlayerGraphic.setImage(image);
		phaseSwitch.setStyle("-fx-background-radius: 100;-fx-font-family:\"Arial Black\";-fx-font-size:18;-fx-text-fill:white;-fx-background-color:" + color);
	}
	
	private ArrayList<Rectangle> getRectangles(HBox hb) {
		ArrayList<Rectangle> rectangles = new ArrayList<>();
		for (Node currentNode : hb.getChildren()){
		    if (currentNode instanceof Rectangle){
		    	rectangles.add((Rectangle)currentNode);
		    }
		}
		return rectangles;
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
	
	/* Method called when exit button is pressed */
	@FXML
	private void exit(final ActionEvent event) {
		event.consume();
		Platform.exit();
	}

}
