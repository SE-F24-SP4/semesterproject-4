package com.github.sef24sp4.core.game;

import com.github.sef24sp4.common.collisionsystem.CollisionSystemSPI;
import com.github.sef24sp4.common.services.IEntityProcessingService;
import com.github.sef24sp4.core.interfaces.IGameTick;

import java.util.ServiceLoader;

public class GameLoop implements IGameTick {
	private final GraphicsOverlayEntityManager graphicsOverlayEntityManager;

	private final GameSettings gameSettings;

	private final CollisionSystemSPI collisionSystemSPI;

	private final ServiceLoader<IEntityProcessingService> processingServices = ServiceLoader.load(IEntityProcessingService.class);


	GameLoop(final GameSettings gameSettings, final GraphicsOverlayEntityManager graphicsOverlayEntityManager, final CollisionSystemSPI collisionSystemSPI) {
		this.gameSettings = gameSettings;
		this.graphicsOverlayEntityManager = graphicsOverlayEntityManager;
		this.collisionSystemSPI = collisionSystemSPI;
	}


	@Override
	public void tick(final long now) {
		this.processingServices.forEach(s -> s.process(this.graphicsOverlayEntityManager, this.gameSettings));
		this.collisionSystemSPI.processCollisions(this.graphicsOverlayEntityManager);
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
