package com.github.sef24sp4.common.entities;

public interface IAttackingEntity {

	/**
	 * Can be called by other components to query how much attack damage an attacking entity is capable of doing.
	 *
	 * @return The amount of damage.
	 */
	public double getAttackDamage();
}
