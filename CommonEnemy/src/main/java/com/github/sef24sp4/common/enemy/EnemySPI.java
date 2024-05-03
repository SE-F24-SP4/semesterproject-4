package com.github.sef24sp4.common.enemy;

public interface EnemySPI {
	/**
	 * Create a new enemy with the specified tier.
	 * <p>
	 * Note, the new enemy does not have any start coordinates and should be provided after,
	 * or it will assume the standard values (0,0) provided by {@link com.github.sef24sp4.common.vector.Coordinates Coordinates}.
	 *
	 * @param enemyTier is a positive integer describing the tier and the difficulty of the enemy. the higher the number the higher the difficulty.
	 * @return An enemy object of type {@link CommonEnemy}.
	 * @throws IllegalArgumentException if a non-positive integer is provided.
	 */
	public CommonEnemy createEnemy(int enemyTier);

	/**
	 * Gets the {@link EnemyRole } of this type of enemy.
	 *
	 * @return A {@link EnemyRole } of this type of enemy.
	 */
	public EnemyRole getRole();
}