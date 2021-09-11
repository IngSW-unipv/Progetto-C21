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
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.TextArea;
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
import javafx.stage.StageStyle;
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
	private Text territoryText, phaseText, userName1, userName2, userName3, userName4, userName5, userName6,cardNumberText;
	
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
					circleIceland, circleScandinavia, circleGreatBritain, circleNorthernEurope, circleWesternEurope, circleSouthernEurope, circleRussia, circleUral, circleAfghanistan, circleSiberia, circleYakutsk, circleIrkutsk, circleKamchatka, 
					circleMongolia, circleJapan, circleChina, circleSiam, circleIndia, circleMiddleEast, circleEgypt, circleNorthAfrica, circleEastAfrica, circleCongo, circleSouthAfrica, circleMadagascar, circleIndonesia, circleNewGuinea, circleWesternAustralia, circleEasternAustralia;
	
	@FXML
	private Label labelAlaska, labelNorthWestTerritory, labelGreenland, labelAlberta, labelOntario, labelQuebec, labelWesternUnitedStates, labelEasternUnitedStates, labelCentralAmerica, labelVenezuela, labelBrazil, labelPeru, labelArgentina,
					labelIceland, labelScandinavia, labelGreatBritain, labelNorthernEurope, labelWesternEurope, labelSouthernEurope, labelRussia, labelUral, labelAfghanistan, labelSiberia, labelYakutsk, labelIrkutsk, labelKamchatka, 
					labelMongolia, labelJapan, labelChina, labelSiam, labelIndia, labelMiddleEast, labelEgypt, labelNorthAfrica, labelEastAfrica, labelCongo, labelSouthAfrica, labelMadagascar, labelIndonesia, labelNewGuinea, labelWesternAustralia, labelEasternAustralia;
	
	@FXML
	private SVGPath alaska, northWestTerritory, greenland, alberta, ontario, quebec, westernUnitedStates, easternUnitedStates, centralAmerica, venezuela, brazil, peru, argentina,
					iceland, scandinavia, greatBritain, northernEurope, westernEurope, southernEurope, russia, ural, afghanistan, siberia, yakutsk, irkutsk, kamchatka, 
					mongolia, japan, china, siam, india, middleEast, egypt, northAfrica, eastAfrica, congo, southAfrica,madagascar, indonesia, newGuinea, westernAustralia, easternAustralia;
	@FXML
	private TextArea phasesDescriptionArea;
	
	@FXML
	private VBox attackButtonIcon;
	
	@FXML
	private ScrollBar scrollBar;

	
	private SVGPath[] paths;
	
	private boolean fortified;
	
	
	
	public boolean isFortified() {
		return fortified;
	}

	public void setFortified(boolean fortified) {
		this.fortified = fortified;
	}

	private Territory territory1 = null, territory2 = null;
	private SVGPath svgTerr2;
	
	
	static RisikoGame game;
	private static GameController instance;

	/**
	 * Sets the instance to this instance of GameSceneController
	 */
	public GameController() {
		instance = this;
		
	}

	/**
	 * Instance getter
	 * @return instance
	 */
	public static GameController getInstance() {
		return instance;
	}
	
	static String terrFile = "src/risk/asset/territories.txt", continentsFile = "src/risk/asset/continents.txt", missionsFile = "src/risk/asset/missions.txt";
	private int counterConsecutiveClicks = 0;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		ArrayList<Player> playersList = PlayersList.getPlayers();
		Player[] playersArr = new Player[playersList.size()];
		playersArr = playersList.toArray(playersArr);
		paths = new SVGPath[] {alaska, northWestTerritory, greenland, alberta, ontario, quebec, westernUnitedStates, easternUnitedStates, centralAmerica, venezuela, brazil, peru, argentina,
				iceland, scandinavia, greatBritain, northernEurope, westernEurope, southernEurope, russia, ural, afghanistan, siberia, yakutsk, irkutsk, kamchatka, 
				mongolia, japan, china, siam, india, middleEast, egypt, northAfrica, eastAfrica, congo, southAfrica,madagascar, indonesia, newGuinea, westernAustralia, easternAustralia};
		
		

		
		try {
			game = new RisikoGame(playersArr, terrFile, continentsFile, missionsFile);
			phaseText.setText(""+game.getGamePhase());
			phaseText.setText("FIRST TURN");
			phaseSwitch.setText(String.valueOf(game.getCurrentTurn().getBonusTanks()));
			phasesDescriptionArea.setText("PHASES:\n");
			attackButtonIcon.setDisable(true);
			if (game.getCurrentTurn().isAI()) {
				game.getCurrentTurn().playTurn();
			}
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
	
	protected void updateTerritoriesGraphic() {
		Circle[] circles = {circleAlaska, circleNorthWestTerritory, circleGreenland, circleAlberta, circleOntario, circleQuebec, circleWesternUnitedStates, circleEasternUnitedStates, circleCentralAmerica, circleVenezuela, circleBrazil, circlePeru, circleArgentina,
				circleIceland, circleScandinavia, circleGreatBritain, circleNorthernEurope, circleWesternEurope, circleSouthernEurope, circleRussia, circleUral, circleAfghanistan, circleSiberia, circleYakutsk, circleIrkutsk, circleKamchatka, 
				circleMongolia, circleJapan, circleChina, circleSiam, circleIndia, circleMiddleEast, circleEgypt, circleNorthAfrica, circleEastAfrica, circleCongo, circleSouthAfrica, circleMadagascar, circleIndonesia, circleNewGuinea, circleWesternAustralia, circleEasternAustralia};
		Label[] labels = {labelAlaska, labelNorthWestTerritory, labelGreenland, labelAlberta, labelOntario, labelQuebec, labelWesternUnitedStates, labelEasternUnitedStates, labelCentralAmerica, labelVenezuela, labelBrazil, labelPeru, labelArgentina,
				labelIceland, labelScandinavia, labelGreatBritain, labelNorthernEurope, labelWesternEurope, labelSouthernEurope, labelRussia, labelUral, labelAfghanistan, labelSiberia, labelYakutsk, labelIrkutsk, labelKamchatka, 
				labelMongolia, labelJapan, labelChina, labelSiam, labelIndia, labelMiddleEast, labelEgypt, labelNorthAfrica, labelEastAfrica, labelCongo, labelSouthAfrica, labelMadagascar, labelIndonesia, labelNewGuinea, labelWesternAustralia, labelEasternAustralia};
		
		ArrayList<Territory> territories = game.getTerritories();
		for(int i = 0; i < territories.size(); i++) {
			String color = territories.get(i).getOwner().getColorName();
			int tanks = territories.get(i).getTanks();
			
			circles[i].setFill(Color.web(color));
			labels[i].setText(String.valueOf(tanks));
			
			if(color.toLowerCase().equals("black") || color.toLowerCase().equals("blue")) {
				labels[i].setTextFill(Color.WHITE);
			} else {
				labels[i].setTextFill(Color.BLACK);
			}
		}
		
	}
	

	@FXML
	private void handleSVGPathPressed(MouseEvent event) {
		event.consume();
		boolean enter = true;
		Territory t = game.getTerritory(((SVGPath) event.getSource()).getId().replace(" ", ""));
		
		
		switch (game.getGamePhase()) {
		case FIRSTTURN:
				
			if (game.getCurrentTurn().equals(t.getOwner()) && counterConsecutiveClicks < 3) {
				if (t.getOwner().getBonusTanks() > 0) {
					int ntanks = 21; //cambiare solo valore alla variabile e non piu ai due parametri (serve per il print)
					t.getOwner().placeTank(ntanks); //cambia metti 1 al posto di 21
					t.addTanks(ntanks); //cambia metti 1 al posto di 21
					
					this.setPhaseTextArea(game.getCurrentTurn().getName()+
							" has placed "+ntanks+" tanks"+" in "+ t.getName());

					counterConsecutiveClicks++;

					phaseSwitch.setText(String.valueOf(game.getCurrentTurn().getBonusTanks()));

					if (game.getCurrentTurn().getBonusTanks() == 0) {
						counterConsecutiveClicks = 0;
						nextPhase();
						enter = false;
					}

				}
			}

			if (counterConsecutiveClicks >= 3) {
				counterConsecutiveClicks = 0;
				if (enter) {
					nextTurn();
					phaseSwitch.setText(String.valueOf(game.getCurrentTurn().getBonusTanks()));
				}
			}


			break;
		case DRAFT:
			fortified=false;
			if (game.getCurrentTurn().equals(t.getOwner())) {
				if (t.getOwner().getBonusTanks() > 0) {
					t.getOwner().placeTank(1);
					t.addTanks(1);
					
					this.setPhaseTextArea(game.getCurrentTurn().getName()+
							" has placed 1 "+"tank"+" in "+ t.getName());
					
					switchPlayerGraphic();
					phaseSwitch.setText(String.valueOf(game.getCurrentTurn().getBonusTanks()));

					if (game.getCurrentTurn().getBonusTanks() == 0) {
						phaseSwitch.setText(">>");
						enter = false;
					}

				}
			}

			break;

		case ATTACK:

			/*
			 * 1) scegliere un proprio territorio che abbia >1 tank
			 * 2) scegliere un territorio da attaccare che sia di un avversario e che sia confinante con il nostro territorio 
			 */
			SVGPath svg = ((SVGPath) event.getSource());

			if(territory1 == null) {
				// se e' il tuo e ha >1 tank lo selezioni
				if(game.getCurrentTurn().equals(t.getOwner()) && t.getTanks() > 1) {
					territory1 = t;
					setSelectedTerritoryGraphic(svg, true);
					phaseSwitch.setDisable(true);
					attackButtonIcon.setDisable(true);
				}
			// se invece premo lo stesso territorio gia' selezionato, allora lo deseleziono e deseleziono anche il secondo
			} else if(svg.getId().replace(" ", "").toLowerCase().equals(territory1.getName().toLowerCase())) {
				territory1 = null;
				setSelectedTerritoryGraphic(svg, false);
				territory2 = null;
				if(svgTerr2 != null)
					setSelectedTerritoryGraphic(svgTerr2, false);
				phaseSwitch.setDisable(false);
				attackButtonIcon.setDisable(true);
			}


			// se terr1 e' selezionato e terr2 invece no
			if(territory1 != null && territory2 == null) {
				// se il terr premuto e' confinante con il primo lo seleziono
				if(!game.getCurrentTurn().equals(t.getOwner()) && territory1.isConfinante(t)) {
					territory2 = t;
					svgTerr2 = svg;
					setSelectedTerritoryGraphic(svg, true);
					attackButtonIcon.setDisable(false);
					phaseSwitch.setDisable(true);
				}
			// se invece premo lo stesso territorio gia' selezionato, allora lo deseleziono
			} else if(territory1 != null && territory2 != null) {
				if(svg.getId().replace(" ", "").toLowerCase().equals(territory2.getName().toLowerCase())) {
					territory2 = null;
					setSelectedTerritoryGraphic(svg, false);
					if(territory1 == null)
						phaseSwitch.setDisable(false);
					else
						phaseSwitch.setDisable(true);
					attackButtonIcon.setDisable(true);
				} else if(!game.getCurrentTurn().equals(t.getOwner()) && territory1.isConfinante(t)) {
					setSelectedTerritoryGraphic(svgTerr2, false);
					territory2 = t;
					svgTerr2 = svg;
					setSelectedTerritoryGraphic(svg, true);
					attackButtonIcon.setDisable(false);
					phaseSwitch.setDisable(true);
				}
			}

			break;

		case FORTIFY:
			
			
			
			SVGPath svg1 = ((SVGPath) event.getSource());
			if(!fortified) {
				if(territory1 == null) {
					/* 
					 * una volta scelto il territorio attaccante:
					 * 		1) settare il territorio selezionato graficamente (+10% darker)
					 * 		2) permettere la scelta del territorio da attaccare
					 */
					if(game.getCurrentTurn().equals(t.getOwner()) && t.getTanks() > 1) {
						territory1 = t;
						setSelectedTerritoryGraphic(svg1, true);
						phaseSwitch.setDisable(true);
					}
	
				} else if(svg1.getId().replace(" ", "").toLowerCase().equals(territory1.getName().toLowerCase())) {
					territory1 = null;
					setSelectedTerritoryGraphic(svg1, false);
					phaseSwitch.setDisable(false);
				}
	
				if(territory1 != null && territory2 == null) {
	
					if(game.getCurrentTurn().equals(t.getOwner()) && territory1.isConfinante(t)) {
						territory2 = t;
						
						
						phaseSwitch.setDisable(false);
						
						
						// apri schemata displacement
						try {
							
							windowLoader("/risk/view/fxml/DisplacementScene.fxml", "Displacement", true, false);
						} catch (IOException e) {
							e.printStackTrace();
						}	
					}
				}

			}
						
						
			break;
		}

		updateTerritoriesGraphic();

		if (game.verifyMission()) {
			System.out.println("hai vinto, fare grafica");
			Platform.exit();
		}

	}
	
	protected void setSelectedTerritoryGraphic(SVGPath svg, boolean col) {
		if(col){
			// colora il territorio selezionato
			switch(svg.getStyleClass().get(0)) {
			case "northAmerica":
				svg.setStyle("-fx-fill: #CC6D47");
				break;
			case "southAmerica":
				svg.setStyle("-fx-fill: #7AA5B3");
				break;
			case "europa":
				svg.setStyle("-fx-fill: #A58CA5");
				break;
			case "oceania":
				svg.setStyle("-fx-fill: #CCA786");
				break;
			case "africa":
				svg.setStyle("-fx-fill: #AB8554");
				break;
			case "asia":
				svg.setStyle("-fx-fill: #5DBB5D");
				break;
			}//aaa
		} else {
			//decolora il territorio selezionato
			switch(svg.getStyleClass().get(0)) {
			case "northAmerica":
				svg.setStyle("");
				svg.getStyleClass().clear();
				svg.getStyleClass().add("northAmerica");
				break;
			case "southAmerica":
				svg.setStyle("");
				svg.getStyleClass().clear();
				svg.getStyleClass().add("southAmerica");
				break;
			case "europa":
				svg.setStyle("");
				svg.getStyleClass().clear();
				svg.getStyleClass().add("europa");
				break;
			case "oceania":
				svg.setStyle("");
				svg.getStyleClass().clear();
				svg.getStyleClass().add("oceania");
				break;
			case "africa":
				svg.setStyle("");
				svg.getStyleClass().clear();
				svg.getStyleClass().add("africa");
				break;
			case "asia":
				svg.setStyle("");
				svg.getStyleClass().clear();
				svg.getStyleClass().add("asia");
				break;
			}
		}
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
		if (!game.getGamePhase().equals(GAME_PHASE.FIRSTTURN) && game.getCurrentTurn().getBonusTanks() == 0) {
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
		
//		if(game.getCurrentTurn().isAI()) {
//			game.getCurrentTurn().playTurn();
//			nextTurn();
//			}
	}
	
	/**
	 * Switches the game phase to the next one
	 */
	public void nextPhase() {

		switch (game.getGamePhase()) {
		case FIRSTTURN:
			if (!game.firstPhaseEnded()) {
				game.nextTurn();
				if (game.getCurrentTurn().getColorName().toLowerCase().equals("yellow")||game.getCurrentTurn().getColorName().toLowerCase().equals("pink"))
					phaseSwitch.setTextFill(Color.BLACK);
				else
					phaseSwitch.setTextFill(Color.WHITE);
				phaseSwitch.setText(String.valueOf(game.getCurrentTurn().getBonusTanks()));

				switchPhaseGraphic();
				switchPlayerGraphic();
				return;
			} else {
				game.nextPhase();
				phaseText.setText(game.getGamePhase().toString());
				phaseSwitch.setText(String.valueOf(game.getCurrentTurn().getBonusTanks()));
				setPhaseTextArea("\n" + game.getCurrentTurn().getName() + " turn! You received " + game.getCurrentTurn().getBonusTanks() + " bonus armies");
				switchPhaseGraphic();
				switchPlayerGraphic();
				
				/* PROVA */
				if (game.getCurrentTurn().isAI()) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("New turn");
				alert.setHeaderText(null);
				alert.setContentText(game.getCurrentTurn().getName() + " turn! You received " + game.getCurrentTurn().getBonusTanks() + " bonus armies");
				alert.showAndWait();
			}
			}
			break;
		case DRAFT:
			game.nextPhase();
			phaseText.setText(game.getGamePhase().toString());
			phaseSwitch.setText(String.valueOf(game.getCurrentTurn().getBonusTanks()));
			updateTerritoriesGraphic();
			switchPhaseGraphic();
			switchPlayerGraphic();
			break;
		case ATTACK:
			game.nextPhase();
			phaseText.setText(game.getGamePhase().toString());
			updateTerritoriesGraphic();
			switchPhaseGraphic();
			switchPlayerGraphic();
			break;
		case FORTIFY:
			nextTurn();
			game.nextPhase();
			phaseText.setText(game.getGamePhase().toString());
			phaseSwitch.setText(String.valueOf(game.getCurrentTurn().getBonusTanks()));
			setPhaseTextArea("\n" + game.getCurrentTurn().getName() + " turn! You received " + game.getCurrentTurn().getBonusTanks() + " bonus armies");
			updateTerritoriesGraphic();
			switchPhaseGraphic();
			switchPlayerGraphic();
			updateCardsNumber();
			if (game.getCurrentTurn().isAI()) {
			/* PROVA */
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("New turn");
			alert.setHeaderText(null);
			alert.setContentText(game.getCurrentTurn().getName() + " turn! You received " + game.getCurrentTurn().getBonusTanks() + " bonus armies");
			alert.showAndWait();
			break;
		}
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
				phaseSwitch.setText(">>");
				break;
			case FORTIFY:
				for(int i = 0; i < rectangles.size(); i++) {
					if(i == 2)
						rectangles.get(i).setFill(Color.web(game.getCurrentTurn().getColorName().toLowerCase()));
					else
						rectangles.get(i).setFill(Color.TRANSPARENT);
				}
				phaseSwitch.setText(">>");
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
		if(game.getCurrentTurn().getColorName().toLowerCase().equals("yellow")||game.getCurrentTurn().getColorName().toLowerCase().equals("pink")) {
			phaseSwitch.setStyle("-fx-background-radius: 100;-fx-font-family:\"Arial Black\";-fx-font-size:18;-fx-base:" + color);
			phaseSwitch.setTextFill(Color.BLACK);
		}
		else {
			phaseSwitch.setStyle("-fx-background-radius: 100;-fx-font-family:\"Arial Black\";-fx-font-size:18;-fx-base:" + color);
			phaseSwitch.setTextFill(Color.WHITE);
		}
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
	public void windowLoader(String scene, String title, boolean cantclose, boolean undecorated) throws IOException{
		Parent sceneParent = FXMLLoader.load(getClass().getResource(scene));
		Scene mScene = new Scene(sceneParent);
		Stage window = new Stage();
		/*** ***/
		if(undecorated)
			window.initStyle(StageStyle.UNDECORATED);
		/*** ***/
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
			windowLoader("/risk/view/fxml/PlayerInfoWindow.fxml", "Player info", false, false);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	public void missionIconPressed(MouseEvent event){
		try {
			windowLoader("/risk/view/fxml/MissionImageWindow.fxml", "Mission", false, false);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	public void cardIconPressed(MouseEvent event){
		try {
			windowLoader("/risk/view/fxml/CardScene.fxml", "Cards", true, false);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	public void attackButtonIconPressed(MouseEvent event){
		if(game.getGamePhase().equals(GAME_PHASE.ATTACK)) {
			try {
				windowLoader("/risk/view/fxml/AttackScene.fxml", "Attack", true, false);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void clearAllTerritories() {
		
		for(int i =0; i < paths.length;i++) {
			
			switch(paths[i].getStyleClass().get(0)) {
			case "northAmerica":
				paths[i].setStyle("");
				paths[i].getStyleClass().clear();
				paths[i].getStyleClass().add("northAmerica");
				break;
			case "southAmerica":
				paths[i].setStyle("");
				paths[i].getStyleClass().clear();
				paths[i].getStyleClass().add("southAmerica");
				break;
			case "europa":
				paths[i].setStyle("");
				paths[i].getStyleClass().clear();
				paths[i].getStyleClass().add("europa");
				break;
			case "oceania":
				paths[i].setStyle("");
				paths[i].getStyleClass().clear();
				paths[i].getStyleClass().add("oceania");
				break;
			case "africa":
				paths[i].setStyle("");
				paths[i].getStyleClass().clear();
				paths[i].getStyleClass().add("africa");
				break;
			case "asia":
				paths[i].setStyle("");
				paths[i].getStyleClass().clear();
				paths[i].getStyleClass().add("asia");
				break;
			}
		}
			
	}
	
	public void clearSelectedTerritory(Territory t) {
		for(int i =0; i < paths.length;i++) {
			
			if(t.getName().toLowerCase().equals(paths[i].getId().toLowerCase())) {
				System.out.println("si");
				paths[i].setStyle("");
				paths[i].getStyleClass().clear();
				paths[i].getStyleClass().add("europa");
			}
		}
	}
	
	public void setPhaseTextArea(String text) {
		phasesDescriptionArea.setText(phasesDescriptionArea.getText()+text+"\n");
		phasesDescriptionArea.setScrollTop(Double.MAX_VALUE);
	}
	
	protected void updateCardsNumber() {
		cardNumberText.setText(""+game.getCurrentTurn().getCards().size());
	}
	public void setPhaseSwitchText(String text) {
		phaseSwitch.setText(text);
	}
	public Territory getTerritory1() {
		return territory1;
	}
	
	public Territory getTerritory2() {
		return territory2;
	}
	
	public void setTerritory1(Territory t) {
		territory1 = t;
	}
	
	public void setTerritory2(Territory t) {
		territory2 = t;
	}
	
	public Player getCurrentPlayer() {
		return game.getCurrentTurn();
	}
	
	public void setAttackButtonDisable(boolean t) {
		attackButtonIcon.setDisable(t);
	}
	public void setPhaseSwitchButtonDisable(boolean t) {
		phaseSwitch.setDisable(t);
	}
	public static RisikoGame getGame() {
		return game;
	}

	/* Method called when exit button is pressed */
	@FXML
	private void exit(final ActionEvent event) {
		event.consume();
		Platform.exit();
	}
  
}
