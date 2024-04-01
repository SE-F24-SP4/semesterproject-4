package com.github.sef24sp4.core.game;

import com.github.sef24sp4.common.services.IGamePluginService;
import com.github.sef24sp4.core.interfaces.IGameProcessor;
import com.github.sef24sp4.core.interfaces.IGameTickExecutor;

import java.util.ServiceLoader;

public class Game implements IGameProcessor {
	private final ServiceLoader<IGamePluginService> gamePluginServices = ServiceLoader.load(IGamePluginService.class);
	private final IGameTickExecutor gameTickExecutor;
	private final GameLoop gameLoop;

	Game(GameLoop gameLoop, IGameTickExecutor gameTickExecutor) {
		this.gameLoop = gameLoop;
		this.gameTickExecutor = gameTickExecutor;
		this.gameTickExecutor.setGameTick(this.gameLoop);
	}

	@Override
	public void begin() {
		this.gamePluginServices.forEach(s -> s.gameStart(this.gameLoop.getEntityManager(), this.gameLoop.getGameSettings()));
		this.gameTickExecutor.start();
	}

	@Override
	public void end() {
		this.gamePluginServices.forEach(s -> s.gameStop(this.gameLoop.getEntityManager(), this.gameLoop.getGameSettings()));
		this.gameTickExecutor.stop();
	}

	@Override
	public void pause() {
		this.gameTickExecutor.stop();
	}

	@Override
	public void resume() {
		this.gameTickExecutor.start();
	}
}
