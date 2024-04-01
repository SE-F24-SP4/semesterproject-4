package com.github.sef24sp4.core.interfaces;

/**
 * This interface specifies a generic game processor.
 * It is responsible for handling the life cycle of the core game.
 */
public interface IGameProcessor {

	/**
	 * Begin the game.
	 * This should start a new game play in asynchronous manner.
	 */
	public void begin();

	/**
	 * End the game.
	 * The game will be stopped and should release any active resources.
	 * It is assumed that the application can safely terminate after the method call returns.
	 */
	public void end();

	/**
	 * Pause the game.
	 * This should temporarily halt execution of the game.
	 *
	 * @see #resume()
	 */
	public void pause();

	/**
	 * Resumes the game from a paused state.
	 * The game should continue as if it had not been paused to begin with.
	 * This call should be asynchronous as with {@link #begin()}.
	 *
	 * @see #pause()
	 */
	public void resume();
}
