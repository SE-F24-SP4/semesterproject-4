package com.github.sef24sp4.core.scenes;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import java.io.IOException;
import java.util.Objects;

public final class StartMenu {
	private Scene scene;
	@FXML
	private Button startButton;

	public static void load(final Scene scene) throws IOException {
		final FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(StartMenu.class.getResource("start-menu.fxml")));
		final Parent root = loader.load();

		final StartMenu startMenu = loader.getController();
		startMenu.setScene(scene);


		scene.setRoot(root);
	}

	public Scene getScene() {
		return this.scene;
	}

	private void setScene(final Scene scene) {
		this.scene = scene;
	}

	public void startGame(final ActionEvent actionEvent) {
		GameScene.load(this.getScene());
	}
}
