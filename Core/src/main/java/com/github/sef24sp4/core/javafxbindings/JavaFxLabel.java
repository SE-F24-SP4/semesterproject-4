package com.github.sef24sp4.core.javafxbindings;

import com.github.sef24sp4.common.graphics.label.Label;
import com.github.sef24sp4.common.vector.BasicVector;
import com.github.sef24sp4.common.vector.IVector;
import javafx.scene.text.Font;

public class JavaFxLabel implements Label {
	private final javafx.scene.control.Label fxLabel;

	JavaFxLabel(final IVector coordinates, final javafx.scene.control.Label fxLabel) {
		this.fxLabel = fxLabel;
		this.setCoordinates(coordinates);
	}

	public javafx.scene.control.Label getFxLabel() {
		return this.fxLabel;
	}

	@Override
	public String getText() {
		return this.fxLabel.getText();
	}

	@Override
	public void setText(final String text) {
		this.fxLabel.setText(text);
	}

	@Override
	public double getFontSize() {
		return this.fxLabel.getFont().getSize();
	}

	@Override
	public void setFontSize(final double size) {
		this.fxLabel.fontProperty().set(new Font(size));
	}

	@Override
	public IVector getCoordinates() {
		return new BasicVector(this.fxLabel.getTranslateX(), this.fxLabel.getTranslateY());
	}

	@Override
	public void setCoordinates(final IVector coordinates) {
		this.fxLabel.setTranslateX(coordinates.getX());
		this.fxLabel.setTranslateY(coordinates.getY());
	}
}
