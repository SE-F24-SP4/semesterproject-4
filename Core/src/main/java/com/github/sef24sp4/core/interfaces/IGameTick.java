package com.github.sef24sp4.core.interfaces;

/**
 * A GameTick represents the code which gets executed continuously.
 */
@FunctionalInterface
public interface IGameTick {
	/**
	 * The code to be executed continuously.
	 *
	 * @param now The current {@link System#nanoTime()}. Can be used to compute time differences between ticks.
	 */
	public void tick(final long now);
}
