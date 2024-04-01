package com.github.sef24sp4.core.game;

import com.github.sef24sp4.common.services.IEntityProcessingService;
import com.github.sef24sp4.core.interfaces.IGameTick;

import java.util.ServiceLoader;

public class GameLoop implements IGameTick {
	private final EntityManager entityManager;

	private final GameSettings gameSettings;

	private final ServiceLoader<IEntityProcessingService> processingServices = ServiceLoader.load(IEntityProcessingService.class);


	GameLoop(GameSettings gameSettings, EntityManager entityManager) {
		this.gameSettings = gameSettings;
		this.entityManager = entityManager;
	}


	@Override
	public void tick(long now) {
		this.processingServices.forEach(s -> s.process(this.entityManager, this.gameSettings));
		this.entityManager.render();
		this.gameSettings.getKeys().clearCurrentTickKeys();
	}


	public EntityManager getEntityManager() {
		return this.entityManager;
	}

	public GameSettings getGameSettings() {
		return this.gameSettings;
	}
}
