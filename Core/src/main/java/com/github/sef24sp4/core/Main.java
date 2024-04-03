package com.github.sef24sp4.core;


import com.github.sef24sp4.core.scenes.StartMenu;
import javafx.application.Application;
import javafx.stage.Stage;

public final class Main extends Application {

	public static void main(final String[] args) {
		Application.launch(Main.class);
	}

	@Override
	public void start(final Stage primaryStage) throws Exception {
		primaryStage.setTitle("Shape War");
		StartMenu.load(primaryStage);
	}

}