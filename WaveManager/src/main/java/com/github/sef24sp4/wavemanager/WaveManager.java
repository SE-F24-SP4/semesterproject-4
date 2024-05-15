package com.github.sef24sp4.wavemanager;

import com.github.sef24sp4.common.enemy.CommonEnemy;
import com.github.sef24sp4.common.enemy.EnemyRole;
import com.github.sef24sp4.common.enemy.EnemySPI;
import com.github.sef24sp4.common.entities.IEntity;
import com.github.sef24sp4.common.interfaces.IEntityManager;
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

	private final int waveCooldownInSeconds;

	private long timeOfLastWaveStart;

	private WaveStatus waveStatus = WaveStatus.ONGOING;

	private final Map<EnemyRole, List<EnemySPI>> enemyCatalog = new HashMap<>();

	private final int baseDifficulty;
	private final double growthRate;

	// used in processWaitingWave() to measure time and make sure we update only when needed to.
	private long lastSecondTimeCheck;

	// test
	private final long maxCoolDownTicks = 8_000;
	private long timeOfLastCheck;

	/**
	 * Constructs a new WaveManager to manage waves, wave timing and enemy compositions.
	 * <p>
	 * This constructor initializes the WaveManager with the specified starting wave number,
	 * the initial countdown until the next wave spawns, and game settings to manage spawning.
	 * The actual wave number starts one less than the provided startWaveNumber
	 * because it will be incremented by the nextWave function at the start of the game.
	 *
	 * @param startWaveNumber The wave number from which to start the count, must be greater than 0.
	 * @param waveCooldownInSeconds The time in seconds until the next wave spawns, can't be negative.
	 * If provided 0 it means that no time should be wasted and the next wave should spawn when it's ready.
	 * @param gameSettings The settings for the map to assign spawn locations.
	 *
	 * @exception IllegalArgumentException is thrown when the startWaveNumber is below 1, or if
	 * timeUntilNextWaveInSeconds is negative.
	 */
		public WaveManager(final int startWaveNumber, final int waveCooldownInSeconds, final IGameSettings gameSettings) {
		this(startWaveNumber, waveCooldownInSeconds, gameSettings, 50, 1.25);
	}

	public WaveManager(final int startWaveNumber, final int waveCooldownInSeconds, final IGameSettings gameSettings, final int baseDifficulty, final double growthRate) {
		if (startWaveNumber < 1) throw new IllegalArgumentException("startWaveNumber must be greater than 0");
		if (waveCooldownInSeconds < 0) throw new IllegalArgumentException("timeUntilNextWaveInSeconds must be greater or equal to 0");
		if (baseDifficulty < 1) throw new IllegalArgumentException("baseDifficulty must be greater than 0");
		if (growthRate < 1) throw new IllegalArgumentException("growthRate must be greater than 0");

		this.waveNumber = startWaveNumber - 1; // gets +1 in the nextWave function
		this.gameSettings = gameSettings;
		this.waveCooldownInSeconds = waveCooldownInSeconds;
		this.baseDifficulty = baseDifficulty;
		this.growthRate = growthRate;

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
	public long getSecondsUntilNextWave() {
		if (this.waveStatus == WaveStatus.ONGOING) return -1;
		final long remainingTime = Math.multiplyFull(this.waveCooldownInSeconds, 1000) - (System.currentTimeMillis() - this.timeOfLastWaveStart);
		if (remainingTime <= 0) return 0;
		return remainingTime;
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
	public void startWaveWithEntities(IEntityManager entityManager) {
		this.waveStatus = WaveStatus.ONGOING;
		this.getAllEntities().forEach(entityManager::addEntity);
	}

	@Override
	public void processWaitingWave() {
		long currentTimeUntilNextWave = Math.ceilDiv(this.getSecondsUntilNextWave(), 1000);
		if (currentTimeUntilNextWave <= 0) {
			this.waveStatus = WaveStatus.READY;
			// remove label or hide text

		} else if (currentTimeUntilNextWave != this.lastSecondTimeCheck) {
			this.lastSecondTimeCheck = currentTimeUntilNextWave;
			// update label countdown
			System.out.println(currentTimeUntilNextWave);
		}
	}

	@Override
	public void processOngoingWave(IEntityManager entityManager) {
		if (entityManager.getEntitiesByClass(CommonEnemy.class).isEmpty()) {
			this.nextWave();
			this.waveStatus = WaveStatus.WAITING;
			this.timeOfLastCheck = System.currentTimeMillis();
			// update wave number label
			System.out.println("Wave: " + this.waveNumber);
		}
		// test
		if (this.maxCoolDownTicks - (System.currentTimeMillis() - this.timeOfLastCheck) < 0) {
			entityManager.removeEntitiesByClass(CommonEnemy.class);
		}
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
		this.waveDifficulty = Math.round(this.baseDifficulty * Math.pow(this.growthRate, this.waveNumber - 1));
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
