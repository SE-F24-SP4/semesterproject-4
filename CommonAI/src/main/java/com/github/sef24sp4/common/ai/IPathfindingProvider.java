package com.github.sef24sp4.common.ai;

import com.github.sef24sp4.common.entities.IEntity;
import com.github.sef24sp4.common.interfaces.IVector;

public interface IPathfindingProvider {

	/**
	 * Entity can use this method to navigate to a targetCoordinate.
	 * The Method gives the entity the next coordinate on the optimal route towards the targetCoordinate.
	 * @param entity is the entity that needs to be moved.
	 * @param targetCoordinate is the coordinate the entity essentially needs to go to.
	 * @return an IVector, that is the next coordinate that the entity should go to.
	 */
	public IVector nextCoordinateInPath(IEntity entity, IVector targetCoordinate);

}


