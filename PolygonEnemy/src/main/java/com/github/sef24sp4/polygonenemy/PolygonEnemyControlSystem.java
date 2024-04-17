package com.github.sef24sp4.polygonenemy;

import com.github.sef24sp4.common.ai.IPathfindingProvider;
import com.github.sef24sp4.common.data.Coordinates;
import com.github.sef24sp4.common.enemy.EnemySPI;
import com.github.sef24sp4.common.entities.IEntity;
import com.github.sef24sp4.common.interfaces.IEntityManager;
import com.github.sef24sp4.common.interfaces.IGameSettings;
import com.github.sef24sp4.common.interfaces.IVector;
import com.github.sef24sp4.common.services.IEntityProcessingService;
import com.github.sef24sp4.player.Player;

import java.util.Optional;
import java.util.ServiceLoader;

public class PolygonEnemyControlSystem implements IEntityProcessingService, EnemySPI {
	private final Optional<IPathfindingProvider> optionalAIPathProvider = ServiceLoader.load(IPathfindingProvider.class).findFirst();

	@Override
	public void process(IEntityManager entityManager, IGameSettings gameSettings) {
		entityManager.getEntitiesByClass(PolygonEnemy.class).forEach(polygonEnemy -> {
			// standard value, so will always go towards the player.
			IVector pointToGo = Player.getPlayer().getCoordinates();
			// will overwrite if pathfinding provider is available, to find a better path to player.
			if (this.optionalAIPathProvider.isPresent()) {
				pointToGo = this.optionalAIPathProvider.get().nextCoordinateInPath(polygonEnemy, pointToGo);
			}

			IVector directionVector = polygonEnemy.getCoordinates().getVectorTo(pointToGo);

			// check if the enemy is already at the target position to avoid division by zero.
			double norm = directionVector.getNorm();
			if (norm > 0) {
				// normalize and scale the direction vector
				IVector movementVector = new Coordinates(
						(directionVector.getX() / norm) * polygonEnemy.getSpeed(), // Normalize then scale
						(directionVector.getY() / norm) * polygonEnemy.getSpeed() // Normalize then scale
				);

				// move enemyPolygon a certain distance depending on movementVector.
				polygonEnemy.setX(polygonEnemy.getX() + movementVector.getX());
				polygonEnemy.setY(polygonEnemy.getY() + movementVector.getY());
			}
		});
	}

	@Override
	public IEntity createEnemy(int difficulty) {
		return new PolygonEnemy(difficulty + 2); // plus 2 to get the lowest form of a polygon enemy which have 3 edges, which is the starting point for this enemy.
	}
}
