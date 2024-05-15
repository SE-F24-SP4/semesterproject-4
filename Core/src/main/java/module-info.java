import com.github.sef24sp4.common.graphics.label.LabelProvider;
import com.github.sef24sp4.common.interfaces.IGameSettings;
import com.github.sef24sp4.core.game.GameSettingsProvider;
import com.github.sef24sp4.core.javafxbindings.JavaFxLabelProvider;
import com.github.sef24sp4.common.collisionsystem.CollisionSystemProvider;

module Core {
	uses com.github.sef24sp4.common.services.IEntityProcessingService;
	uses com.github.sef24sp4.common.services.IGamePluginService;
	uses CollisionSystemProvider;
	requires javafx.graphics;
	requires javafx.controls;
	requires javafx.fxml;
	requires Common;
	requires CommonCollisionSystem;
	opens com.github.sef24sp4.core to javafx.fxml, javafx.graphics;
	opens com.github.sef24sp4.core.scenes to javafx.fxml, javafx.graphics;
	provides LabelProvider with JavaFxLabelProvider;
	provides IGameSettings with GameSettingsProvider;
}
