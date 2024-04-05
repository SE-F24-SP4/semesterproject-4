package com.github.sef24sp4.common.weapon;

import com.github.sef24sp4.common.entities.IEntity;
import com.github.sef24sp4.common.interfaces.IEntityManager;

public interface WeaponSPI {

	/**
	 * This method tries to shoot a projectile with the current weapon.
	 *
	 * @param entityManager The entityManager where the projectile is added.
	 * @param shooter       The entity who is shooting.
	 *                      The projectiles direction match the shooter's rotation.
	 * @return {@code true} If shooting was successful {@code false} If shooting was unsuccessful.
	 * This can be because, the weapon has been triggered too fast {@link #getRemainingCoolDownTicks()}
	 * or there is no more ammunition {@link #getAmmoCount()}.
	 */
	public boolean shoot(IEntityManager entityManager, IEntity shooter);

	/**
	 * This method queries the amount of ammunition left.
	 *
	 * @return Positive integer describes the amount of projectiles the weapon has.
	 * {@code 0} If out of ammunition.
	 * {@code -1} If the weapon has infinite ammunition.
	 */
	public int getAmmoCount();

	/**
	 * This method queries the time in ticks, before the weapon is ready to shoot again.
	 *
	 * @return Positive long describing the remaining ticks.
	 * {@code 0} If the weapon is ready to shoot.
	 */
	public long getRemainingCoolDownTicks();
}
