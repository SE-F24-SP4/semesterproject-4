package com.github.sef24sp4.polygonenemy;

import com.github.sef24sp4.common.enemy.EnemySPI;
import com.github.sef24sp4.common.entities.IEntity;
import com.github.sef24sp4.common.interfaces.IEntityManager;
import com.github.sef24sp4.common.interfaces.IGameSettings;
import com.github.sef24sp4.common.services.IEntityProcessingService;

public class PolygonEnemyControlSystem implements IEntityProcessingService, EnemySPI {
	@Override
	public void process(IEntityManager entityManager, IGameSettings gameSettings) {
		for (IEntity polygonEnemy : entityManager.getEntitiesByClass(PolygonEnemy.class)) {
			// movement

		}
	}

	@Override
	public IEntity createEnemy(int difficulty) {
		return new PolygonEnemy(difficulty + 2); // plus 2 to get the lowest form of a polygon enemy which have 3 edges, which is the starting point for this enemy.
	}
}
