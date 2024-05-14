package com.github.sef24sp4.wavemanager;

import com.github.sef24sp4.common.enemy.CommonEnemy;
import com.github.sef24sp4.common.interfaces.IEntityManager;
import com.github.sef24sp4.common.interfaces.IGameSettings;
import com.github.sef24sp4.common.services.IEntityProcessingService;
import com.github.sef24sp4.common.services.IGamePluginService;

public class WaveControlSystem implements IEntityProcessingService, IGamePluginService {
	private static IWaveManager waveManager;
	// used in waiting.
	private int lastTimeCheck;

	// test
	private final long maxCoolDownTicks = 8_000;
	private long timeOfLastCheck;

	@Override
	public void process(IEntityManager entityManager, IGameSettings gameSettings) {
		switch (waveManager.getWaveStatus()) {
			case ONGOING -> {
				if (entityManager.getEntitiesByClass(CommonEnemy.class).isEmpty()) {
					waveManager.nextWave();
					waveManager.setWaveStatus(WaveStatus.WAITING);
					this.timeOfLastCheck = System.currentTimeMillis();
					// update wave number label
					System.out.println("Wave: " + waveManager.getWaveNumber());
				}
				// test
				if (this.maxCoolDownTicks - (System.currentTimeMillis() - this.timeOfLastCheck) < 0) {
					entityManager.removeEntitiesByClass(CommonEnemy.class);
				}
			}
			case WAITING -> {
				int currentTimeUntilNextWave = waveManager.getWaveTimer();
				if (currentTimeUntilNextWave <= 0) {
					waveManager.setWaveStatus(WaveStatus.READY);
					// remove label or hide text

				} else if (currentTimeUntilNextWave != this.lastTimeCheck) {
					this.lastTimeCheck = currentTimeUntilNextWave;
					// update label countdown
					System.out.println(currentTimeUntilNextWave);
				}
			}
			case READY -> {
				waveManager.getAllEntities().forEach(entityManager::addEntity);
				waveManager.setWaveStatus(WaveStatus.ONGOING);
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
