package com.github.sef24sp4.common.ai.map;

import com.github.sef24sp4.common.interfaces.GameSettingsLoader;
import com.github.sef24sp4.common.interfaces.IGameSettings;

import java.util.Optional;
import java.util.ServiceLoader;

/**
 * A factory for creating and fetching a {@link Map} instance.
 */
public interface MapProvider {
	/**
	 * Load any available {@link MapProvider} using {@link ServiceLoader#load(Class)} and fetch the provided {@link Map}.
	 *
	 * @param gameSettings The {@link IGameSettings} of the game.
	 * @return An {@link Optional} with the fetched {@link Map} if a {@link MapProvider} was found.
	 * Otherwise, an {@link Optional#empty() empty Optional} is returned.
	 * @see #fetch(IGameSettings)
	 */
	public static Optional<Map> load(final IGameSettings gameSettings) {
		return ServiceLoader.load(MapProvider.class).findFirst().map(s -> s.fetch(gameSettings));
	}

	/**
	 * An alias for {@link #load(IGameSettings) load(GameSettingsLoader.load())}.
	 *
	 * @return An {@link Optional} with the fetched {@link Map} if a {@link MapProvider} was found.
	 * Otherwise, an {@link Optional#empty() empty Optional} is returned.
	 * @see #fetch(IGameSettings)
	 * @see #load(IGameSettings)
	 */
	public static Optional<Map> load() {
		return load(GameSettingsLoader.load());
	}

	/**
	 * Fetch a {@link Map} from the current provider.
	 * <p/>
	 * It is generally recommended to use the static {@link #load(IGameSettings)} method,
	 * when wanting to fetch a {@link Map} from a provider.
	 *
	 * @param gameSettings The {@link IGameSettings} of the game.
	 * @return The {@link Map} instance of the provider.
	 * @see #load(IGameSettings)
	 */
	public Map fetch(final IGameSettings gameSettings);
}
