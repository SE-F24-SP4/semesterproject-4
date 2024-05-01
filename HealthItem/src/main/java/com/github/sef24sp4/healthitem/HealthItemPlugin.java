package com.github.sef24sp4.healthitem;

import com.github.sef24sp4.common.entities.IEntity;
import com.github.sef24sp4.common.interfaces.IEntityManager;
import com.github.sef24sp4.common.interfaces.IGameSettings;
import com.github.sef24sp4.common.services.IGamePluginService;

public class HealthItemPlugin implements IGamePluginService {
	@Override
	public void launch(IEntityManager entityManager, IGameSettings gameSettings) {

	}

	@Override
	public void gameStart(IEntityManager entityManager, IGameSettings gameSettings) {
	}

	@Override
	public void gameStop(IEntityManager entityManager, IGameSettings gameSettings) {
		entityManager.removeEntitiesByClass(HealthItem.class);
	}
}
