package com.github.sef24sp4.core;


import com.github.sef24sp4.core.scenes.StartMenu;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public final class Main extends Application {

	public static void main(final String[] args) {
		Application.launch(Main.class);
	}

	@Override
	public void start(final Stage primaryStage) throws Exception {
		primaryStage.setTitle("Shape War");
		final Scene scene = new Scene(new Pane(), 800, 600);
		primaryStage.setMaximized(true);
		//primaryStage.setFullScreen(true); // Cannot get to work on MacOS
		primaryStage.setScene(scene);
		primaryStage.show();
		StartMenu.load(scene);
	}

}