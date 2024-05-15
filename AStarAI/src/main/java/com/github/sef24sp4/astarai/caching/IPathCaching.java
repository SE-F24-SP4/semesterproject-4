package com.github.sef24sp4.astarai.caching;

import com.github.sef24sp4.common.entities.ICollidableEntity;
import com.github.sef24sp4.common.vector.IVector;

public interface IPathCaching {

	/**
	 * Set the Astar instance to null.
	 * Clear the pathlist
	 */
	public void flush();

	/**
	 * Method is used to give the next step in the path.
	 *
	 * @param entity            is the entity that needs to be moved.
	 * @param targetCoordinates is the coordinate the entity essentially needs to go to.
	 * @return an IVecter. If cache is valid it returns the next cached IVector.
	 * If not it calculates a new path by making a new instance on the Astar and returns next IVector. .
	 */
	public IVector getNextCoordinates(ICollidableEntity entity, final IVector targetCoordinates);
}

