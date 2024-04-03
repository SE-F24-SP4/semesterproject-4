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
	public static void load(Stage stage) throws IOException {
		FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(StartMenu.class.getResource("start-menu.fxml")));
		Parent root = loader.load();

		StartMenu startMenu = loader.getController();
		startMenu.setStage(stage);

		stage.setScene(new Scene(root));
		stage.show();
	}

	private Stage stage;

	@FXML
	private Button startButton;


	public Stage getStage() {
		return this.stage;
	}

	public void setStage(Stage stage) {
		this.stage = stage;
	}

	public void startGame(ActionEvent actionEvent) {
		GameScene.load(this.getStage());
	}
}
