package com.github.sef24sp4.common.weapon;

import com.github.sef24sp4.common.entities.IEntity;

public interface WeaponSPI {

	/**
	 * @param shooter
	 * @return The shot projectile
	 */
	public IEntity shoot(IEntity shooter);
}
