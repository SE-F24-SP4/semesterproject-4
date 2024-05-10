package com.github.sef24sp4.core.interfaces;


/**
 * A GameTick executor asynchronously runs the game tick code for every frame shown.
 * Depending on the implementation the GameTick may or may not be executed on the calling thread.
 */
public interface IGameTickExecutor {

	/**
	 * Sets the code to be executed every game tick.
	 *
	 * @param gameTick The gameTick to be executed.
	 */
	public void setGameTick(final IGameTick gameTick);


	/**
	 * Starts the GameTickExecutor asynchronously.
	 */
	public void start();

	/**
	 * Stops the GameTickExecutor.
	 * It cannot be expected that any currently running gameTick is interrupted.
	 * This will depend on the implementation.
	 */
	public void stop();
}
