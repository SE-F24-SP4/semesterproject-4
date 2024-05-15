package com.github.sef24sp4.wavemanager;

import com.github.sef24sp4.common.interfaces.IEntityManager;
import com.github.sef24sp4.common.interfaces.IGameSettings;
import com.github.sef24sp4.common.services.IEntityProcessingService;
import com.github.sef24sp4.common.services.IGamePluginService;

public class WaveControlSystem implements IEntityProcessingService, IGamePluginService {
	private static IWaveManager waveManager;

	@Override
	public void process(IEntityManager entityManager, IGameSettings gameSettings) {
		switch (waveManager.getWaveStatus()) {
			case ONGOING -> {
				waveManager.processOngoingWave(entityManager);
			}
			case WAITING -> {
				waveManager.processWaitingWave();
			}
			case READY -> {
				waveManager.startWaveWithEntities(entityManager);
			}
			case null, default -> throw new IllegalStateException("Unexpected value: " + waveManager.getWaveStatus());
		}
	}

	@Override
	public void launch(IEntityManager entityManager, IGameSettings gameSettings) {

	}

	@Override
	public void gameStart(IEntityManager entityManager, IGameSettings gameSettings) {
		waveManager = new WaveManager(1, 5, gameSettings);
	}

	@Override
	public void gameStop(IEntityManager entityManager, IGameSettings gameSettings) {

	}
}
