package com.github.sef24sp4.polygonenemy;

import com.github.sef24sp4.common.ai.IPathfindingProvider;
import com.github.sef24sp4.common.enemy.EnemySPI;
import com.github.sef24sp4.common.entities.ICollidableEntity;
import com.github.sef24sp4.common.entities.IEntity;
import com.github.sef24sp4.common.interfaces.IEntityManager;
import com.github.sef24sp4.common.interfaces.IGameSettings;
import com.github.sef24sp4.common.metadata.GameElementType;
import com.github.sef24sp4.common.services.IEntityProcessingService;
import com.github.sef24sp4.common.vector.BasicVector;
import com.github.sef24sp4.common.vector.IVector;

import java.util.Optional;
import java.util.ServiceLoader;

public class PolygonEnemyControlSystem implements IEntityProcessingService, EnemySPI {
	private final Optional<IPathfindingProvider> optionalAIPathProvider = ServiceLoader.load(IPathfindingProvider.class).findFirst();
	private IEntity player;

	@Override
	public void process(IEntityManager entityManager, IGameSettings gameSettings) {
		// Pre-fetch and cache the player once
		if (this.player == null) {
			entityManager.getEntitiesByClass(ICollidableEntity.class).forEach(entity -> {
				if (entity.getType() == GameElementType.PLAYER) {
					this.player = entity;
				}
			});
		}
		// Early exit if player has not been initialized yet
		if (this.player == null) return;
		IVector playerCoordinates = this.player.getCoordinates();

		// loop through all polygon enemies
		entityManager.getEntitiesByClass(PolygonEnemy.class).forEach(polygonEnemy -> {
			// standard value, so will always go towards the player.
			IVector pointToGo = playerCoordinates;

			// will overwrite pointToGo if pathfinding provider is available, to find a better path to player.
			if (this.optionalAIPathProvider.isPresent()) {
				pointToGo = this.optionalAIPathProvider.get().nextCoordinateInPath(polygonEnemy, pointToGo);
			}
			// find directionVector
			BasicVector directionVector = (BasicVector) polygonEnemy.getCoordinates().getVectorTo(pointToGo);

			// create a movementVector by normalizing and scaling the directionVector
			BasicVector movementVector = (BasicVector) directionVector.getNormalizedVector();
			movementVector.scale(polygonEnemy.getSpeed());

			// move enemyPolygon a certain distance depending on movementVector.
			polygonEnemy.getCoordinates().add(movementVector);

		});
	}

	@Override
	public IEntity createEnemy(int difficulty) {
		return new PolygonEnemy(difficulty + 2); // plus 2 to get the lowest form of a polygon enemy which have 3 edges, which is the starting point for this enemy.
	}
}
