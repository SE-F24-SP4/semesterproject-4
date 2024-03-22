package com.github.sef24sp4.common.collisionsystem;

import com.github.sef24sp4.common.entities.ICollidableEntity;

@FunctionalInterface
public interface IntersectsCallback {

	/**
	 * Call to check when two entities intersects with each other.
	 * @param entity1
	 * @param entity2
	 * @return true if the two entities are within each others bounding box.
	 */
	public boolean intersects(ICollidableEntity entity1, ICollidableEntity entity2);
}
