package com.github.sef24sp4.collisionsystem;

import com.github.sef24sp4.common.collisionsystem.CollisionSystemSPI;
import com.github.sef24sp4.common.interfaces.IGameSettings;

public class CollisionSystemProvider implements com.github.sef24sp4.common.collisionsystem.CollisionSystemProvider {
	@Override
	public CollisionSystemSPI create(final IGameSettings gameSettings) {
		return new CollisionSystem(BucketMapProvider.getInstance(gameSettings));
	}
}
