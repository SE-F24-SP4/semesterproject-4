package com.github.sef24sp4.core.game;

public final class GameSettingsProvider {
	private static GameSettings settings;

	private GameSettingsProvider() {
	}

	public static void setSettings(GameSettings settings) {
		GameSettingsProvider.settings = settings;
	}

	public static GameSettings provider() {
		if (settings == null) throw new IllegalStateException("Game settings provider not yet initialized");
		return settings;
	}
}
