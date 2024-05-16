package com.github.sef24sp4.core.game;

import com.github.sef24sp4.common.interfaces.IGameSettings;

import java.util.function.BooleanSupplier;

public class GameSettings implements IGameSettings {
	private final GameInput gameInput;
	private int displayWidth;
	private int displayHeight;

	private BooleanSupplier endGameCallback;

	public GameSettings(final GameInput gameInput) {
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

	public void setDisplayWidth(final int displayWidth) {
		this.displayWidth = displayWidth;
	}

	@Override
	public int getDisplayHeight() {
		return this.displayHeight;
	}

	public void setDisplayHeight(final int displayHeight) {
		this.displayHeight = displayHeight;
	}

	@Override
	public boolean stopGame() {
		if (this.endGameCallback == null) return false;
		return this.endGameCallback.getAsBoolean();
	}

	public void setEndGameCallback(final BooleanSupplier endGameCallback) {
		this.endGameCallback = endGameCallback;
	}
}
