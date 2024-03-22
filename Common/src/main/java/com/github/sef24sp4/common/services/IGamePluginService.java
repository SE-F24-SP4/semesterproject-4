package com.github.sef24sp4.common.services;

import com.github.sef24sp4.common.interfaces.IEntityManager;
import com.github.sef24sp4.common.interfaces.IGameSettings;

public interface IGamePluginService {

	/**
	 * Called when the application is launched and plugins are loaded
	 * @param entityManager
	 * @param gameSettings
	 */
	public void launch(IEntityManager entityManager, IGameSettings gameSettings);


	/**
	 * Called when a game is started
	 * @param entityManager
	 * @param gameSettings
	 */
	public void gameStart(IEntityManager entityManager, IGameSettings gameSettings);

	/**
	 * Gets called when the current game is has ended
	 * @param entityManager
	 * @param gameSettings
	 */
	public void gameStop(IEntityManager entityManager, IGameSettings gameSettings);
}
