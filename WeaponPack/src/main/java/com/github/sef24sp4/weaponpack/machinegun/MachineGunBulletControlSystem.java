package com.github.sef24sp4.weaponpack.machinegun;

import com.github.sef24sp4.common.vector.Coordinates;
import com.github.sef24sp4.common.entities.IEntity;
import com.github.sef24sp4.common.interfaces.IEntityManager;
import com.github.sef24sp4.common.interfaces.IGameSettings;
import com.github.sef24sp4.common.projectile.CommonProjectile;
import com.github.sef24sp4.common.projectile.ProjectileSPI;
import com.github.sef24sp4.common.services.IEntityProcessingService;
import com.github.sef24sp4.weaponpack.AbstractBulletControlSystem;

public class MachineGunBulletControlSystem extends AbstractBulletControlSystem {
	private final double bulletSpeed = 10.0;

	//This method creates the look of the projectile and defines direction for the projectile, based on the shooter.
	@Override
	public CommonProjectile createProjectile(IEntity shooter) {
		return new MachineGunBullet(shooter);
	}

	@Override
	protected double getBulletSpeed() {
		return this.bulletSpeed;
	}
}
