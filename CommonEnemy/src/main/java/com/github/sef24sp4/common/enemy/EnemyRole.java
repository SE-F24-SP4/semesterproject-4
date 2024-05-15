package com.github.sef24sp4.common.enemy;

/**
 * Represents distinct roles that an enemy can fulfill in a game.
 * <p>
 * Each role is associated with specific behaviors, abilities, and strategic significance, influencing how
 * enemies interact with the player and other entities in the game environment.
 */
public enum EnemyRole {
	/**
	 * Represents an enemy that primarily focuses on offensive actions against the player.
	 * Attackers usually have high damage output but may vary in terms of defense and speed.
	 */
	ATTACKER,

	/**
	 * Represents an enemy designed to absorb damage and protect other enemies.
	 * Tanks typically have high health and defense, serving as a shield for weaker roles.
	 */
	TANK,

	/**
	 * Represents an enemy that provides support to other enemies through healing, buffs, or other enhancements.
	 * Support enemies may not be very effective in direct combat but are crucial for the survivability and
	 * performance of their team.
	 */
	SUPPORT,

	/**
	 * Represents a particularly strong enemy, often serving as the culmination of a challenge or level.
	 * Bosses are characterized by unique abilities, significant health pools, and challenging combat tactics.
	 */
	BOSS
}