package com.github.sef24sp4.weaponpack;

import com.github.sef24sp4.common.entities.IEntity;
import com.github.sef24sp4.common.interfaces.IEntityManager;
import com.github.sef24sp4.common.interfaces.IGameSettings;
import com.github.sef24sp4.common.projectile.ProjectileSPI;
import com.github.sef24sp4.common.services.IEntityProcessingService;
import com.github.sef24sp4.weaponpack.shotgun.ShotGunBullet;

public abstract class AbstractBulletControlSystem implements IEntityProcessingService, ProjectileSPI {
	@Override
	public void process(IEntityManager entityManager, IGameSettings gameSettings) {
		for (AbstractProjectile bullet : entityManager.getEntitiesByClass(AbstractProjectile.class)) {
			if (!gameSettings.isEntityWithinFrame(bullet)) {
				entityManager.removeEntity(bullet);
			}
			bullet.setX(bullet.getX() + Math.cos(bullet.getRotation()) * this.getBulletSpeed());
			bullet.setY(bullet.getY() + Math.sin(bullet.getRotation()) * this.getBulletSpeed());
		}
	}

	protected abstract double getBulletSpeed();
}
