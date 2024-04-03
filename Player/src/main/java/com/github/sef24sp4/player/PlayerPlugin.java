package com.github.sef24sp4.player;

import com.github.sef24sp4.common.data.Coordinates;
import com.github.sef24sp4.common.entities.CommonEntity;
import com.github.sef24sp4.common.interfaces.IEntityManager;
import com.github.sef24sp4.common.interfaces.IGameSettings;
import com.github.sef24sp4.common.services.IGamePluginService;

public class PlayerPlugin implements IGamePluginService {
	private CommonEntity player;
	@Override
	public void launch(IEntityManager entityManager, IGameSettings gameSettings) {

	}

	@Override
	public void gameStart(IEntityManager entityManager, IGameSettings gameSettings) {
		this.player = this.createPlayer(gameSettings);
		entityManager.addEntity(this.player);
	}

	@Override
	public void gameStop(IEntityManager entityManager, IGameSettings gameSettings) {
		entityManager.removeEntity(this.player);
	}

	public CommonEntity createPlayer(IGameSettings gameSettings) {
		this.player = new Player();
		this.player.setX(gameSettings.getDisplayWidth() / 2.0);
		this.player.setY(gameSettings.getDisplayHeight() / 2.0);
		this.player.setPolygonCoordinates(
				new Coordinates(-5, -5),
				new Coordinates(10, 0),
				new Coordinates(-5, 5)
		);
		return this.player;
	}
}
