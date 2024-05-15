package com.github.sef24sp4.weaponpack.shotgun;

import com.github.sef24sp4.weaponpack.AbstractBulletControlSystem;

public class ShotGunBulletControlSystem extends AbstractBulletControlSystem {
	private final double bulletSpeed = 20.0;

	@Override
	protected double getBulletSpeed() {
		return this.bulletSpeed;
	}
}