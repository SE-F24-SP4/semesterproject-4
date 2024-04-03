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

	public GameInput(IMouseCoordinates mouseCoordinates) {
		this.mouseCoordinates = mouseCoordinates;
	}

	public void setKeyState(InputAction key, Boolean state) {
		this.keyState.put(key, state);
		this.currentTickKeyState.put(key, state);
	}

	public void clearCurrentTickKeys() {
		this.currentTickKeyState.clear();
	}

	@Override
	public boolean isDown(InputAction key) {
		return this.keyState.getOrDefault(key, false);
	}

	@Override
	public boolean isPressed(InputAction key) {
		return this.currentTickKeyState.getOrDefault(key, false);
	}

	@Override
	public IMouseCoordinates getMouseCoordinates() {
		return this.mouseCoordinates;
	}
}
