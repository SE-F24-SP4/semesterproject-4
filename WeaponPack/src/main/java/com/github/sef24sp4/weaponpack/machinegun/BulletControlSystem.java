package com.github.sef24sp4.weaponpack.machinegun;

import com.github.sef24sp4.common.vector.Coordinates;
import com.github.sef24sp4.common.entities.IEntity;
import com.github.sef24sp4.common.interfaces.IEntityManager;
import com.github.sef24sp4.common.interfaces.IGameSettings;
import com.github.sef24sp4.common.projectile.CommonProjectile;
import com.github.sef24sp4.common.projectile.ProjectileSPI;
import com.github.sef24sp4.common.services.IEntityProcessingService;

public class BulletControlSystem implements IEntityProcessingService, ProjectileSPI {
	private final double bulletSpeed = 10.0;

	//This method creates the look of the projectile and defines direction for the projectile, based on the shooter.
	@Override
	public CommonProjectile createProjectile(IEntity shooter) {
		Bullet bullet = new Bullet(shooter);
		bullet.setPolygonCoordinates(
				new Coordinates(-2, 2),
				new Coordinates(-2, -2),
				new Coordinates(2, -2),
				new Coordinates(2, 2)
		);

		//Here is the direction of the polygon shoot-aim defined.
		bullet.setCoordinates(shooter.getCoordinates().clone());
		bullet.setRotation(shooter.getRotation());
		return bullet;
	}

	//This method defines the projectiles placement inside the map-frame.
	@Override
	public void process(IEntityManager entityManager, IGameSettings gameSettings) {
		for (Bullet bullet : entityManager.removeEntitiesByClass(Bullet.class)) {
			if (!gameSettings.isEntityWithinFrame(bullet)) {
				entityManager.removeEntity(bullet);
			}
			bullet.setX(bullet.getX() + Math.cos(bullet.getRotation()) * this.bulletSpeed);
			bullet.setY(bullet.getY() + Math.sin(bullet.getRotation()) * this.bulletSpeed);
		}
	}
}
