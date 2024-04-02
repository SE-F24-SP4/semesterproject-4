package com.github.sef24sp4.polygonenemy;

import com.github.sef24sp4.common.data.Coordinates;
import com.github.sef24sp4.common.entities.IEntity;
import com.github.sef24sp4.common.interfaces.IEntityManager;
import com.github.sef24sp4.common.interfaces.IGameSettings;
import com.github.sef24sp4.common.services.IGamePluginService;

import java.util.Random;

/**
 * For demo purpose.
 * <p>
 * At game start it creates a circle of enemies ranging from 3 to 20 with random amount of edges ranging between 3 and 10.
 * <p>
 * At game stop it deletes all enemies of type PolygonEnemy.
 * @see IGamePluginService
 * @see PolygonEnemy
 */
public class PolygonEnemyPlugin implements IGamePluginService {
	@Override
	public void launch(IEntityManager entityManager, IGameSettings gameSettings) {

	}

	@Override
	public void gameStart(IEntityManager entityManager, IGameSettings gameSettings) {
		// spawn random enemies in a circle around the middle with equal spacing
		Random generator = new Random();
		int enemyAmount = generator.nextInt(3, 20);
		for (int enemy = 0; enemy < enemyAmount; enemy++) {
			// create entity with a random edge between 3 and 10
			int amountOfEdges = generator.nextInt(3, 10);
			PolygonEnemy polygonEnemy = new PolygonEnemy(amountOfEdges);

			// find x
			double x = 20 * Math.cos(2 * Math.PI * enemy / enemyAmount);
			// find y
			double y = 20 * Math.sin(2 * Math.PI * enemy / enemyAmount);
			// add start position
			Coordinates startPosition = new Coordinates(x, y);
			polygonEnemy.setCoordinates(startPosition);
			// add entity
			entityManager.addEntity(polygonEnemy);
		}
	}

	@Override
	public void gameStop(IEntityManager entityManager, IGameSettings gameSettings) {
		// delete all enemies of type PolygonEnemy
		for (IEntity polygonEnemy : entityManager.getEntitiesByClass(PolygonEnemy.class)) {
			entityManager.removeEntity(polygonEnemy);
		}
	}
}
