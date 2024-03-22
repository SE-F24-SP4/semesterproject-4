package com.github.sef24sp4.common.collisionsystem;

import com.github.sef24sp4.common.interfaces.IEntityManager;
import com.github.sef24sp4.common.interfaces.IGameSettings;

public interface CollisionSystemSPI {

	/**
	 * Important that this is called before processCollisions method.
	 * @param callback
	 */
	public void setIntersectsCallback(IntersectsCallback callback);


	public void processCollisions(IEntityManager entityManager, IGameSettings gameSettings);
}
