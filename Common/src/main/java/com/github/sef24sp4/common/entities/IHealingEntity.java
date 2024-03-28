package com.github.sef24sp4.common.entities;

public interface IHealingEntity {

	/**
	 * Gets called by other entities to query how much this entity can heal the other entity.
	 *
	 * @return The amount of healing.
	 */
	public double getHealingAmount();
}
