package com.github.sef24sp4.wavemanager;

import com.github.sef24sp4.common.interfaces.IEntityManager;

/**
 * A WaveManager that is used to manage the waves of enemy entities in a game.
 * <p>
 * This extends {@link IEntityManager} to use its capabilities for handling and storing enemy entities.
 * The {@code IWaveManager} is specifically designed to construct enemy compositions and handle
 * wave progression.
 *
 * @see IEntityManager
 * @see WaveStatus
 */
public interface IWaveManager extends IEntityManager {
	/**
	 * Gets the current wave number.
	 *
	 * @return The current wave number as an integer. This number increments
	 * each time a new wave begins.
	 */
	public int getWaveNumber();

	/**
	 * Gets the difficulty level of the current wave.
	 * <p>
	 * The difficulty is calculated based on the wave's characteristics and the
	 * predefined scaling logic. The value is a long integer that quantifies the
	 * challenge or complexity expected for the wave.
	 *
	 * @return The current difficulty level of the wave as a long. This value
	 * is computed when the wave is initiated or updated and remains constant
	 * throughout the wave's lifecycle.
	 */
	public long getWaveDifficulty();

	/**
	 * Gets the current status of the WaveManager.
	 *
	 * @return A {@link WaveStatus } representing the current status of this WaveManager,
	 * providing insight into the wave's lifecycle and control states.
	 */
	public WaveStatus getWaveStatus();

	/**
	 * Sets the status of the WaveManager to the specified state.
	 *
	 * @param waveStatus The new status that the WaveManager should be set to; must not be null.
	 * It determines the behavior and the operational state of the WaveManager.
	 */
	public void setWaveStatus(WaveStatus waveStatus);

	/**
	 * Gets the time remaining until the next wave starts and enemies begin to spawn.
	 * <p>
	 * This method returns the countdown timer's current value in seconds, which indicates
	 * how much time is left before the next wave of enemies is spawned.
	 *
	 * @return The number of milliseconds remaining until the next wave starts. Will return -1 if the wave is ongoing.
	 */
	public long getSecondsUntilNextWave();

	/**
	 * Handles all logic that should happen when transitioning to the next wave.
	 * <p>
	 * This method is responsible for advancing the game's wave counter and initiating
	 * the setup for the next wave. This could include spawning new enemies, resetting
	 * player health or resources, and recalculating difficulties. It ensures the game
	 * progresses smoothly from one wave to the next, maintaining the continuity and
	 * challenge of the gameplay.
	 */
	public void nextWave();

	/**
	 * Starts the wave and adds the enemy composition to the entityManager provided.
	 * <p>
	 * Represents the transition and the spawning time of the new enemies when the wave is ready to spawn.
	 * And is responsible for adding enemies to the entityManager.
	 *
	 * @param entityManager Where the enemy composition will be added to.
	 */
	public void startWaveWithEntities(IEntityManager entityManager);

	/**
	 * Handles logic when waiting to spawn the next wave.
	 * <p>
	 * This method is responsible for checking if it is ready to start the wave.
	 */
	public void processWaitingWave();

	/**
	 * Handles logic when the wave is ongoing.
	 * <p>
	 * This method is responsible for checking if it can go into the next wave.
	 *
	 * @param entityManager is used to check entities in the ongoing game.
	 */
	public void processOngoingWave(IEntityManager entityManager);
}
