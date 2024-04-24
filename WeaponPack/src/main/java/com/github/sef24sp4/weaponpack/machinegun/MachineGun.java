package com.github.sef24sp4.weaponpack.machinegun;

import com.github.sef24sp4.common.entities.IEntity;
import com.github.sef24sp4.common.interfaces.IEntityManager;
import com.github.sef24sp4.common.weapon.WeaponSPI;

public class MachineGun implements WeaponSPI {
	//Variables defined for ammoCount and projectiles.
	private int ammoCount = 250;
	private final BulletControlSystem bulletControlSystem = new BulletControlSystem();

	private final long maxCountDownTicks = 1_000_000_000 / 16;
	private long timeOfLastShot;

	public MachineGun() {
		/*
		 * Allow shooting as soon the MachineGun is loaded.
		 * This works by artificially setting the timeOfLastShot to before {@code now - maxCoolDownTicks}.
		 */
		this.timeOfLastShot = System.nanoTime() - this.maxCountDownTicks;
	}

	//Shoot-method ensures, that if the ticks are high, and if there is no ammunition, then the gun cannot continue.
	//But if this statement needs to be true, then we need to add a bullet, which decreases based on the ammoCount--;
	@Override
	public boolean shoot(IEntityManager entityManager, IEntity shooter) {
		if (this.ammoCount <= 0 || this.getRemainingCoolDownTicks() > 0) return false;
		this.timeOfLastShot = System.nanoTime();
		entityManager.addEntity(this.bulletControlSystem.createProjectile(shooter));
		this.ammoCount--;
		return true;
	}

	/**
	 * Gets the amount of ammunition.
	 * @return The value of ammunition.
	 */
	@Override
	public int getAmmoCount() {
		return this.ammoCount;
	}

	/**
	 * The method defines remainingCoolDownTicks, which is the time from last shot and to the current time.
	 * @return The value of remainingCoolDownTicks.
	 */
	@Override
	public long getRemainingCoolDownTicks() {
		final long remainingCoolDownTicks = this.maxCountDownTicks - (System.nanoTime() - this.timeOfLastShot);
		if (remainingCoolDownTicks <= 0) return 0;
		return remainingCoolDownTicks;
	}
}
