package com.github.sef24sp4.baseweapon;

import com.github.sef24sp4.common.vector.Coordinates;
import com.github.sef24sp4.common.entities.IEntity;
import com.github.sef24sp4.common.interfaces.IEntityManager;
import com.github.sef24sp4.common.interfaces.IGameSettings;
import com.github.sef24sp4.common.projectile.CommonProjectile;
import com.github.sef24sp4.common.projectile.ProjectileSPI;
import com.github.sef24sp4.common.services.IEntityProcessingService;

public class ProjectileControlSystem implements IEntityProcessingService, ProjectileSPI {
	private final double projectileSpeed = 5.0;

	//This method creates the look of the projectile and defines direction for the projectile, based on the shooter.
	@Override
	public CommonProjectile createProjectile(IEntity shooter) {
		BaseProjectile projectile = new BaseProjectile(shooter);
		//Here the shape of the CommonProjectile-Polygon is defined.
		projectile.setPolygonCoordinates(
				new Coordinates(-1, 1),
				new Coordinates(-1, -1),
				new Coordinates(1, -1),
				new Coordinates(1, 1)
		);

		//Here is the direction of the polygon shoot-aim defined.
		projectile.setCoordinates(shooter.getCoordinates().clone());
		projectile.setRotation(shooter.getRotation());
		return projectile;
	}

	//This method defines the projectiles placement inside the map-frame.
	@Override
	public void process(IEntityManager entityManager, IGameSettings gameSettings) {
		for (BaseProjectile projectile : entityManager.getEntitiesByClass(BaseProjectile.class)) {
			if (!gameSettings.isEntityWithinFrame(projectile)) {
				entityManager.removeEntity(projectile);
			}

			projectile.setX(projectile.getX() + Math.cos(projectile.getRotation()) * this.projectileSpeed);
			projectile.setY(projectile.getY() + Math.sin(projectile.getRotation()) * this.projectileSpeed);
		}
	}
}
