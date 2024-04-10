package com.github.sef24sp4.baseweapon;

import com.github.sef24sp4.common.entities.ICollidableEntity;
import com.github.sef24sp4.common.entities.IEntity;
import com.github.sef24sp4.common.interfaces.IEntityManager;
import com.github.sef24sp4.common.weapon.WeaponSPI;

public class BaseWeapon implements WeaponSPI {
	private int ammoCount;
	private final int maxAmmoCount;
	private long remainingCoolDownTicks = 0;
	private long timeOfShoot = 0;
	private final long presentTime = System.nanoTime();

	public BaseWeapon() {
		this.ammoCount = this.getAmmoCount();
		this.maxAmmoCount = Integer.MAX_VALUE;
		this.remainingCoolDownTicks = 0;
		this.timeOfShoot = 0;
	}

	@Override
	public boolean shoot(IEntityManager entityManager, IEntity shooter) {
		if (this.getRemainingCoolDownTicks() < 0 && this.getAmmoCount() <= 0) {
			return false;
		} else if (this.getRemainingCoolDownTicks() > 0 && this.getAmmoCount() > 0) {
			return true;
		}

		while (this.getRemainingCoolDownTicks() > 0 && this.getAmmoCount() > 0) {
			this.ammoCount--;
		}

		this.remainingCoolDownTicks = this.getRemainingCoolDownTicks();

		ProjectileControlSystem projectileControlSystem = new ProjectileControlSystem();
		entityManager.addEntity(projectileControlSystem.createProjectile((ICollidableEntity) shooter));
		return true;
	}

	@Override
	public int getAmmoCount() {
		if (this.ammoCount > 0) {
			return 0;
		} else if (this.ammoCount < 0) {
			return this.maxAmmoCount;
		}
		return this.ammoCount;
	}

	@Override
	public long getRemainingCoolDownTicks() {
		if (this.remainingCoolDownTicks > this.presentTime - this.timeOfShoot) {
			return this.remainingCoolDownTicks;
		} else if (this.remainingCoolDownTicks < this.presentTime - this.timeOfShoot) {
			return 0;
		}
		return this.remainingCoolDownTicks;
	}

}
