package com.github.sef24sp4.core.scenes;

import com.github.sef24sp4.common.collisionsystem.CollisionSystemProvider;
import com.github.sef24sp4.common.collisionsystem.CollisionSystemSPI;
import com.github.sef24sp4.core.game.GameBuilder;
import com.github.sef24sp4.core.game.GameSettings;
import com.github.sef24sp4.core.game.GameSettingsProvider;
import com.github.sef24sp4.core.interfaces.IGameProcessor;
import com.github.sef24sp4.core.javafxbindings.AnimationTimerTickExecutor;
import com.github.sef24sp4.core.javafxbindings.EntityToJavaFxMapper;
import com.github.sef24sp4.core.javafxbindings.JavaFxInputInitializer;
import com.github.sef24sp4.core.javafxbindings.JavaFxLabelProvider;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.io.UncheckedIOException;

public final class GameScene {
	private static Scene scene;
	private static IGameProcessor gameProcessor;

	private GameScene() {
	}

	public static void load(final Scene rootScene) {
		GameScene.scene = rootScene;

		final Button button = new Button("Back to start");
		final Pane pane = new Pane(button);
		GameScene.scene.setRoot(pane);

		JavaFxLabelProvider.setRoot(pane);

		gameProcessor = GameScene.getGameProcessor(pane);

		button.addEventHandler(ActionEvent.ANY, event -> {
			endGame();
		});

		gameProcessor.begin();
	}

	private static boolean endGame() {
		try {
			gameProcessor.end();
			StartMenu.load(scene);
		} catch (IOException e) {
			throw new UncheckedIOException(e);
		}
		return true;
	}

	public static IGameProcessor getGameProcessor(final Pane pane) {
		final GameSettings gameSettings = new GameSettings(JavaFxInputInitializer.init(pane));
		gameSettings.setDisplayHeight(Math.toIntExact(Math.round(pane.getHeight())));
		gameSettings.setDisplayWidth(Math.toIntExact(Math.round(pane.getWidth())));

		GameSettingsProvider.setSettings(gameSettings);

		gameSettings.setEndGameCallback(GameScene::endGame);

		pane.widthProperty().addListener(((observable, oldValue, newValue) -> gameSettings.setDisplayWidth(newValue.intValue())));
		pane.heightProperty().addListener(((observable, oldValue, newValue) -> gameSettings.setDisplayHeight(newValue.intValue())));

		final CollisionSystemSPI collisionSystemSPI = CollisionSystemProvider.load().create(gameSettings);

		return new GameBuilder()
				.setGameTickExecutor(new AnimationTimerTickExecutor())
				.setEntityEntityToGraphicsMapper(new EntityToJavaFxMapper(pane.getChildren()))
				.setGameSettings(gameSettings)
				.setCollisionSystemSPI(collisionSystemSPI)
				.build();
	}
}
