package com.github.sef24sp4.collisionsystem;

import com.github.sef24sp4.collisionsystem.map.BucketMap;
import com.github.sef24sp4.common.collisionsystem.CollisionSystemSPI;
import com.github.sef24sp4.common.interfaces.IGameSettings;

public class CollisionSystemFactory implements com.github.sef24sp4.common.collisionsystem.CollisionSystemFactory {
	@Override
	public CollisionSystemSPI create(final IGameSettings gameSettings) {
		return new CollisionSystem(BucketMap.generate(gameSettings));
	}
}
