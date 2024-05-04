package com.github.sef24sp4.wavemanager;

import com.github.sef24sp4.common.enemy.CommonEnemy;
import com.github.sef24sp4.common.interfaces.IEntityManager;
import com.github.sef24sp4.common.interfaces.IGameSettings;
import com.github.sef24sp4.common.services.IEntityProcessingService;
import com.github.sef24sp4.common.services.IGamePluginService;

public class WaveControlSystem implements IEntityProcessingService, IGamePluginService {
	private IWaveManager waveManager;
	// test
	private final long maxCoolDownTicks = 8_000;
	private long timeOfLastCheck;

	// used in waiting.
	int lastTimeCheck;

	@Override
	public void process(IEntityManager entityManager, IGameSettings gameSettings) {
		if (this.waveManager == null) {
			this.gameStart(entityManager, gameSettings);
		}
		switch (this.waveManager.getWaveStatus()) {
			case ONGOING -> {
				if (entityManager.getEntitiesByClass(CommonEnemy.class).isEmpty()) {
					this.waveManager.nextWave();
					this.waveManager.setWaveStatus(WaveStatus.WAITING);
					this.timeOfLastCheck = System.currentTimeMillis();
					// update wave number label
					System.out.println("Wave: " + this.waveManager.getWaveNumber());
				}
				// test
				if (this.maxCoolDownTicks - (System.currentTimeMillis() - this.timeOfLastCheck) < 0) {
					entityManager.removeEntitiesByClass(CommonEnemy.class);
				}
			}
			case WAITING -> {
				int currentTimeUntilNextWave = this.waveManager.getWaveTimer();
				if (currentTimeUntilNextWave <= 0) {
					this.waveManager.setWaveStatus(WaveStatus.READY);
					// remove label or hide text

				} else if (currentTimeUntilNextWave != lastTimeCheck) {
					lastTimeCheck = currentTimeUntilNextWave;
					// update label countdown
					System.out.println(currentTimeUntilNextWave);
				}
			}
			case READY -> {
				this.waveManager.getAllEntities().forEach(entityManager::addEntity);
				this.waveManager.setWaveStatus(WaveStatus.ONGOING);
			}
			case null, default -> {

			}
		}
	}

	@Override
	public void launch(IEntityManager entityManager, IGameSettings gameSettings) {

	}

	@Override
	public void gameStart(IEntityManager entityManager, IGameSettings gameSettings) {
		this.waveManager = new WaveManager(1, 5, gameSettings);
	}

	@Override
	public void gameStop(IEntityManager entityManager, IGameSettings gameSettings) {

	}
}
