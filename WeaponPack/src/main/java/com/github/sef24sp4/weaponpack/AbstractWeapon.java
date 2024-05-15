package com.github.sef24sp4.weaponpack;

import com.github.sef24sp4.common.weapon.WeaponSPI;

public abstract class AbstractWeapon implements WeaponSPI {
	private final long maxCoolDownTicks;
	private long timeOfLastShot;
	private int ammoCount;

	public AbstractWeapon(final long maxCoolDownTicks, final int ammoCount) {
		this.maxCoolDownTicks = maxCoolDownTicks;
		this.ammoCount = ammoCount;
		/*
		 * Allow shooting as soon the Weapon is loaded.
		 * This works by artificially setting the timeOfLastShot to before {@code now - maxCoolDownTicks}.
		 */
		this.timeOfLastShot = System.nanoTime() - this.maxCoolDownTicks;
	}

	/**
	 * The method defines remainingCoolDownTicks, which is the time from last shot and to the current time.
	 *
	 * @return The value of remainingCoolDownTicks.
	 */
	@Override
	public long getRemainingCoolDownTicks() {
		final long remainingCoolDownTicks = this.getMaxCoolDownTicks() - (System.nanoTime() - this.getTimeOfLastShot());
		if (remainingCoolDownTicks <= 0) return 0;
		return remainingCoolDownTicks;
	}

	protected long getMaxCoolDownTicks() {
		return this.maxCoolDownTicks;
	}

	protected long getTimeOfLastShot() {
		return this.timeOfLastShot;
	}

	protected long resetTimeOfLastShot() {
		this.timeOfLastShot = System.nanoTime();
		return this.timeOfLastShot;
	}

	protected boolean prepareShootIfPossible() {
		if (this.ammoCount <= 0 || this.getRemainingCoolDownTicks() > 0) return false;
		this.resetTimeOfLastShot();
		this.ammoCount--;
		return true;
	}

	/**
	 * Gets the amount of ammunition.
	 *
	 * @return The value of ammunition.
	 */
	@Override
	public int getAmmoCount() {
		return this.ammoCount;
	}

}
