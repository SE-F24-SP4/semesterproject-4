package com.github.sef24sp4.common.enemy;

import com.github.sef24sp4.common.entities.IEntity;
public interface EnemySPI {
	/**
	 * Create a new enemy with the specified difficulty.
	 * <p>
	 * Note the new enemy does not have any start coordinates and should be provided after,
	 * or it will assume the standard values (0,0) provided by {@link com.github.sef24sp4.common.data.Coordinates}.
	 *
	 * @param difficulty is a positive integer describing the difficulty of the enemy.
	 * @return An enemy object of type {@link IEntity}.
	 * @throws IllegalArgumentException if a non-positive integer is provided.
	 * @see IEntity
	 */
	public IEntity createEnemy(int difficulty);
}
