package risk.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.ResourceBundle;

import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;
import javafx.util.Duration;
import risk.model.Player;
import risk.model.PlayersList;
import risk.model.util.Color;

public class MenuController implements Initializable {
	
	/* TextFields for player's names */
	@FXML
	private TextField p1name;
	@FXML
	private TextField p2name;
	@FXML
	private TextField p3name;
	@FXML
	private TextField p4name;
	@FXML
	private TextField p5name;	
	@FXML
	private TextField p6name;	
	
	/* CheckBoxes for AI players selection */
	@FXML
	private CheckBox p2check;
	@FXML
	private CheckBox p3check;
	@FXML
	private CheckBox p4check;
	@FXML
	private CheckBox p5check;
	@FXML
	private CheckBox p6check;
	
	/* Exit button to close the app */
	@FXML
	private Button exitButton, backButton;
	
	/* Play button to start the game */
	@FXML
	private Button playButton, prePlayButton, preRulesButton;
	
	@FXML
	private AnchorPane anchorPane, preAnchorPane;
	
	
	/* Player's name list */
	private ArrayList<String> players;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// initializing players arraylist
		players = new ArrayList<>(Arrays.asList("", "", "", "", "", ""));
		// setting the play button disabled until user adds 3 players
		playButton.setDisable(true);
		// setting textfields 2-6 disabled
		p2name.setDisable(true);
		p3name.setDisable(true);
		p4name.setDisable(true);
		p5name.setDisable(true);
		p6name.setDisable(true);
		// setting checkboxes 2-6 disabled
		p2check.setDisable(true);
		p3check.setDisable(true);
		p4check.setDisable(true);
		p5check.setDisable(true);
		p6check.setDisable(true);

		/* LISTENER for every TextField (1-6) */
		p1name.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValude, String newValue) {
				if(!p1name.getText().isBlank()) {
					p2name.setDisable(false);
					p2check.setDisable(false);
					/* ADDING PLAYER'S NAME */
					players.set(0, p1name.getText());
				}
				else {
					p2name.setText("");
					p2name.setDisable(true);
					p2check.setSelected(false);
					p2check.setDisable(true);
					/* REMOVING PLAYER'S NAME */
					players.set(0, "");
				}
			}
		});
		p2name.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValude, String newValue) {
				if(!p2name.getText().isBlank()) {
					p3name.setDisable(false);
					p3check.setDisable(false);
					/* ADDING PLAYER'S NAME */
					players.set(1, p2name.getText());
				}
				else {
					p3name.setText("");
					p3name.setDisable(true);
					p3check.setSelected(false);
					p3check.setDisable(true);
					/* REMOVING PLAYER'S NAME */
					players.set(1, "");
				}
			}
		});
		p3name.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValude, String newValue) {
				if(!p3name.getText().isBlank()) {
					p4name.setDisable(false);
					p4check.setDisable(false);
					/* ENABLING PLAY BUTTON */
					playButton.setDisable(false);
					/* ADDING PLAYER'S NAME */
					players.set(2, p3name.getText());
				}
				else {
					p4name.setText("");
					p4name.setDisable(true);
					p4check.setSelected(false);
					p4check.setDisable(true);
					/* DISABLING PLAY BUTTON */
					playButton.setDisable(true);
					/* REMOVING PLAYER'S NAME */
					players.set(2, "");
				}
			}
		});
		p4name.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValude, String newValue) {
				if(!p4name.getText().isBlank()) {
					p5name.setDisable(false);
					p5check.setDisable(false);
					/* ADDING PLAYER'S NAME */
					players.set(3, p4name.getText());
				}
				else {
					p5name.setText("");
					p5name.setDisable(true);
					p5check.setSelected(false);
					p5check.setDisable(true);
					/* REMOVING PLAYER'S NAME */
					players.set(3, "");
				}
			}
		});
		p5name.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValude, String newValue) {
				if(!p5name.getText().isBlank()) {
					p6name.setDisable(false);
					p6check.setDisable(false);
					/* ADDING PLAYER'S NAME */
					players.set(4, p5name.getText());
				}
				else {
					p6name.setText("");
					p6name.setDisable(true);
					p6check.setSelected(false);
					p6check.setDisable(true);
					/* REMOVING PLAYER'S NAME */
					players.set(4, "");
				}
			}
		});
		p6name.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValude, String newValue) {
				if(!p6name.getText().isBlank()) {
					/* ADDING PLAYER'S NAME */
					players.set(5, p6name.getText());
				}
				else {
					/* REMOVING PLAYER'S NAME */
					players.set(5, "");
				}
			}
		});

		/* LISTENER for every CheckBox (2-6) */
		p2check.selectedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
		    public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
		        if(p2check.isSelected() && p2name.getText().isBlank())
		        	p2name.setText("player2");
		    }
		});
		p3check.selectedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
		    public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
		        if(p3check.isSelected() && p3name.getText().isBlank())
		        	p3name.setText("player3");
		    }
		});
		p4check.selectedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
		    public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
		        if(p4check.isSelected() && p4name.getText().isBlank())
		        	p4name.setText("player4");
		    }
		});
		p5check.selectedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
		    public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
		        if(p5check.isSelected() && p5name.getText().isBlank())
		        	p5name.setText("player5");
		    }
		});
		p6check.selectedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
		    public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
		        if(p6check.isSelected() && p6name.getText().isBlank())
		        	p6name.setText("player6");
		    }
		});

	}
	
	/* Method called when exit button is pressed */
	@FXML
	private void exit(final ActionEvent event) {
		event.consume();
		Platform.exit();
	}
	
	/* Method called when play button is pressed */
	@FXML
	private void play(final ActionEvent event) {
		event.consume();
		setPlayersList();

		try {
			Pane root = (Pane)FXMLLoader.load(getClass().getResource("/risk/view/fxml/GameBoardScene.fxml"));
			Scene scene = new Scene(root);
			Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
			window.setScene(scene);
			window.setTitle("RISK!");
			window.show();
			window.centerOnScreen();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/* Method called when pre-play button is pressed */
	@FXML
	private void prePlay(final ActionEvent event) {
		event.consume();
		
		Timeline timeline = new Timeline();
		KeyValue kv = new KeyValue(anchorPane.translateXProperty(), -450, Interpolator.EASE_IN);
		KeyFrame kf = new KeyFrame(Duration.seconds(1), kv);
		timeline.getKeyFrames().add(kf);
		timeline.play();
	}
	
	/* Method called when back button is pressed */
	@FXML
	private void back(final ActionEvent event) {
		event.consume();
		
		Timeline timeline = new Timeline();
		KeyValue kv = new KeyValue(anchorPane.translateXProperty(), 450, Interpolator.EASE_IN);
		KeyFrame kf = new KeyFrame(Duration.seconds(1), kv);
		timeline.getKeyFrames().add(kf);
		timeline.play();
	}
	
	/* Method called when rules button is pressed */
	@FXML
	private void rules(final ActionEvent event) {
		try {
			Parent sceneParent = FXMLLoader.load(getClass().getResource("/risk/view/fxml/RulesWindow.fxml"));
			Scene mScene = new Scene(sceneParent);
			Stage window = new Stage();
			//window.setResizable(false);
			window.setTitle("Rules");
			window.setScene(mScene);
			window.initModality(Modality.APPLICATION_MODAL);
			window.showAndWait();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/* 
	 * Method used to set the static ArrayList of Players
	 * Color assignment is random
	 * Player list is shuffled
	 */
	private void setPlayersList() {
		ArrayList<Player> list = new ArrayList<>();
		ArrayList<Color> colorList = new ArrayList<>( Arrays.asList(Color.YELLOW, Color.RED, Color.GREEN, Color.BLUE, Color.PINK, Color.BLACK));
		Collections.shuffle(colorList);
		
		if(!p1name.getText().isBlank())
			list.add(new Player(p1name.getText(), colorList.get(0), false));
		if(!p2name.getText().isBlank())
			list.add(new Player(p2name.getText(), colorList.get(1), p2check.isSelected()));
		if(!p3name.getText().isBlank())
			list.add(new Player(p3name.getText(), colorList.get(2), p3check.isSelected()));
		if(!p4name.getText().isBlank())
			list.add(new Player(p4name.getText(), colorList.get(3), p4check.isSelected()));
		if(!p5name.getText().isBlank())
			list.add(new Player(p5name.getText(), colorList.get(4), p5check.isSelected()));
		if(!p6name.getText().isBlank())
			list.add(new Player(p6name.getText(), colorList.get(5), p6check.isSelected()));

		Collections.shuffle(list);
		PlayersList.setPlayers(list);
		
		if(PlayersList.getPlayers().size() < 3) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error Dialog");
			alert.setContentText("Ooops, there was an error! Number of players less than 3...");
			alert.showAndWait();
		}	
	}

}