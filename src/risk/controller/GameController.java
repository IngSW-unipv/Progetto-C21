package risk.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.ScaleTransition;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
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
import javafx.scene.control.ScrollBar;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
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
import javafx.util.Duration;
import risk.model.Player;
import risk.model.PlayersList;
import risk.model.RisikoGame;
import risk.model.Territory;
import risk.model.util.GAME_PHASE;

/**
 * @author utente
 *
 */
/**
 * @author utente
 *
 */
public class GameController implements Initializable {
<<<<<<< HEAD

=======
	
	/**
	 *  root Pane
	 */
>>>>>>> bcb3213ce553f853e3a5d6a003de39e2b08ea570
	@FXML
	private Pane rootPane;
	
	/**
	 * Pause menu pane
	 */
	@FXML
	private BorderPane pauseMenuPane;
	
	/**
	 * Text label
	 */
	@FXML
	private Text territoryText, phaseText, userName1, userName2, userName3, userName4, userName5, userName6,cardNumberText;
	
	/**
	 * VBox 
	 */
	@FXML
	private VBox usersBox, attackButtonIcon;
	
	/**
	 * HBox containing graphic of changing phase
	 */
	@FXML
<<<<<<< HEAD
	private HBox phaseGraphic, plusAnimBox;
=======
	private HBox phaseGraphic;
	
	/**
	 * Button to switch phase 
	 */
>>>>>>> bcb3213ce553f853e3a5d6a003de39e2b08ea570
	@FXML
	private Button phaseSwitch;
	
	/**
	 * Image view of GameScene
	 */
	@FXML
	private ImageView userImage1, userImage2, userImage3, userImage4, userImage5, userImage6, actualPlayerGraphic, speakerImage;
	
	/**
	 * Circles of Territory 
	 */
	@FXML
	private Circle circleAlaska, circleNorthWestTerritory, circleGreenland, circleAlberta, circleOntario, circleQuebec, circleWesternUnitedStates, circleEasternUnitedStates, circleCentralAmerica, circleVenezuela, circleBrazil, circlePeru, circleArgentina,
<<<<<<< HEAD
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
=======
					circleIceland, circleScandinavia, circleGreatBritain, circleNorthernEurope, circleWesternEurope, circleSouthernEurope, circleRussia, circleUral, circleAfghanistan, circleSiberia, circleYakutsk, circleIrkutsk, circleKamchatka, 
					circleMongolia, circleJapan, circleChina, circleSiam, circleIndia, circleMiddleEast, circleEgypt, circleNorthAfrica, circleEastAfrica, circleCongo, circleSouthAfrica, circleMadagascar, circleIndonesia, circleNewGuinea, circleWesternAustralia, circleEasternAustralia;
	/**
	 * Label of Territory
	 */
	@FXML
	private Label labelAlaska, labelNorthWestTerritory, labelGreenland, labelAlberta, labelOntario, labelQuebec, labelWesternUnitedStates, labelEasternUnitedStates, labelCentralAmerica, labelVenezuela, labelBrazil, labelPeru, labelArgentina,
					labelIceland, labelScandinavia, labelGreatBritain, labelNorthernEurope, labelWesternEurope, labelSouthernEurope, labelRussia, labelUral, labelAfghanistan, labelSiberia, labelYakutsk, labelIrkutsk, labelKamchatka, 
					labelMongolia, labelJapan, labelChina, labelSiam, labelIndia, labelMiddleEast, labelEgypt, labelNorthAfrica, labelEastAfrica, labelCongo, labelSouthAfrica, labelMadagascar, labelIndonesia, labelNewGuinea, labelWesternAustralia, labelEasternAustralia;
	/**
	 * SVGPath of the Territory
	 */
	@FXML
	private SVGPath alaska, northWestTerritory, greenland, alberta, ontario, quebec, westernUnitedStates, easternUnitedStates, centralAmerica, venezuela, brazil, peru, argentina,
					iceland, scandinavia, greatBritain, northernEurope, westernEurope, southernEurope, russia, ural, afghanistan, siberia, yakutsk, irkutsk, kamchatka, 
					mongolia, japan, china, siam, india, middleEast, egypt, northAfrica, eastAfrica, congo, southAfrica,madagascar, indonesia, newGuinea, westernAustralia, easternAustralia;
	/**
	 * TextArea describing what happens during phases
	 */
>>>>>>> bcb3213ce553f853e3a5d6a003de39e2b08ea570
	@FXML
	private TextArea phasesDescriptionArea;
	
	/**
	 * Scroll bar fot TextArea
	 */
	@FXML
	private ScrollBar scrollBar;

<<<<<<< HEAD

=======
	
	/**
	 * Array of SVGPath
	 */
>>>>>>> bcb3213ce553f853e3a5d6a003de39e2b08ea570
	private SVGPath[] paths;
	
	/**
	 * boolean to manage control
	 */
	private boolean fortified, cardSceneOpen, music = true;
	
	/**
	 * SoundController that manages music
	 */
	private SoundController soundController;
	
	/**
	 * Language of rules
	 */
	private String rulesLeng = null;
	
	/**
	 * Territory to select 
	 */
	private Territory territory1 = null, territory2 = null;
	
	/**
	 * SVGPath of second Territory
	 */
	private SVGPath svgTerr2;
	
	/**
	 * Static variable of RisikoGame
	 */
	public static RisikoGame game;
<<<<<<< HEAD

=======
	
	/**
	 * Static instance of GameController
	 */
>>>>>>> bcb3213ce553f853e3a5d6a003de39e2b08ea570
	private static GameController instance;
	
	/**
	 * Path of file to read
	 */
	static String terrFile = "src/risk/asset/territories.txt", continentsFile = "src/risk/asset/continents.txt", missionsFile = "src/risk/asset/missions.txt";
	
	/**
	 * Counter of clicks
	 */
	private int counterConsecutiveClicks = 0;

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
<<<<<<< HEAD

	static String terrFile = "src/risk/asset/territories.txt", continentsFile = "src/risk/asset/continents.txt", missionsFile = "src/risk/asset/missions.txt";
	private int counterConsecutiveClicks = 0;
=======
	
	
>>>>>>> bcb3213ce553f853e3a5d6a003de39e2b08ea570

	/**
	 * Method to start the game and load assets
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		ArrayList<Player> playersList = PlayersList.getPlayers();
		Player[] playersArr = new Player[playersList.size()];
		playersArr = playersList.toArray(playersArr);
		soundController = new SoundController();
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
				nextPhase();
			}
		} catch (NumberFormatException | IOException e) {
			System.err.println("Impossible to load assets. Aborting...");
			System.out.println(e.getMessage());
			System.out.println(e.getStackTrace());
		}

		initializeUserBar();
		updateTerritoriesGraphic();
		switchPlayerGraphic();
		playMusic();
	}


<<<<<<< HEAD

	/* Method that draw on the game scene the right number of colored users*/
=======
	
	/**
	 * Method that draws on the game scene the right number of colored users
	 */
>>>>>>> bcb3213ce553f853e3a5d6a003de39e2b08ea570
	private void initializeUserBar() {
		// removing useless ImageView and text
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
<<<<<<< HEAD

	/* Method to set the right color and name to gui elements for each player */
=======
	
	
	/**
	 * Method to set the right color and name to gui elements for each player
	 */
>>>>>>> bcb3213ce553f853e3a5d6a003de39e2b08ea570
	private void initializeUserColorsAndNames() {
		try {
			Text[] userNames = {userName1, userName2, userName3, userName4, userName5, userName6};
			ImageView[] userImages = {userImage1, userImage2, userImage3, userImage4, userImage5, userImage6};
			// for every player get color and name to set gui elements
			for(int i = 0; i < 6; i++) {
				String name = game.getPlayers()[i].getName();
				String color = game.getPlayers()[i].getColor().toString().toLowerCase();

				userNames[i].setText(name);
				if(game.getPlayers()[i].isEliminated()) {
					InputStream stream = new FileInputStream("src/risk/view/images/users/eliminated.png");
					Image image = new Image(stream);
					userImages[i].setImage(image);
				}else {
					InputStream stream = new FileInputStream("src/risk/view/images/users/" + color + ".png");
					Image image = new Image(stream);
					userImages[i].setImage(image);
				}


				if(i == 0)
					userNames[i].setUnderline(true);
			}
		} catch(IndexOutOfBoundsException e) {
			// ...
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		}	
	}
<<<<<<< HEAD

=======
	
	/**
	 *  Method called to update the graphic of Territory labels
	 */
>>>>>>> bcb3213ce553f853e3a5d6a003de39e2b08ea570
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


	/**
	 * Method that permits to play game phases clicking on territory
	 * @param event MouseEvent when a Territory is clicked
	 */
	@FXML
	private void handleSVGPathPressed(MouseEvent event) {
		event.consume();
		boolean enter = true;
		Territory t = game.getTerritory(((SVGPath) event.getSource()).getId().replace(" ", ""));


		switch (game.getGamePhase()) {
		case FIRSTTURN:

			if (!game.getCurrentTurn().isAI()) {
				if (game.getCurrentTurn().equals(t.getOwner()) && counterConsecutiveClicks < 3) {
					if (t.getOwner().getBonusTanks() > 0) {

<<<<<<< HEAD
						int ntanks = 1; // cambiare solo valore alla variabile e non piu ai due parametri (serve per il print)

						t.getOwner().placeTank(ntanks); //cambia metti 1 al posto di 21
						t.addTanks(ntanks); //cambia metti 1 al posto di 21
=======
					int ntanks = 1; 
					t.getOwner().placeTank(ntanks); 
					t.addTanks(ntanks);
					
					this.setPhaseTextArea(game.getCurrentTurn().getName()+
							" has placed "+ntanks+" tanks"+" in "+ t.getName());
>>>>>>> bcb3213ce553f853e3a5d6a003de39e2b08ea570

						this.setPhaseTextArea(game.getCurrentTurn().getName()+" has placed "+ntanks+" tanks"+" in "+ t.getName());

						counterConsecutiveClicks++;

						phaseSwitch.setText(String.valueOf(game.getCurrentTurn().getBonusTanks()));

						if (game.getCurrentTurn().getBonusTanks() == 0) {
							counterConsecutiveClicks = 0;
							nextPhase();
							enter = false;
						}

						// animation
						tankAddedAnimation(event);
					}
				}

				if (counterConsecutiveClicks >= 3) {
					counterConsecutiveClicks = 0;
					if (enter) {
						nextTurn();
						phaseSwitch.setText(String.valueOf(game.getCurrentTurn().getBonusTanks()));
					}
				}
			}

			//		else { // se � AI non deve fare sta roba, i tank li mette il playTurn di player e devo
			//				// farglieli mettere a 3 alla volta
			//
			//			if (game.getCurrentTurn().equals(t.getOwner())) {
			//				if (t.getOwner().getBonusTanks() > 0) {
			//					int ntanks = 1; // cambiare solo valore alla variabile e non piu ai due parametri (serve per il
			//									// print)
			//					t.getOwner().placeTank(ntanks); // cambia metti 1 al posto di 21
			//					t.addTanks(ntanks); // cambia metti 1 al posto di 21
			//
			//					this.setPhaseTextArea(game.getCurrentTurn().getName() + " has placed " + ntanks + " tanks" + " in "
			//							+ t.getName());
			//
			//					phaseSwitch.setText(String.valueOf(game.getCurrentTurn().getBonusTanks()));
			//
			//					if (game.getCurrentTurn().getBonusTanks() == 0) {
			//						nextPhase();
			//					}
			//
			//				}
			//			}
			//
			//		}

			break;
		case DRAFT:
			fortified=false;
			if (game.getCurrentTurn().equals(t.getOwner())) {
				if (t.getOwner().getBonusTanks() > 0) {
					t.getOwner().placeTank(1);
					t.addTanks(1);

					this.setPhaseTextArea(game.getCurrentTurn().getName()+" has placed 1 "+"tank"+" in "+ t.getName());

					switchPlayerGraphic();
					phaseSwitch.setText(String.valueOf(game.getCurrentTurn().getBonusTanks()));

					if (game.getCurrentTurn().getBonusTanks() == 0) {
						phaseSwitch.setText(">>");
						//enter = false;
					}
					
					// animation
					tankAddedAnimation(event);
				}
			}

			break;

		case ATTACK:

			/*
			 * 1) choose a Territory with number of Tanks > 1
			 * 2) choose a Territory belonging to an opponent and bordering the attacking one
			 */
			SVGPath svg = ((SVGPath) event.getSource());

			if(territory1 == null) {
				// if the first Territory belongs to the current Player and owns more than 1 tank
				if(game.getCurrentTurn().equals(t.getOwner()) && t.getTanks() > 1) {
					territory1 = t;
					setSelectedTerritoryGraphic(svg, true);
					phaseSwitch.setDisable(true);
					attackButtonIcon.setDisable(true);
				}
<<<<<<< HEAD
				// se invece premo lo stesso territorio gia' selezionato, allora lo deseleziono e deseleziono anche il secondo
=======
			// if the first selected Territory is selected again, then deselect the first and the second Territory
>>>>>>> bcb3213ce553f853e3a5d6a003de39e2b08ea570
			} else if(svg.getId().replace(" ", "").toLowerCase().equals(territory1.getName().toLowerCase())) {
				territory1 = null;
				setSelectedTerritoryGraphic(svg, false);
				territory2 = null;
				if(svgTerr2 != null)
					setSelectedTerritoryGraphic(svgTerr2, false);
				phaseSwitch.setDisable(false);
				attackButtonIcon.setDisable(true);
			}


			// if Territory1 is selected and Territory2 is not selected
			if(territory1 != null && territory2 == null) {
				// if the second selected Territory is bordering the first one and it does not belong to the current player
				if(!game.getCurrentTurn().equals(t.getOwner()) && territory1.isConfinante(t)) {
					territory2 = t;
					svgTerr2 = svg;
					setSelectedTerritoryGraphic(svg, true);
					attackButtonIcon.setDisable(false);

					/*** ***/			
					ScaleTransition st = new ScaleTransition(Duration.millis(500), attackButtonIcon);
					st.setByX(.05);
					st.setByY(.05);
					st.setCycleCount(2);
					st.setAutoReverse(true);  
					st.play();
					/*** ***/

					phaseSwitch.setDisable(true);
				}
<<<<<<< HEAD
				// se invece premo lo stesso territorio gia' selezionato, allora lo deseleziono
=======
			//if the second selected Territory is selected again, then deselect only the second Territory
>>>>>>> bcb3213ce553f853e3a5d6a003de39e2b08ea570
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

					/*** ***/			
					ScaleTransition st = new ScaleTransition(Duration.millis(500), attackButtonIcon);
					st.setByX(.05);
					st.setByY(.05);
					st.setCycleCount(2);
					st.setAutoReverse(true);  
					st.play();
					/*** ***/

					phaseSwitch.setDisable(true);
				}
			}

			break;

		case FORTIFY:



			SVGPath svg1 = ((SVGPath) event.getSource());
			if(!fortified) {
				if(territory1 == null) {
					/* 
					 * Chosen the attacker Territory:
					 * 		1) set the selected Territory (+10% darker)
					 * 		2) permit the choice of the defending Territory
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
<<<<<<< HEAD


						// apri schemata displacement
=======
						
						
						// open DisplacementScene
>>>>>>> bcb3213ce553f853e3a5d6a003de39e2b08ea570
						try {

							windowLoader("/risk/view/fxml/DisplacementScene.fxml", "Displacement", true, true);
						} catch (IOException e) {
							e.printStackTrace();
						}	
					}
				}

			}


			break;
		}

		updateTerritoriesGraphic();

	}
<<<<<<< HEAD

	public void nextTurn() {

		// passa il turno al giocatore successivo
=======
	
	/**
	 * Method called to switch turn
	 */
	public void nextTurn() {
		
>>>>>>> bcb3213ce553f853e3a5d6a003de39e2b08ea570
		game.nextTurn();
		switchPhaseGraphic();
		switchPlayerGraphic();
		if (game.getCurrentTurn().isAI() && game.getGamePhase() == GAME_PHASE.FIRSTTURN)
			nextPhase();
<<<<<<< HEAD

		// Ogni volta che il turno passa ad un altro giocatore, il suo nome viene sottolineato


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

=======
		
		
		
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
	 * Method called to switch phase
	 */
>>>>>>> bcb3213ce553f853e3a5d6a003de39e2b08ea570
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

				if (game.getCurrentTurn().isAI())
					nextPhase();

				return;
			} else {
				game.nextPhase();
				if (!game.getCurrentTurn().isAI()) {
					phaseText.setText(game.getGamePhase().toString());
					phaseSwitch.setText(String.valueOf(game.getCurrentTurn().getBonusTanks()));
					setPhaseTextArea("\n" + game.getCurrentTurn().getName() + " turn! You received " + game.getCurrentTurn().getBonusTanks() + " bonus armies");

					switchPhaseGraphic();
					switchPlayerGraphic();
					callInfoWindows();
				}
				if (game.getCurrentTurn().isAI())
					nextPhase();
			}
			break;
		case DRAFT:
			game.nextPhase();
			phaseText.setText(game.getGamePhase().toString());
			phaseSwitch.setText(String.valueOf(game.getCurrentTurn().getBonusTanks()));
			updateTerritoriesGraphic();
			switchPhaseGraphic();
			switchPlayerGraphic();
			if (game.getCurrentTurn().isAI())
				nextPhase();
			break;
		case ATTACK:
			game.nextPhase();
			if (game.getCurrentTurn().isAI())
				nextPhase();
			phaseText.setText(game.getGamePhase().toString());
			updateTerritoriesGraphic();
			switchPhaseGraphic();
			switchPlayerGraphic();
			fortified = false;
			break;
		case FORTIFY:
			nextTurn();
			game.nextPhase();
			if (!game.getCurrentTurn().isAI()) {
<<<<<<< HEAD
				System.out.println("bella");
				phaseText.setText(game.getGamePhase().toString());				//label in basso al centro
				phaseSwitch.setText(String.valueOf(game.getCurrentTurn().getBonusTanks()));		//label con il numero di tank in basso al centro	
				setPhaseTextArea("\n" + game.getCurrentTurn().getName() + " turn! You received " + game.getCurrentTurn().getBonusTanks() + " bonus armies");	//label in basso a destra
				updateTerritoriesGraphic(); // update dei cerchiolini sui territori
				switchPhaseGraphic(); // update del colore della label in basso al centro
				switchPlayerGraphic(); // update dei colori del player per la label con il numero di carri in basso al
				// centro
				updateCardsNumber(); // update del numero di carte sulla label
				callInfoWindows(); // apre il pop up con il numero di carri armati
			}
			if (game.getCurrentTurn().isAI())
				nextPhase();
=======
			System.out.println("bella");
			phaseText.setText(game.getGamePhase().toString());				//down center label
			phaseSwitch.setText(String.valueOf(game.getCurrentTurn().getBonusTanks()));		//center down label with number of tanks	
			setPhaseTextArea("\n" + game.getCurrentTurn().getName() + " turn! You received " + game.getCurrentTurn().getBonusTanks() + " bonus armies");	//label in basso a destra
			updateTerritoriesGraphic(); // update text label of Territories
			switchPhaseGraphic(); // update color label
			switchPlayerGraphic(); // update player color for the label down center
			updateCardsNumber(); // update number of cards on the label
			callInfoWindows(); // open the infoWindow with the number of tanks
		}
		if (game.getCurrentTurn().isAI())
			nextPhase();
>>>>>>> bcb3213ce553f853e3a5d6a003de39e2b08ea570
		}


	}
<<<<<<< HEAD

=======
	
	/**
	 * Method to color the Territory selected
	 * @param svg SVGPath of the Territory to reset the color
	 * @param col boolean to color the Territory (darker color if true)
	 */
>>>>>>> bcb3213ce553f853e3a5d6a003de39e2b08ea570
	protected void setSelectedTerritoryGraphic(SVGPath svg, boolean col) {
		if(col){
			// color the selected Territory (10% darker)
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
			}
		} else {
			//reset the color of the selected Territory
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
<<<<<<< HEAD

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
=======
	
	/**
	 * Method the reset the color of every Territory
	 */
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
>>>>>>> bcb3213ce553f853e3a5d6a003de39e2b08ea570
			}
				
		}
<<<<<<< HEAD
	}

=======
		
		
	/**
	 * Method called to change the color of rectangles in phase switch box
	 */
>>>>>>> bcb3213ce553f853e3a5d6a003de39e2b08ea570
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
<<<<<<< HEAD

=======
	
	/**
	 * Method called to update the image of the current player in the phase switch box
	 */
>>>>>>> bcb3213ce553f853e3a5d6a003de39e2b08ea570
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
		Text[] userNames = {userName1, userName2, userName3, userName4, userName5, userName6};
		for(Text t : userNames) {
			t.setUnderline(false);
		}
		userNames[game.getTurnCounter()].setUnderline(true);
	}
<<<<<<< HEAD

	private void tankAddedAnimation(MouseEvent event) {
		// ANIMAZIONE
		int translateSpan = 10;
		plusAnimBox.setTranslateX(event.getSceneX() - plusAnimBox.getWidth()/2);
		plusAnimBox.setTranslateY(event.getSceneY()- plusAnimBox.getHeight()+translateSpan);
		FadeTransition ft = new FadeTransition(Duration.millis(300), plusAnimBox);
		ft.setFromValue(0);
		ft.setToValue(1);
		ft.setCycleCount(2);
		ft.setAutoReverse(true);
		ft.play();
		TranslateTransition tt = new TranslateTransition(Duration.millis(600), plusAnimBox);
		tt.setByY(-translateSpan);
		tt.play();
	}

=======
	
	/**
	 * Method called to return the rectangles of the graphic phase switch
	 * @param hb Hbox containing the graphic of phase switch
	 * @return the list of Rectangles in the graphic of phase switch
	 */
>>>>>>> bcb3213ce553f853e3a5d6a003de39e2b08ea570
	private ArrayList<Rectangle> getRectangles(HBox hb) {
		ArrayList<Rectangle> rectangles = new ArrayList<>();
		for (Node currentNode : hb.getChildren()){
			if (currentNode instanceof Rectangle){
				rectangles.add((Rectangle)currentNode);
			}
		}
		return rectangles;
	}
<<<<<<< HEAD

=======
	
	
>>>>>>> bcb3213ce553f853e3a5d6a003de39e2b08ea570
	/**
	 * @param scene is the path of the scene to load
	 * @param title is the title of the window
	 * @param cantclose boolean to decide if the window can be closed
	 * @param undecorated boolean to decide if the window has decoration
	 * @throws IOException if file is not found or can not be read
	 */
	public void windowLoader(String scene, String title, boolean cantclose, boolean undecorated) throws IOException{
		Parent sceneParent = FXMLLoader.load(getClass().getResource(scene));
		Scene mScene = new Scene(sceneParent);
		mScene.getRoot().requestFocus();
		Stage window = new Stage();
		
		if(undecorated)
			window.initStyle(StageStyle.UNDECORATED);
		
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

	/**
	 * Method called to open italian rules
	 * @param event MouseEvent Italian Rules button pressed in the right top of GameScene
	 */
	@FXML
	private void itRulesPressed(MouseEvent event) {
		rulesLeng = "it";
		try {
			GameController.getInstance().windowLoader("/risk/view/fxml/RulesWindow.fxml", "Rules", false, false);
		} catch (IOException e) {
			e.printStackTrace();
		}
		rulesLeng = null;
	}
<<<<<<< HEAD

=======
	
	/**
	 * Method called to open english rules
	 * @param event MouseEvent English Rules button pressed in the right top of GameScene
	 */
>>>>>>> bcb3213ce553f853e3a5d6a003de39e2b08ea570
	@FXML
	private void enRulesPressed(MouseEvent event) {
		rulesLeng = "en";
		try {
			GameController.getInstance().windowLoader("/risk/view/fxml/RulesWindow.fxml", "Rules", false, false);
		} catch (IOException e) {
			e.printStackTrace();
		}
		rulesLeng = null;
	}
<<<<<<< HEAD

=======
	
	/**
	 * Method called to receive the SVGPath of the Territory hovered
	 * @param event MouseEvent Territory hovered
	 */
>>>>>>> bcb3213ce553f853e3a5d6a003de39e2b08ea570
	@FXML
	private void handleSVGPathHover(MouseEvent event) {
		event.consume();
		territoryText.setText(((SVGPath)event.getSource()).getId());
	}
<<<<<<< HEAD

=======
	
	/**
	 * Method called to switch phase using the button
	 * @param event MouseEvent PhaseSwitch button pressed
	 */
>>>>>>> bcb3213ce553f853e3a5d6a003de39e2b08ea570
	@FXML
	private void handlePhaseSwitchPressed(MouseEvent event) {
		event.consume();
		if (!game.getGamePhase().equals(GAME_PHASE.FIRSTTURN) && game.getCurrentTurn().getBonusTanks() == 0) {
			nextPhase();
		}
	}
<<<<<<< HEAD

=======
	
	/**
	 * Method called to stop or play music pressing the relative button
	 * @param event MouseEvent speaker button pressed
	 * @throws FileNotFoundException if the music is not found throws an Exception
	 */
>>>>>>> bcb3213ce553f853e3a5d6a003de39e2b08ea570
	@FXML
	private void speakerButtonPressed(MouseEvent event) throws FileNotFoundException {
		event.consume();

		if(music) {
			soundController.stopMusic();
			InputStream stream = new FileInputStream("src/risk/view/images/speakerOff.png");
			Image image = new Image(stream);
			speakerImage.setImage(image);
			music = false;
		} else {
			soundController.gameMusic();
			InputStream stream = new FileInputStream("src/risk/view/images/speaker.png");
			Image image = new Image(stream);
			speakerImage.setImage(image);
			music = true;
		}

	}
<<<<<<< HEAD


	/**
	 * Switches the game turn to the next one
	 */

=======
	
>>>>>>> bcb3213ce553f853e3a5d6a003de39e2b08ea570

	/**
	 * Method called to open the PlayerInfoWindow
	 * @param event MouseEvent player on infoBar on the right pressed in GameScene
	 */
<<<<<<< HEAD




=======
>>>>>>> bcb3213ce553f853e3a5d6a003de39e2b08ea570
	@FXML
	public void playerIconPressed(MouseEvent event){
		try {
			windowLoader("/risk/view/fxml/PlayerInfoWindow.fxml", "Player info", false, false);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
<<<<<<< HEAD

=======
	
	/**
	 * Method called to open the MissionImageWindow
	 * @param event MouseEvent Mission button pressed in GameScene
	 */
>>>>>>> bcb3213ce553f853e3a5d6a003de39e2b08ea570
	@FXML
	public void missionIconPressed(MouseEvent event){
		try {
			windowLoader("/risk/view/fxml/MissionImageWindow.fxml", "Mission", false, false);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
<<<<<<< HEAD

=======
	
	/**
	 * Method called to open the CardScene
	 * @param event MouseEvent CardIcon button pressed in GameScene
	 */
>>>>>>> bcb3213ce553f853e3a5d6a003de39e2b08ea570
	@FXML
	public void cardIconPressed(MouseEvent event){
		cardSceneOpen = true;
		try {
			windowLoader("/risk/view/fxml/CardScene.fxml", "Cards", true, true);
		} catch (IOException e) {
			e.printStackTrace();
		}
		cardSceneOpen = false;
	}
<<<<<<< HEAD

=======
	
	/**
	 * Method called to open the AttackScene
	 * @param event MouseEvent attack button pressed in GameScene
	 */
>>>>>>> bcb3213ce553f853e3a5d6a003de39e2b08ea570
	@FXML
	public void attackButtonIconPressed(MouseEvent event){
		if(game.getGamePhase().equals(GAME_PHASE.ATTACK)) {
			try {
				soundController.stopMusic();
				windowLoader("/risk/view/fxml/AttackScene.fxml", "Attack", true, true);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		// after the attack check victory
		if (game.verifyMission()) {
			try {
				soundController.stopMusic();
				windowLoader("/risk/view/fxml/WinScene.fxml", "Win", true, true);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
<<<<<<< HEAD

	@FXML
	private void pauseButtonPressed(MouseEvent event) {
		FadeTransition ft = new FadeTransition(Duration.millis(800), pauseMenuPane);
		ft.setFromValue(0);
		ft.setToValue(1);
		ft.play();

		pauseMenuPane.setMouseTransparent(false);
	}

=======
	
	/**
	 * Method called to open the pause menu
	 * @param event MouseEvent pause button pressed in GameScene
	 */
	@FXML
	private void pauseButtonPressed(MouseEvent event) {
	     FadeTransition ft = new FadeTransition(Duration.millis(800), pauseMenuPane);
	     ft.setFromValue(0);
	     ft.setToValue(1);
	     ft.play();
	     
	     pauseMenuPane.setMouseTransparent(false);
	}
	
	/**
	 * Method called to resume the game from the menu
	 * @param event MouseEvent button resume pressed in menu
	 */
>>>>>>> bcb3213ce553f853e3a5d6a003de39e2b08ea570
	@FXML
	private void pauseMenuResumePressed(MouseEvent event) {
		FadeTransition ft = new FadeTransition(Duration.millis(800), pauseMenuPane);
		ft.setFromValue(1);
		ft.setToValue(0);
		ft.play();

		pauseMenuPane.setMouseTransparent(true);
	}

	/**
	 * Method called to restart the game from the pause menu
	 * @param event MouseEvent
	 */
	@FXML
	private void pauseMenuRestartPressed(MouseEvent event) {
		// DA FARE
	}
<<<<<<< HEAD

=======
	
	/**
	 * Method called to exit from the pause menu
	 * @param event MouseEvent
	 */
>>>>>>> bcb3213ce553f853e3a5d6a003de39e2b08ea570
	@FXML
	private void pauseMenuExitPressed(MouseEvent event) {
		event.consume();
		Platform.exit();
	}
<<<<<<< HEAD


=======
	
	
	/**
	 * Method to write what happens during phases in textArea (right section)
	 * @param text String
	 */
>>>>>>> bcb3213ce553f853e3a5d6a003de39e2b08ea570
	public void setPhaseTextArea(String text) {
		phasesDescriptionArea.setText(phasesDescriptionArea.getText()+text+"\n");
		phasesDescriptionArea.setScrollTop(Double.MAX_VALUE);
	}
<<<<<<< HEAD

=======
	
	/**
	 * Method to update the visualization of the number of cards in the button
	 */
>>>>>>> bcb3213ce553f853e3a5d6a003de39e2b08ea570
	protected void updateCardsNumber() {
		cardNumberText.setText(""+game.getCurrentTurn().getCards().size());
	}
	
	/**
	 * Method called to write the number of tanks on phaseSwitch button in GameScene
	 * @param text Number of tanks to place
	 */
	public void setPhaseSwitchText(String text) {
		phaseSwitch.setText(text);
	}
	
	/**
	 * @return the first Territory chosen
	 */
	public Territory getTerritory1() {
		return territory1;
	}
<<<<<<< HEAD

	public boolean getMusic() {
		return music;
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

=======
	
	/**
	 * @return boolean if the music is played
	 */
	public boolean getMusic() {
		return music;
	}
	
	/**
	 * @return the second Territory chosen
	 */
	public Territory getTerritory2() {
		return territory2;
	}
	
	/**
	 * Method called to set the first territory chosen
	 * @param t Territory
	 */
	public void setTerritory1(Territory t) {
		territory1 = t;
	}
	
	/**
	 * Method called to set the second territory chosen
	 * @param t Territory
	 */
	public void setTerritory2(Territory t) {
		territory2 = t;
	}
	
	/**
	 * @return the player that is playing
	 */
	public Player getCurrentPlayer() {
		return game.getCurrentTurn();
	}
	
	/**
	 * Method called to disable attack button in GameScene
	 * @param t boolean to disable the button
	 */
>>>>>>> bcb3213ce553f853e3a5d6a003de39e2b08ea570
	public void setAttackButtonDisable(boolean t) {
		attackButtonIcon.setDisable(t);
	}
	
	/**
	 * Method called to disable phaseSwitch button in GameScene
	 * @param t boolean to disable the button
	 */
	public void setPhaseSwitchButtonDisable(boolean t) {
		phaseSwitch.setDisable(t);
	}

	/**
	 * Method called to update Users Bar in GameScene on the right 
	 */
	public void updateUsersBar() {
		initializeUserColorsAndNames();
	}
<<<<<<< HEAD

	/*Method called to play every music in game*/
=======
	
	/**
	 * Method called to play every music in game
	 */
>>>>>>> bcb3213ce553f853e3a5d6a003de39e2b08ea570
	public void playMusic() {
		if(music) soundController.gameMusic();

	}
<<<<<<< HEAD

	/*Method called to stop every music in game*/
	public void stopMusic() {
		soundController.stopMusic();;
	}

	/* Method called to show how many tanks bonus are received in a new window*/
=======
	
	/**
	 * Method called to stop every music in game
	 */
	public void stopMusic() {
		soundController.stopMusic();;
	}
	
	/**
	 * Method called to show how many tanks bonus are received in a new window
	 **/
>>>>>>> bcb3213ce553f853e3a5d6a003de39e2b08ea570
	private void callInfoWindows() {
		try {
			GameController.getInstance().windowLoader("/risk/view/fxml/InfosWindow.fxml", "Territory conquered", true, true);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
<<<<<<< HEAD



=======
	
	
	
	/**
	 * Method called to return what is the language chosen of the rules 
	 * @return language of the rules
	 */
>>>>>>> bcb3213ce553f853e3a5d6a003de39e2b08ea570
	public String getRulesLeng() {
		return rulesLeng;
	}
	
	/** 
	 * Method to return fortified true if a user has moved during FORTIFY phase
	 * @return boolean if a user has already moved during FORTIFY phase
	 */
	public boolean isFortified() {
		return fortified;
	}

	/**
	 * Method to set fortified true if a user has moved during FORTIFY phase
	 * @param fortified boolean to set if a user has fortified
	 */
	public void setFortified(boolean fortified) {
		this.fortified = fortified;
	}
<<<<<<< HEAD

	public boolean isCardSceneOpen() {
		return cardSceneOpen;
	}

	/* Method called when exit button is pressed */
=======
	
	
	/**
	 * Method to return true if the CardScene is open
	 * @return boolean if the CardScene is open
	 */
	public boolean isCardSceneOpen() {
		return cardSceneOpen;
	}
	
	/**
	 * Method called to close the game
	 * @param event ActionEvent exit button pressed
	 */
>>>>>>> bcb3213ce553f853e3a5d6a003de39e2b08ea570
	@FXML
	private void exit(ActionEvent event) {
		event.consume();
		Platform.exit();
	}



}
