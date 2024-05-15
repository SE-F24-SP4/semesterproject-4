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
		final Player player = Player.reinitializePlayer();
		entityManager.addEntity(player);
		player.setX(gameSettings.getDisplayWidth() / 2.0);
		player.setY(gameSettings.getDisplayHeight() / 2.0);
	}

	@Override
	public void gameStop(IEntityManager entityManager, IGameSettings gameSettings) {
		entityManager.removeEntity(Player.getPlayer());
	}
}
