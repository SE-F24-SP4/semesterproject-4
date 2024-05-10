package com.github.sef24sp4.core.game;

import com.github.sef24sp4.common.entities.IEntity;
import com.github.sef24sp4.core.interfaces.EntityToGraphicsMapper;
import com.github.sef24sp4.core.interfaces.IGameProcessor;
import com.github.sef24sp4.core.interfaces.IGameTickExecutor;

public class GameBuilder {
	private GameSettings gameSettings;
	private EntityToGraphicsMapper<IEntity, ?> entityEntityToGraphicsMapper;

	private IGameTickExecutor gameTickExecutor;

	public GameBuilder setGameSettings(final GameSettings gameSettings) {
		this.gameSettings = gameSettings;
		return this;
	}

	public GameBuilder setEntityEntityToGraphicsMapper(final EntityToGraphicsMapper<IEntity, ?> entityEntityToGraphicsMapper) {
		this.entityEntityToGraphicsMapper = entityEntityToGraphicsMapper;
		return this;
	}

	public GameBuilder setGameTickExecutor(final IGameTickExecutor gameTickExecutor) {
		this.gameTickExecutor = gameTickExecutor;
		return this;
	}

	public IGameProcessor build() {
		final GraphicsOverlayEntityManager graphicsOverlayEntityManager = new GraphicsOverlayEntityManager(this.entityEntityToGraphicsMapper);
		final GameLoop gameLoop = new GameLoop(this.gameSettings, graphicsOverlayEntityManager);
		return new Game(gameLoop, this.gameTickExecutor);
	}
}
