package com.github.sef24sp4.weaponpack.machinegun;

import com.github.sef24sp4.common.entities.IEntity;
import com.github.sef24sp4.common.projectile.CommonProjectile;
import com.github.sef24sp4.weaponpack.AbstractBulletControlSystem;

public class MachineGunBulletControlSystem extends AbstractBulletControlSystem {
	private final double bulletSpeed = 10.0;
	@Override
	protected double getBulletSpeed() {
		return this.bulletSpeed;
	}
}
