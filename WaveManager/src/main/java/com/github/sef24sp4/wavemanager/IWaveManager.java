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
	 * @return The number of seconds remaining until the next wave starts.
	 */
	public int getSecondsUntilNextWave();

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
}
