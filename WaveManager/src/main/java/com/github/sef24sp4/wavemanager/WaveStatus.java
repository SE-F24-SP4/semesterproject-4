package com.github.sef24sp4.wavemanager;

/**
 * Represents the status of a wave in a game.
 * <p>
 * This enum is used to control and display the current state of waves in the game,
 * such as whether a wave is currently active, on hold, or prepared to start.
 */
public enum WaveStatus {
	/**
	 * Indicates that the wave is currently ongoing and active.
	 * This status is used when the wave is actively in progress, with players
	 * engaging with the game's challenges.
	 */
	ONGOING,

	/**
	 * Indicates that the game is waiting before the next wave begins.
	 * This status can be used during intervals between waves, allowing
	 * players time to prepare or rest.
	 */
	WAITING,

	/**
	 * Indicates that the wave is ready to begin.
	 * This status is used to signal that all conditions for starting the new wave
	 * have been met, such as the completion of a waiting period or the achievement
	 * of necessary game objectives.
	 */
	READY
}
