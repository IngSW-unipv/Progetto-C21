package risk.controller;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import risk.main.Main;
import risk.model.Player;

public class WinSceneController implements Initializable {
	
    @FXML
    private AnchorPane rootPane, titleBg;
    
    @FXML
    private Label titleLabel, tankNumLabel, terrNumLabel ,contNumLabel;

    @FXML
    private ImageView missionImage;

    @FXML
    private Button newGameButton, exitButton;
    
    String color;
    private SoundController soundcontroller;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		// prendo il player vincitore e setto i colori e la label della barra superiore
		Player winner = GameController.game.getCurrentTurn();
		setColor(winner);
		titleLabel.setText(winner.getName() + " is the winner!");
		soundcontroller = new SoundController();
		soundcontroller.winningSound();
		// setto i valori dei carri/territori/continenti
		tankNumLabel.setText(winner.getTanks() + "");
		terrNumLabel.setText(winner.getTerritories() + "");
		contNumLabel.setText(winner.getContinents() + "");
		
		// setto l'immagine della missione del vincitore
		int code = winner.getMission().getCodeMission();
		if(code == 7) code = 1;
		
		String path = "src/risk/view/images/missions/mission0" + code + ".png";
        File file = new File(path);
        Image image = new Image(file.toURI().toString());
        missionImage.setImage(image);

		
		
     // setto il comportamento dei pulsanti
        newGameButton.setOnAction((event) -> {
        	GameController.getInstance().stopMusic();
        	soundcontroller.stopMusic();
        	Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
    		window.close();
        }); 
        exitButton.setOnAction((event) -> {
        	event.consume();
        	Platform.exit();
        	System.exit(0);
        });

		// hide window on H hold pressed
		rootPane.setOnKeyPressed((event) -> {
			if (event.getCode() == KeyCode.H) {
				rootPane.getScene().getWindow().setOpacity(0);
			}
		});
		rootPane.setOnKeyReleased((event) -> {
			if (event.getCode() == KeyCode.H) {
				rootPane.getScene().getWindow().setOpacity(1);
			}
		});

	}

	public void setColor(Player p) {
		color = p.getColorName();
		titleBg.setStyle("-fx-background-color:" + color + ";");
		exitButton.setStyle(exitButton.getStyle() + "-fx-base:" + color + ";");
		newGameButton.setStyle(exitButton.getStyle() + "-fx-base:" + color + ";");
		
		if(color.toLowerCase().equals("pink") || (color.toLowerCase().equals("yellow"))) {
			titleLabel.setStyle(titleLabel.getStyle() + "-fx-text-fill: black;");
			exitButton.setStyle(exitButton.getStyle() + "-fx-text-fill: black;");
			newGameButton.setStyle(exitButton.getStyle() + "-fx-text-fill: black;");
		}
	}

}
