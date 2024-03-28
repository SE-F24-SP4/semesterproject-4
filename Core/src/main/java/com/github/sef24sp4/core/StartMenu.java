package com.github.sef24sp4.core;

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

	@FXML
	private Button startButton;

	public static void load(Stage window) throws IOException {
		Parent root = FXMLLoader.load(Objects.requireNonNull(StartMenu.class.getResource("start-menu.fxml")));

		window.setScene(new Scene(root));
		window.show();
	}

	public void startGame(ActionEvent actionEvent) {
		System.out.println("Start game");
	}
}
