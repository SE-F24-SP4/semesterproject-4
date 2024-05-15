package com.github.sef24sp4.core.javafxbindings;

import com.github.sef24sp4.common.gamecontrol.InputAction;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;

import java.util.Map;
import java.util.Optional;

import static java.util.Map.entry;

public final class JavaFXInputMap {
	private static final Map<KeyCode, InputAction> KEY_MAP = Map.ofEntries(
			entry(KeyCode.A, InputAction.LEFT),
			entry(KeyCode.W, InputAction.UP),
			entry(KeyCode.D, InputAction.RIGHT),
			entry(KeyCode.S, InputAction.DOWN)
	);

	private static final Map<MouseButton, InputAction> MOUSE_BUTTON_MAP = Map.ofEntries(
			entry(MouseButton.PRIMARY, InputAction.SHOOT)
	);

	private JavaFXInputMap() {
	}

	public static Optional<InputAction> getFromKey(final KeyCode keyCode) {
		return Optional.ofNullable(JavaFXInputMap.KEY_MAP.get(keyCode));
	}

	public static Optional<InputAction> getFromMouseButton(final MouseButton mouseButton) {
		return Optional.ofNullable(JavaFXInputMap.MOUSE_BUTTON_MAP.get(mouseButton));
	}
}
