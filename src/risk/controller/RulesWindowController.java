package risk.controller;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class RulesWindowController implements Initializable {
	
	@FXML
	private ImageView imageView1, imageView2;
	
	@FXML
	private Button previousButton, nextButton;
	
	private int page;
	private String leng;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		page = 1;
		leng = MenuController.rulesLeng;
		setImageView(page);
		
		previousButton.setOnAction((event) -> {
			if(page > 1) {
				page -= 1;
				setImageView(page);
			}
		});
		
		nextButton.setOnAction((event) -> {
			if(page < 15) {
				page += 1;
				setImageView(page);
			}
		});

		
	}
	
	private void setImageView(int page) {
		String path1 = "src/risk/view/images/rules/" + leng + "/" + page + ".jpg";
        File file1 = new File(path1);
        Image image1 = new Image(file1.toURI().toString());
        imageView1.setImage(image1);
		String path2 = "src/risk/view/images/rules/" + leng + "/" + (page+1) + ".jpg";
        File file2 = new File(path2);
        Image image2 = new Image(file2.toURI().toString());
        imageView2.setImage(image2);
	}

}
