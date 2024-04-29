package com.github.sef24sp4.common.entities;

public interface ISpeedModifyingEntity {

	/**
	 * Gets called by other entities to query how much this entity can boost speed by.
	 *
	 * @return The amount of additional speed.
	 */
	public double getSpeedAmount();
}
