package com.github.sef24sp4.newbaseweapon;

import com.github.sef24sp4.common.interfaces.IEntityManager;
import com.github.sef24sp4.common.interfaces.IGameSettings;
import com.github.sef24sp4.common.services.IGamePluginService;

public class BulletPlugin implements IGamePluginService {

	@Override
	public void launch(final IEntityManager entityManager, final IGameSettings gameSettings) {

	}

	@Override
	public void gameStart(final IEntityManager entityManager, final IGameSettings gameSettings) {

	}

	@Override
	public void gameStop(final IEntityManager entityManager, final IGameSettings gameSettings) {
		entityManager.removeEntitiesByClass(AbstractProjectile.class);
	}
}
