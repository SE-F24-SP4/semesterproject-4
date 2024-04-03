package com.github.sef24sp4.core.game;

import com.github.sef24sp4.common.services.IEntityProcessingService;
import com.github.sef24sp4.core.interfaces.IGameTick;

import java.util.ServiceLoader;

public class GameLoop implements IGameTick {
	private final GraphicsOverlayEntityManager graphicsOverlayEntityManager;

	private final GameSettings gameSettings;

	private final ServiceLoader<IEntityProcessingService> processingServices = ServiceLoader.load(IEntityProcessingService.class);


	GameLoop(GameSettings gameSettings, GraphicsOverlayEntityManager graphicsOverlayEntityManager) {
		this.gameSettings = gameSettings;
		this.graphicsOverlayEntityManager = graphicsOverlayEntityManager;
	}


	@Override
	public void tick(long now) {
		this.processingServices.forEach(s -> s.process(this.graphicsOverlayEntityManager, this.gameSettings));
		this.graphicsOverlayEntityManager.render();
		this.gameSettings.getKeys().clearCurrentTickKeys();
	}


	public GraphicsOverlayEntityManager getEntityManager() {
		return this.graphicsOverlayEntityManager;
	}

	public GameSettings getGameSettings() {
		return this.gameSettings;
	}
}
