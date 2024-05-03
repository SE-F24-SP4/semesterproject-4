package com.github.sef24sp4.common.enemy;

public interface EnemySPI {
	/**
	 * Creates a new enemy with the specified tier.
	 * <p>
	 * Note, the newly created enemy does not have any start coordinates and should be provided
	 * after creation, or it will assume the default coordinates (0,0) as specified by
	 * {@link com.github.sef24sp4.common.vector.Coordinates Coordinates}.
	 *
	 * @param enemyTier is a positive integer describing the tier and the difficulty of the enemy.
	 * the higher the number, the greater the difficulty.
	 * @return An enemy object of type {@link CommonEnemy}.
	 * @throws IllegalArgumentException if a non-positive integer is provided.
	 */
	public CommonEnemy createEnemy(int enemyTier);

	/**
	 * Gets the role of this type of enemy.
	 * <p>
	 * The role can indicate specific behaviors, responsibilities, or abilities of the enemy,
	 * which are significant for gameplay strategies and interactions.
	 *
	 * @return The {@link EnemyRole} of this type of enemy, detailing its designated function
	 * or behavior within the game.
	 * @see EnemyRole
	 */
	public EnemyRole getRole();
}