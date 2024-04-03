package com.github.sef24sp4.core.game;

import com.github.sef24sp4.common.interfaces.IGameSettings;

public class GameSettings implements IGameSettings {
	private final GameInput gameInput;
	private int displayWidth;
	private int displayHeight;

	public GameSettings(GameInput gameInput) {
		this.gameInput = gameInput;
	}

	@Override
	public GameInput getKeys() {
		return this.gameInput;
	}

	@Override
	public int getDisplayWidth() {
		return this.displayWidth;
	}

	public void setDisplayWidth(int displayWidth) {
		this.displayWidth = displayWidth;
	}

	@Override
	public int getDisplayHeight() {
		return this.displayHeight;
	}

	public void setDisplayHeight(int displayHeight) {
		this.displayHeight = displayHeight;
	}
}