package risk.controller;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import risk.model.Player;

public class WinSceneController implements Initializable {

    @FXML
    private AnchorPane titleBg;

    @FXML
    private Label titleLabel, subtitleLabel, tankNumLabel, terrNumLabel ,contNumLabel;

    @FXML
    private ImageView missionImage;

    @FXML
    private Button newGamebutton, exitButton;
    
    String color;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		/*
		Player winner = passare player vincitore
		setColor(winner);
		
		titleLabel.setText(winner.getName() + " is the winner!");
		subtitleLabel.setText(winner.getName() + " has completed his mission");
		
		tankNumLabel.setText(winner.getTanks());
		terrNumLabel.setText(winner.getTerritories());
		contNumLabel.setText(winner.getContinents());
		
		// set mission's image
		int code = winner.getMission().getCodeMission();
		if(code == 7) code = 1;
		
		String path = "src/risk/view/images/missions/mission0" + code + ".png";
        File file = new File(path);
        Image image = new Image(file.toURI().toString());
        missionImage.setImage(image);
		*/
		
		// mancano da definire i comportamenti dei due pulsanti
		
	}
	
	public void setColor(Player p) {
		color = p.getColorName();
		titleBg.setStyle("-fx-background-color:" + color + ";");
		exitButton.setStyle(exitButton.getStyle() + "-fx-base:" + color + ";");
		newGamebutton.setStyle(exitButton.getStyle() + "-fx-base:" + color + ";");
		
		if(color.toLowerCase().equals("pink") || (color.toLowerCase().equals("yellow"))) {
			titleLabel.setStyle(titleLabel.getStyle() + "-fx-text-fill: black;");
			exitButton.setStyle(exitButton.getStyle() + "-fx-text-fill: black;");
			newGamebutton.setStyle(exitButton.getStyle() + "-fx-text-fill: black;");
		}
	}

}
