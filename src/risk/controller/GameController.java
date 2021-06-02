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
	private Text territoryText, yellowName, redName, greenName, blueName, pinkName, blackName;
	
	@FXML
	private VBox usersBox;
	
	@FXML
	private ImageView yellowUser, redUser, greenUser, blueUser, pinkUser, blackUser;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		/* DA ELIMINARE */
		for(Player p : PlayersList.getPlayers())
			System.out.println(p);	
		/****************/
		
		initializeUserBar();
	}
	
	/* Method that draw on the game scene the right number of colored users */
	private void initializeUserBar() {
		
		switch(PlayersList.getPlayers().size()) {
		case 3:
			for(int i = 0; i < 6; i++) {
				usersBox.getChildren().remove(usersBox.getChildren().size()-1);
			}
			break;
		case 4:
			for(int i = 0; i < 4; i++) {
				usersBox.getChildren().remove(usersBox.getChildren().size()-1);
			}
			break;
		case 5:
			for(int i = 0; i < 2; i++)
				usersBox.getChildren().remove(usersBox.getChildren().size()-1);
			break;
		default:
			break;
		}
		
		initializeUserNames();
		double windowHeight = rootPane.getPrefHeight();
		double usersBoxHeight = usersBox.getChildren().size()/2 * 59;	// children is a square 50x50 + text is widthx9 = 59
		double height = (windowHeight - usersBoxHeight)/2;
		usersBox.setLayoutY(height);
	}
	
	private void initializeUserNames() {
		try {
			yellowName.setText(PlayersList.getPlayers().get(0).getName());
			redName.setText(PlayersList.getPlayers().get(1).getName());
			greenName.setText(PlayersList.getPlayers().get(2).getName());
			blueName.setText(PlayersList.getPlayers().get(3).getName());
			pinkName.setText(PlayersList.getPlayers().get(4).getName());
			blackName.setText(PlayersList.getPlayers().get(5).getName());
		} catch (IndexOutOfBoundsException e) {
			// ...
		}	
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
