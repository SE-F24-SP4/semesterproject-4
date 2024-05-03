package com.github.sef24sp4.common.enemy;

import com.github.sef24sp4.common.entities.CommonEntity;

/**
 * Abstract class representing a common enemy entity in the game.
 * <p>
 * This class extends {@link CommonEntity} to inherit base entity functionalities
 * and introduces additional features specific to enemies, such as difficulty rating and tiers.
 *
 * @see CommonEntity
 */
public abstract class CommonEnemy extends CommonEntity {
	/**
	 * Gets the difficulty level of this enemy.
	 * <p>
	 * The difficulty level is a numeric representation of how challenging an enemy is to overcome.
	 * This value may affect the enemy's attributes such as health and damage output.
	 * It can be used to scale the gameplay difficulty dynamically.
	 *
	 * @return The difficulty rating of the enemy, usually represented as a long integer.
	 */
	public abstract long getDifficulty();

	/**
	 * Gets the tier level of this enemy.
	 * <p>
	 * The tier level is an integer that represents the enemy's rank,
	 * Higher tiers typically indicate a stronger, more challenging enemy.
	 *
	 * @return The tier level of the enemy, indicating its difficulty.
	 */
	public abstract int getTier();
}
