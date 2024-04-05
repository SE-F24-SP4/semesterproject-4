package com.github.sef24sp4.common.ai;

import com.github.sef24sp4.common.entities.IEntity;
import com.github.sef24sp4.common.interfaces.IVector;

public interface IPathfindingProvider {

	/**
	 * Entity can by implementing this method navigtate to a TargatCoordinate.
	 * The Method start from the StartCoordinate of the Entity, open neighbor nodes, and expand the node with the lowest fCost.
	 * When the best route towards a target is known. The Entity will navigate to the next step on this route, which is the TargatCoordinate.
	 *
	 * @return an IVector, that is the next node (targetCoordinate), that the entity should go to. This can be a coordinate.
	 */
	public IVector calculateNextMove(IEntity entity, IVector targetCoordinate);

}


