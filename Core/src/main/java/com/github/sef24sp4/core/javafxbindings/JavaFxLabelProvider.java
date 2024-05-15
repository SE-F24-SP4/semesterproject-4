package com.github.sef24sp4.core.javafxbindings;

import com.github.sef24sp4.common.graphics.label.Label;
import com.github.sef24sp4.common.graphics.label.LabelProvider;
import com.github.sef24sp4.common.vector.IVector;
import javafx.scene.layout.Pane;

public class JavaFxLabelProvider implements LabelProvider {
	private static Pane root;

	private static Pane getRoot() {
		if (root == null) throw new IllegalStateException("LabelProvider has not been initialized");
		return root;
	}

	public static void setRoot(final Pane root) {
		JavaFxLabelProvider.root = root;
	}

	private static javafx.scene.control.Label createFxLabel(final String text) {
		final javafx.scene.control.Label javaFxLabel = new javafx.scene.control.Label(text);
		getRoot().getChildren().add(javaFxLabel);
		return javaFxLabel;
	}

	@Override
	public boolean deregisterLabel(final Label label) {
		if (label instanceof final JavaFxLabel l) return getRoot().getChildren().remove(l.getFxLabel());
		return false;
	}

	@Override
	public Label create(final IVector coordinates, final String text) {
		return new JavaFxLabel(coordinates, createFxLabel(text));
	}

	@Override
	public Label create(final IVector coordinates) {
		return this.create(coordinates, "");
	}
}
