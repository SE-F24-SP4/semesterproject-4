package com.github.sef24sp4.common.entities;

import com.github.sef24sp4.common.interfaces.IEntityManager;

public interface ICollidableEntity extends IEntity {
	/**
	 * Called when entity collides with another collidable entity.
	 * Do NOT call `collide` method on `otherEntity`, this will be done automatically by CollisionProcessor
	 * `otherEntity` is provided to query which entity it has collided with.
	 *
	 * @param entityManager
	 * @param otherEntity
	 */
	public void collide(IEntityManager entityManager, ICollidableEntity otherEntity);
}
