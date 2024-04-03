package com.github.sef24sp4.core.javafxbindings;

import com.github.sef24sp4.common.gamecontrol.InputAction;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;

import java.util.Map;
import java.util.Optional;

public final class JavaFXInputMap {
	private static final Map<KeyCode, InputAction> KEY_MAP = Map.ofEntries(
			Map.entry(KeyCode.A, InputAction.LEFT),
			Map.entry(KeyCode.W, InputAction.UP),
			Map.entry(KeyCode.D, InputAction.RIGHT),
			Map.entry(KeyCode.S, InputAction.DOWN)
	);

	private static final Map<MouseButton, InputAction> MOUSE_BUTTON_MAP = Map.ofEntries(
			Map.entry(MouseButton.PRIMARY, InputAction.SHOOT)
	);

	private JavaFXInputMap() {
	}

	public static Optional<InputAction> getFromKey(KeyCode keyCode) {
		return Optional.ofNullable(JavaFXInputMap.KEY_MAP.get(keyCode));
	}

	public static Optional<InputAction> getFromMouseButton(MouseButton mouseButton) {
		return Optional.ofNullable(JavaFXInputMap.MOUSE_BUTTON_MAP.get(mouseButton));
	}
}
