package com.github.sef24sp4.common.ai;

import com.github.sef24sp4.common.entities.IEntity;
import com.github.sef24sp4.common.interfaces.IVector;

public interface IPathfindingProvider {

	/**
	 * Entity can by implementing this method navigtate to a TargatCoordinate.
	 * The Method gives the entity the next coordinate on the optimal route towards the TargatCoordinate.
	 * @param entity is the entity that needs to be moved.
	 * @param targetCoordinate is the Coordinate the entity essentially needs to go to.
	 * @return an IVector, that is the next coordinate that the entity should go to.
	 */
	public IVector nextCoordinateInPath(IEntity entity, IVector targetCoordinate);

}


