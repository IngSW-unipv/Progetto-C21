package risk.controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.SVGPath;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import risk.model.Player;
import risk.model.PlayersList;

public class GameController implements Initializable {
	
	@FXML
	private Pane rootPane;
	
	@FXML
	private Text territoryText, userName1, userName2, userName3, userName4, userName5, userName6;
	
	@FXML
	private VBox usersBox;
	
	@FXML
	private ImageView userImage1, userImage2, userImage3, userImage4, userImage5, userImage6;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		initializeUserBar();
	}
	
	/* Method that draw on the game scene the right number of colored users */
	private void initializeUserBar() {
		
		// removing useless imageview and text
		for(int i = PlayersList.getPlayers().size()*2; i < 12; i++) {
			usersBox.getChildren().remove(usersBox.getChildren().size()-1);
		}

		initializeUserNames();
		
		double windowHeight = rootPane.getPrefHeight();
		double usersBoxHeight = usersBox.getChildren().size()/2 * 59;	// children is a square 50x50 + text is widthx9 = 59
		double height = (windowHeight - usersBoxHeight)/2;
		usersBox.setLayoutY(height);
	}
	
	private void initializeUserNames() {
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
	
	/**
	 * Method that allows to load a scene in a new window
	 * @param scene is the path of the scene to load
	 * @param title is the title of the window
	 * @param cantclose specifies if the window can be closed until an event occurs (if true new window can't be closed)
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

}
