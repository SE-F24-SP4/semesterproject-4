package com.github.sef24sp4.baseweapon;

import com.github.sef24sp4.common.entities.IEntity;
import com.github.sef24sp4.common.interfaces.IEntityManager;
import com.github.sef24sp4.common.weapon.WeaponSPI;

public class BaseWeapon implements WeaponSPI {
	private final int ammoCount = Integer.MAX_VALUE;
	private final ProjectileControlSystem projectileControlSystem = new ProjectileControlSystem();


	private final long maxCoolDownTicks = 1_000_000_000 / 4;
	private long timeOfLastShot;

	public BaseWeapon() {
		/*
		 * Allow shooting as soon the BaseWeapon is loaded.
		 * This works by artificially setting the timeOfLastShot to before {@code now - maxCoolDownTicks}.
		 */
		this.timeOfLastShot = System.nanoTime() - this.maxCoolDownTicks;
	}

	@Override
	public boolean shoot(IEntityManager entityManager, IEntity shooter) {
		if (this.getRemainingCoolDownTicks() > 0) return false;

		this.timeOfLastShot = System.nanoTime();

		entityManager.addEntity(this.projectileControlSystem.createProjectile(shooter));
		return true;
	}

	@Override
	public int getAmmoCount() {
		return this.ammoCount;
	}

	@Override
	public long getRemainingCoolDownTicks() {
		final long remainingCoolDownTicks = this.maxCoolDownTicks - (System.nanoTime() - this.timeOfLastShot);
		if (remainingCoolDownTicks <= 0) return 0;
		return remainingCoolDownTicks;
	}

}
