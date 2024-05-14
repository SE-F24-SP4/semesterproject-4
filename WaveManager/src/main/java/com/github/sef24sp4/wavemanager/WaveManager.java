package com.github.sef24sp4.wavemanager;

import com.github.sef24sp4.common.enemy.CommonEnemy;
import com.github.sef24sp4.common.enemy.EnemyRole;
import com.github.sef24sp4.common.enemy.EnemySPI;
import com.github.sef24sp4.common.entities.IEntity;
import com.github.sef24sp4.common.interfaces.IGameSettings;
import com.github.sef24sp4.common.vector.Coordinates;

import java.util.*;

public class WaveManager implements IWaveManager {

	private Collection<IEntity> enemyComposition = new HashSet<>();

	/**
	 * used instead of a map.
	 */
	private final IGameSettings gameSettings;

	private int waveNumber;

	private long waveDifficulty;

	private final int timeUntilNextWaveInSeconds;

	private long timeOfLastWaveStart;

	private WaveStatus waveStatus = WaveStatus.ONGOING;

	private final Map<EnemyRole, List<EnemySPI>> enemyCatalog = new HashMap<>();

	public WaveManager(int startWaveNumber, int timeUntilNextWaveInSeconds, IGameSettings gameSettings) {
		if (startWaveNumber < 1) throw new IllegalArgumentException("startWaveNumber must be greater than 0");
		this.waveNumber = startWaveNumber - 1; // gets +1 in the nextWave function
		this.gameSettings = gameSettings;
		this.timeUntilNextWaveInSeconds = timeUntilNextWaveInSeconds;
		this.constructCatalog();
	}

	@Override
	public WaveStatus getWaveStatus() {
		return this.waveStatus;
	}

	@Override
	public void setWaveStatus(WaveStatus waveStatus) {
		this.waveStatus = waveStatus;
	}

	@Override
	public int getWaveNumber() {
		return this.waveNumber;
	}

	@Override
	public long getWaveDifficulty() {
		return this.waveDifficulty;
	}

	@Override
	public int getSecondsUntilNextWave() {
		final long remainingTime = Math.multiplyFull(this.timeUntilNextWaveInSeconds, 1000) - (System.currentTimeMillis() - this.timeOfLastWaveStart);
		if (remainingTime <= 0) return 0;
		return Math.toIntExact(Math.ceilDiv(remainingTime, 1000));
	}

	@Override
	public boolean addEntity(final IEntity entity) {
		return this.enemyComposition.add(entity);
	}

	@Override
	public boolean removeEntity(final IEntity entity) {
		return this.enemyComposition.remove(entity);
	}

	@Override
	public Collection<IEntity> getAllEntities() {
		return Collections.unmodifiableCollection(this.enemyComposition);
	}

	@Override
	public void nextWave() {
		this.waveNumber++;
		this.updateDifficulty();
		this.enemyComposition = this.calculateWaveComposition();
		this.assignSpawnLocations();
		this.timeOfLastWaveStart = System.currentTimeMillis();
	}

	private void constructCatalog() {
		ServiceLoader.load(EnemySPI.class).forEach(enemySPI -> {
			EnemyRole currentRole = enemySPI.getRole();
			if (!this.enemyCatalog.containsKey(currentRole)) {
				this.enemyCatalog.put(currentRole, new ArrayList<>());
			}
			this.enemyCatalog.get(currentRole).add(enemySPI);
		});
	}

	private void updateDifficulty() {
		int baseDifficulty = 50;
		double growthRate = 1.25;
		this.waveDifficulty = Math.round(baseDifficulty * Math.pow(growthRate, this.waveNumber - 1));
	}

	private Collection<IEntity> calculateWaveComposition() {
		Random rand = new Random(this.waveNumber);
		List<IEntity> waveComposition = new ArrayList<>();
		long currentDifficulty = 0;

		int maxTier = this.waveNumber;
		int currentTier;

		while (currentDifficulty < this.waveDifficulty) {
			// finding the tier of enemy to spawn
			if (currentDifficulty < this.waveDifficulty * 0.1) {
				// looks at higher tier enemies if the current difficulty is below 10% of the wave difficulty
				currentTier = rand.nextInt(maxTier / 2, maxTier);
			} else if (currentDifficulty > this.waveDifficulty * 0.9) {
				// looks at lower tier enemies if the current difficulty is higher than 90% of the wave difficulty
				currentTier = rand.nextInt(maxTier / 2);
			} else {
				// when the current difficulty is between 10% - 90% of the wave difficulty,
				// look for a tier between 10% and 90% of the maxTier
				currentTier = rand.nextInt(maxTier / 10, Math.toIntExact(Math.round(maxTier * 0.9)));
			}

			EnemySPI enemySPI = this.getSuitableEnemySPI(rand);
			CommonEnemy currentEnemy = enemySPI.createEnemy(currentTier + 1);
			currentDifficulty += currentEnemy.getDifficulty();
			waveComposition.add(currentEnemy);
		}
		return waveComposition;
	}

	private EnemySPI getSuitableEnemySPI(Random rand) {
		EnemyRole randomRole = this.getRandomRole(rand, this.enemyCatalog.keySet());
		List<EnemySPI> enemyList = this.enemyCatalog.get(randomRole);
		return enemyList.get(rand.nextInt(enemyList.size()));
	}

	// used to get a random role
	// the chances of getting a role is described here.
	private EnemyRole getRandomRole(Random rand, Set<EnemyRole> enemyRoles) {
		List<EnemyRole> availableRoles = new ArrayList<>();
		if (enemyRoles.contains(EnemyRole.ATTACKER)) {
			for (int i = 0; i < 5; i++) { // 50% chance
				availableRoles.add(EnemyRole.ATTACKER);
			}
		}
		if (enemyRoles.contains(EnemyRole.TANK)) {
			for (int i = 0; i < 2; i++) { // 20% chance
				availableRoles.add(EnemyRole.TANK);
			}
		}
		if (enemyRoles.contains(EnemyRole.SUPPORT)) {
			for (int i = 0; i < 3; i++) { // 30% chance
				availableRoles.add(EnemyRole.SUPPORT);
			}
		}

		if (availableRoles.isEmpty()) {
			throw new IllegalArgumentException("No suitable roles available");
		}

		int index = rand.nextInt(availableRoles.size());
		return availableRoles.get(index);
	}



	// goes through the list of enemies and assigns them a spawn location
	private void assignSpawnLocations() {
		this.assignCircularSpawnPoints();
		// cannot spawn near the player
		// can spawn in the same area, like north or south of the map
		// can spawn on the opposite sites of each-other, so south and north
		// random
	}

	// assigns enemy composition to spawn in a circle.
	private void assignCircularSpawnPoints() {
		Coordinates center = new Coordinates((double) this.gameSettings.getDisplayWidth() / 2, (double) this.gameSettings.getDisplayHeight() / 2);
		int radius = this.gameSettings.getDisplayHeight() / 3;
		int enemyAmount = this.enemyComposition.size();

		int enemyIndex = 0;
		for (IEntity enemy : this.enemyComposition) {
			// find x
			double x = radius * Math.cos(2 * Math.PI * enemyIndex / enemyAmount) + center.getX();
			// find y
			double y = radius * Math.sin(2 * Math.PI * enemyIndex / enemyAmount) + center.getY();

			enemy.getCoordinates().setX(x);
			enemy.getCoordinates().setY(y);
			enemyIndex++;
		}
	}
}
