package com.github.sef24sp4.weaponpack.shotgun;

import com.github.sef24sp4.common.data.Coordinates;
import com.github.sef24sp4.common.entities.IEntity;
import com.github.sef24sp4.common.interfaces.IEntityManager;
import com.github.sef24sp4.common.interfaces.IGameSettings;
import com.github.sef24sp4.common.projectile.CommonProjectile;
import com.github.sef24sp4.common.projectile.ProjectileSPI;
import com.github.sef24sp4.common.services.IEntityProcessingService;

public class MunitionControlSystem implements IEntityProcessingService, ProjectileSPI {
	private final double munitionSpeed = 20.0;

	@Override
	public CommonProjectile createProjectile(IEntity shooter) {
		Munition munition = new Munition(shooter);
		munition.setPolygonCoordinates(
				new Coordinates(-3, 3),
				new Coordinates(-3, -3),
				new Coordinates(3, -3),
				new Coordinates(3, 3)
		);

		munition.setCoordinates(shooter.getCoordinates().clone());
		munition.setRotation(shooter.getRotation());
		return munition;
	}

	@Override
	public void process(IEntityManager entityManager, IGameSettings gameSettings) {
		for (Munition munition : entityManager.removeEntitiesByClass(Munition.class)) {
			if (!gameSettings.isEntityWithinFrame(munition)) {
				entityManager.removeEntity(munition);
			}
			munition.setX(munition.getX() + Math.cos(munition.getRotation()) * this.munitionSpeed);
			munition.setY(munition.getY() + Math.sin(munition.getRotation()) * this.munitionSpeed);
		}
	}
}