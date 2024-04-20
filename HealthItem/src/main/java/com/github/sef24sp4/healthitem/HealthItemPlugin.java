package com.github.sef24sp4.healthitem;

import com.github.sef24sp4.common.entities.IEntity;
import com.github.sef24sp4.common.interfaces.IEntityManager;
import com.github.sef24sp4.common.interfaces.IGameSettings;
import com.github.sef24sp4.common.services.IGamePluginService;
import com.github.sef24sp4.common.vector.Coordinates;

public class HealthItemPlugin implements IGamePluginService {
	@Override
	public void launch(IEntityManager entityManager, IGameSettings gameSettings) {

	}

	@Override
	public void gameStart(IEntityManager entityManager, IGameSettings gameSettings) {
		int x = gameSettings.getDisplayWidth()/2;
		int y = gameSettings.getDisplayHeight()/2;
		HealthItem healthItem = new HealthItem();
		healthItem.setX(x);
		healthItem.setY(y);
		entityManager.addEntity(healthItem);
		System.out.println("Health item added " + healthItem.getRarity());
	}

	@Override
	public void gameStop(IEntityManager entityManager, IGameSettings gameSettings) {
		for (IEntity healthItem:entityManager.getEntitiesByClass(HealthItem.class)) {
			entityManager.removeEntity(healthItem);
		}
	}
}
