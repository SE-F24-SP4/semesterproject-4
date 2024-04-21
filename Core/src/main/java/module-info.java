import com.github.sef24sp4.common.graphics.label.LabelProvider;
import com.github.sef24sp4.core.javafxbindings.JavaFxLabelProvider;

module Core {
	uses com.github.sef24sp4.common.services.IEntityProcessingService;
	uses com.github.sef24sp4.common.services.IGamePluginService;
	requires javafx.graphics;
	requires javafx.controls;
	requires javafx.fxml;
	requires Common;
	requires CommonCollisionSystem;
	opens com.github.sef24sp4.core to javafx.fxml, javafx.graphics;
	opens com.github.sef24sp4.core.scenes to javafx.fxml, javafx.graphics;
	provides LabelProvider with JavaFxLabelProvider;
}