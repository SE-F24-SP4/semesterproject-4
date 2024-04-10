package com.github.sef24sp4.baseweapon;

import com.github.sef24sp4.common.data.Coordinates;
import com.github.sef24sp4.common.entities.ICollidableEntity;
import com.github.sef24sp4.common.interfaces.IEntityManager;
import com.github.sef24sp4.common.interfaces.IGameSettings;
import com.github.sef24sp4.common.projectile.CommonProjectile;
import com.github.sef24sp4.common.projectile.ProjectileSPI;
import com.github.sef24sp4.common.services.IEntityProcessingService;

public class ProjectileControlSystem implements IEntityProcessingService, ProjectileSPI {
	@Override
	public CommonProjectile createProjectile(ICollidableEntity shooter) {
		//
		BaseProjectile projectile = new BaseProjectile(shooter);
		//Here the shape of the CommonProjectile-Polygon is defined.
		projectile.setPolygonCoordinates(
				new Coordinates(-1, 1),
				new Coordinates(-1, -1),
				new Coordinates(1, -1),
				new Coordinates(1, 1)
		);

		//Here is the direction of the polygon shoot-aim defined.
		projectile.setCoordinates(shooter.getCoordinates());
		projectile.setRotation(shooter.getRotation());
		return projectile;
	}

	@Override
	public void process(IEntityManager entityManager, IGameSettings gameSettings) {
		final double projectileSpeed = 5.0;
		for (BaseProjectile projectile : entityManager.getEntitiesByClass(BaseProjectile.class)) {
			if (!gameSettings.isEntityWithinFrame(projectile)) {
				if (projectile.getX() < 0) {
					projectile.setX(0);
				}
				if (projectile.getX() > gameSettings.getDisplayWidth()) {
					entityManager.removeEntity(projectile);
				}
				if (projectile.getY() < 0) {
					projectile.setY(0);
				}
				if (projectile.getY() > gameSettings.getDisplayHeight()) {
					entityManager.removeEntity(projectile);
				}
			}
			projectile.setX(projectile.getX() + Math.cos(Math.toRadians(projectile.getRotation()) * projectileSpeed));
			projectile.setY(projectile.getY() + Math.sin(Math.toRadians(projectile.getRotation()) * projectileSpeed));
		}
	}
}
