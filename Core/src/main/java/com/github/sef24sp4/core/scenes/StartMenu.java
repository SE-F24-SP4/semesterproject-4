package com.github.sef24sp4.core.scenes;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public final class StartMenu {
	private Stage stage;
	@FXML
	private Button startButton;

	public static void load(final Stage stage) throws IOException {
		final FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(StartMenu.class.getResource("start-menu.fxml")));
		final Parent root = loader.load();

		final StartMenu startMenu = loader.getController();
		startMenu.setStage(stage);

		stage.setScene(new Scene(root));
		stage.show();
	}

	public Stage getStage() {
		return this.stage;
	}

	public void setStage(final Stage stage) {
		this.stage = stage;
	}

	public void startGame(final ActionEvent actionEvent) {
		GameScene.load(this.getStage());
	}
}
