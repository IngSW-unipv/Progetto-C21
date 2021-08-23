package risk.main;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

//  ksndi sdfdddd
//succhiamel 
public class Main extends Application {
	
	@Override
	public void start(Stage primaryStage) {
		try {
			AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("/risk/view/fxml/MenuScene.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("/risk/view/css/MenuStyleSheet.css").toExternalForm());
			primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/risk/view/images/risk-icon.png")));
			primaryStage.setResizable(false);
			primaryStage.setTitle("RISK MENU");
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
