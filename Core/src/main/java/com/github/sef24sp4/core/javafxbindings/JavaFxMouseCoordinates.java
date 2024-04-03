package com.github.sef24sp4.core.javafxbindings;

import com.github.sef24sp4.common.gamecontrol.IMouseCoordinates;
import javafx.event.EventTarget;
import javafx.scene.input.MouseEvent;

/**
 * Implements the {@link IMouseCoordinates} for JavaFX.
 */
public class JavaFxMouseCoordinates implements IMouseCoordinates {
	private double x;
	private double y;

	JavaFxMouseCoordinates(EventTarget target) {
		target.addEventHandler(MouseEvent.ANY, event -> {
			this.x = event.getX();
			this.y = event.getY();
		});
	}

	@Override
	public double getX() {
		return this.x;
	}

	@Override
	public double getY() {
		return this.y;
	}
}
