package com.github.sef24sp4.common.interfaces;

import java.util.ServiceLoader;

public final class GameSettingsLoader {
	private static IGameSettings gameSettings;

	private GameSettingsLoader() {
	}

	/**
	 * Load the game global {@link IGameSettings} using {@link ServiceLoader}.
	 *
	 * @return The game global {@link IGameSettings}.
	 * @throws IllegalStateException If the {@link IGameSettings} is not yet initialized.
	 * @throws RuntimeException      Which wraps a {@link ClassNotFoundException} if no suitable {@link IGameSettings} implementation is found.
	 */
	public static IGameSettings load() {
		if (gameSettings == null) {
			gameSettings = ServiceLoader.load(IGameSettings.class).findFirst()
					.orElseThrow(() -> new RuntimeException(new ClassNotFoundException("Could not find any implementation of IGameSettings")));
		}
		return gameSettings;
	}
}
