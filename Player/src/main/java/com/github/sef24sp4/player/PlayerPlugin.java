package com.github.sef24sp4.player;

import com.github.sef24sp4.common.data.Coordinates;
import com.github.sef24sp4.common.entities.CommonEntity;
import com.github.sef24sp4.common.interfaces.IEntityManager;
import com.github.sef24sp4.common.interfaces.IGameSettings;
import com.github.sef24sp4.common.services.IGamePluginService;

import java.util.ArrayList;
import java.util.List;


public class PlayerPlugin implements IGamePluginService {
	private CommonEntity player;
	@Override
	public void launch(IEntityManager entityManager, IGameSettings gameSettings) {
			this.player = this.createPlayer(gameSettings);
			entityManager.addEntity(this.player);
	}

	@Override
	public void gameStart(IEntityManager entityManager, IGameSettings gameSettings) {

	}

	@Override
	public void gameStop(IEntityManager entityManager, IGameSettings gameSettings) {
		entityManager.removeEntity(this.player);
	}

	public CommonEntity createPlayer(IGameSettings gameSettings) {
		this.player = new Player();
		this.player.setX(gameSettings.getDisplayWidth() / 2);
		this.player.setY(gameSettings.getDisplayHeight() / 2);
		List<Coordinates> a = new ArrayList<>();
		a.add(new Coordinates(-5, -5));
		a.add(new Coordinates(10, 0));
		a.add(new Coordinates(-5, 5));
		this.player.setPolygonCoordinates(a);
		return this.player;
	}
}
