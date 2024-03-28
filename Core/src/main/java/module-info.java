module Core {
	requires javafx.graphics;
	requires javafx.controls;
	requires javafx.fxml;
	opens com.github.sef24sp4.core to javafx.graphics, javafx.fxml;
}