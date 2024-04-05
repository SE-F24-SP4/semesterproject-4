package com.github.sef24sp4.common.ai;

import com.github.sef24sp4.common.entities.IEntity;
import com.github.sef24sp4.common.interfaces.IVector;

public interface IPathfindingProvider {

	/**
	 *
	 * @param entity which is an enemy, should go to the
	 * @param targetCoordinate
	 * @return an IVector, that is the next node (targetCoordinate), that the entity should go to.
	 *         This can be a coordinate, which is the Coordinate of the player.
	 */
	public IVector calculateNextMove(IEntity entity, IVector targetCoordinate);

}


