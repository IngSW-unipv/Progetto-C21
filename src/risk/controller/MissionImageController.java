package risk.controller;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class MissionImageController implements Initializable {
	
	@FXML
	private ImageView imageView;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {		
		int code = GameController.game.getCurrentTurn().getMission().getCodeMission();
		if(code == 7) code = 1;
		
		String path = "src/risk/view/images/missions/mission0" + code + ".png";
        File file = new File(path);
        Image image = new Image(file.toURI().toString());
        imageView.setImage(image);

	}

}
