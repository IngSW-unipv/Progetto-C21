module Risk {
	requires javafx.controls;
	requires javafx.fxml;
	requires transitive javafx.graphics;
	
	opens risk.main to javafx.graphics, javafx.fxml;
	opens risk.controller to javafx.graphics, javafx.fxml;
}
