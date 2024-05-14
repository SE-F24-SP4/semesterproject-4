package com.github.sef24sp4.core.scenes;

import com.github.sef24sp4.core.game.GameBuilder;
import com.github.sef24sp4.core.game.GameSettings;
import com.github.sef24sp4.core.interfaces.IGameProcessor;
import com.github.sef24sp4.core.javafxbindings.AnimationTimerTickExecutor;
import com.github.sef24sp4.core.javafxbindings.EntityToJavaFxMapper;
import com.github.sef24sp4.core.javafxbindings.JavaFxInputInitializer;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.UncheckedIOException;

public final class GameScene {
	private GameScene() {
	}

	public static void load(final Stage stage) {
		final Button button = new Button("Back to start");
		final Pane pane = new Pane(button);
		final Scene scene = new Scene(pane, stage.getScene().getWidth(), stage.getScene().getHeight());
		stage.setScene(scene);

		final IGameProcessor gameProcessor = GameScene.getGameProcessor(pane);

		button.addEventHandler(ActionEvent.ANY, event -> {
			try {
				gameProcessor.end();
				StartMenu.load(stage);
			} catch (IOException e) {
				throw new UncheckedIOException(e);
			}
		});

		gameProcessor.begin();
		stage.show();
	}

	public static IGameProcessor getGameProcessor(final Pane pane) {
		final GameSettings gameSettings = new GameSettings(JavaFxInputInitializer.init(pane));
		gameSettings.setDisplayHeight(Math.toIntExact(Math.round(pane.getHeight())));
		gameSettings.setDisplayWidth(Math.toIntExact(Math.round(pane.getWidth())));

		pane.widthProperty().addListener(((observable, oldValue, newValue) -> gameSettings.setDisplayWidth(newValue.intValue())));
		pane.heightProperty().addListener(((observable, oldValue, newValue) -> gameSettings.setDisplayHeight(newValue.intValue())));

		return new GameBuilder()
				.setGameTickExecutor(new AnimationTimerTickExecutor())
				.setEntityEntityToGraphicsMapper(new EntityToJavaFxMapper(pane.getChildren()))
				.setGameSettings(gameSettings)
				.build();
	}
}
