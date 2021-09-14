package risk.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.web.WebView;

public class RulesWindowController implements Initializable {
	
	@FXML
	private WebView webView;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		webView.getEngine().load("https://github.com/IngSW-unipv/Progetto-C21/blob/main/rules/rules_it.pdf");
		//webView.getEngine().load("https://risiko.it/wp-content/uploads/2017/10/Regolamento-Risiko.pdf");
	}

}
