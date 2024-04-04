package com.github.sef24sp4.player;

import com.github.sef24sp4.common.interfaces.IEntityManager;
import com.github.sef24sp4.common.interfaces.IGameSettings;
import com.github.sef24sp4.common.services.IGamePluginService;

public class PlayerPlugin implements IGamePluginService {

	@Override
	public void launch(IEntityManager entityManager, IGameSettings gameSettings) {

	}

	@Override
	public void gameStart(IEntityManager entityManager, IGameSettings gameSettings) {
		entityManager.addEntity(Player.getPlayer());
		Player.getPlayer().setX(gameSettings.getDisplayWidth() / 2);
		Player.getPlayer().setY(gameSettings.getDisplayWidth() / 2);
	}

	@Override
	public void gameStop(IEntityManager entityManager, IGameSettings gameSettings) {
		entityManager.removeEntity(Player.getPlayer());
	}
}
