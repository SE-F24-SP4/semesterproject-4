package com.github.sef24sp4.core.scenes;

import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public final class GameScene {
	public static void load(Stage stage) {
		Button button = new Button("Back to start");
		button.addEventHandler(ActionEvent.ANY, event -> {
			try {
				StartMenu.load(stage);
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		});

		Pane pane = new Pane(button);
		Scene scene = new Scene(pane, stage.getScene().getWidth(), stage.getScene().getHeight());
		stage.setScene(scene);
		stage.show();
	}

	private GameScene() {
	}
}
