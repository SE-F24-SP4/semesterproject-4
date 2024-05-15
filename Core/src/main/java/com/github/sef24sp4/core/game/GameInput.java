package com.github.sef24sp4.core.game;

import com.github.sef24sp4.common.gamecontrol.IGameInput;
import com.github.sef24sp4.common.gamecontrol.IMouseCoordinates;
import com.github.sef24sp4.common.gamecontrol.InputAction;

import java.util.HashMap;
import java.util.Map;

public class GameInput implements IGameInput {
	private final Map<InputAction, Boolean> keyState = new HashMap<>(InputAction.values().length);
	private final Map<InputAction, Boolean> currentTickKeyState = new HashMap<>(InputAction.values().length);

	private final IMouseCoordinates mouseCoordinates;

	public GameInput(final IMouseCoordinates mouseCoordinates) {
		this.mouseCoordinates = mouseCoordinates;
	}

	/**
	 * Helper method to check and array of keys if they are pressed.
	 *
	 * @param keyMap The specific key map to check.
	 * @param keys   The keys to check.
	 * @return {@code true} if all keys are pressed. {@code false} otherwise.
	 */
	private static boolean checkActions(final Map<InputAction, Boolean> keyMap, final InputAction[] keys) {
		for (final InputAction action : keys) {
			// If any action key is not set then return false.
			if (!keyMap.getOrDefault(action, false)) return false;
		}
		// return true when all keys are pressed.
		return true;
	}

	public void setKeyState(final InputAction key, final boolean state) {
		this.keyState.put(key, state);
		this.currentTickKeyState.put(key, state);
	}

	public void clearCurrentTickKeys() {
		this.currentTickKeyState.clear();
	}

	@Override
	public boolean isDown(final InputAction... keys) {
		return GameInput.checkActions(this.keyState, keys);
	}

	@Override
	public boolean isPressed(final InputAction... keys) {
		return GameInput.checkActions(this.currentTickKeyState, keys);
	}

	@Override
	public IMouseCoordinates getMouseCoordinates() {
		return this.mouseCoordinates;
	}
}
