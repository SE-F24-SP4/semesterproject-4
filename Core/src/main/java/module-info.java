module Core {
	requires javafx.graphics;
	requires javafx.controls;
	requires javafx.fxml;
	requires Common;
	opens com.github.sef24sp4.core to javafx.fxml, javafx.graphics;
	opens com.github.sef24sp4.core.scenes to javafx.fxml, javafx.graphics;
}