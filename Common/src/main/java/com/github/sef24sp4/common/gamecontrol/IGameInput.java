package com.github.sef24sp4.common.gamecontrol;

/**
 * An interface describing a way to get the current input.
 *
 * @see InputAction
 */
public interface IGameInput {

	/**
	 * Check if keys are down (InputActions are active).
	 *
	 * @param keys A variable amount of keys to check
	 * @return {@code true} if all keys are pressed. {@code false} otherwise.
	 * @see InputAction
	 */
	public boolean isDown(InputAction... keys);

	/**
	 * Check if keys have been pressed (InputActions are active).
	 *
	 * @param keys A variable amount of keys to check
	 * @return {@code true} if all keys are pressed. {@code false} otherwise.
	 * @see InputAction
	 */
	public boolean isPressed(InputAction... keys);

	public IMouseCoordinates getMouseCoordinates();
}
