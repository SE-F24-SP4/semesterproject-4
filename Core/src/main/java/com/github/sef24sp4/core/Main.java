package com.github.sef24sp4.core;


import javafx.application.Application;
import javafx.stage.Stage;

public final class Main extends Application {

	public static void main(final String[] args) {
		Application.launch(Main.class);
	}

	@Override
	public void start(final Stage window) throws Exception {
		window.setTitle("Shape War");
		window.show();
	}

}