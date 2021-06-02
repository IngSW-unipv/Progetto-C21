package risk.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.SVGPath;
import javafx.scene.text.Text;
import risk.model.Player;
import risk.model.PlayersList;

public class GameController implements Initializable {
	
	@FXML
	private Pane rootPane;
	
	@FXML
	private Text territoryText;
	
	@FXML
	private VBox usersBox;
	
	@FXML
	private ImageView yellowUser, redUser, greenUser, blueUser, pinkUser, blackUser;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		for(Player p : PlayersList.getPlayers())
			System.out.println(p);	
		
		initializeUserBar();
	}
	
	/* Method that draw on the game scene the right number of colored users */
	private void initializeUserBar() {
		
		switch(PlayersList.getPlayers().size()) {
		case 3:
			for(int i = 0; i < 3; i++)
				usersBox.getChildren().remove(usersBox.getChildren().size()-1);
			break;
		case 4:
			for(int i = 0; i < 2; i++)
				usersBox.getChildren().remove(usersBox.getChildren().size()-1);
			break;
		case 5:
			usersBox.getChildren().remove(usersBox.getChildren().size()-1);
			break;
		default:
			break;
		}
		
		double windowHeight = rootPane.getPrefHeight();
		double usersBoxHeight = usersBox.getChildren().size() * 50;	// children is a square 50x50
		double height = (windowHeight - usersBoxHeight)/2;
		usersBox.setLayoutY(height);
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
