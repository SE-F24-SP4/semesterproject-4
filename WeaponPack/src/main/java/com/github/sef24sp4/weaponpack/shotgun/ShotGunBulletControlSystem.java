package com.github.sef24sp4.weaponpack.shotgun;

import com.github.sef24sp4.common.vector.Coordinates;
import com.github.sef24sp4.common.entities.IEntity;
import com.github.sef24sp4.common.projectile.CommonProjectile;
import com.github.sef24sp4.weaponpack.AbstractBulletControlSystem;

public class ShotGunBulletControlSystem extends AbstractBulletControlSystem {
	private final double bulletSpeed = 20.0;

	//This method creates the look of the projectile and defines direction for the projectile, based on the shooter.
	@Override
	public CommonProjectile createProjectile(IEntity shooter) {
		return new ShotGunBullet(shooter);
	}

	@Override
	protected double getBulletSpeed() {
		return this.bulletSpeed;
	}
}