package com.github.sef24sp4.common.collisionsystem;

import com.github.sef24sp4.common.entities.ICollidableEntity;
import com.github.sef24sp4.common.interfaces.IEntityManager;

public interface CollisionSystemSPI {

	/**
	 * Adds {@link ICollidableEntity} to the collision system if not already present.
	 *
	 * @param entity The {@link ICollidableEntity} to be added.
	 * @return {@code true} if the {@code entity} was successfully added.
	 * {@code false} otherwise. This may be because the {@code entity} was already present.
	 */
	public boolean addEntity(final ICollidableEntity entity);

	/**
	 * Removes {@link ICollidableEntity} from the collision system if present.
	 *
	 * @param entity The {@link ICollidableEntity} to be removed.
	 * @return {@code true} if the {@code entity} was present, and successfully removed.
	 * {@code false} otherwise.
	 */
	public boolean removeEntity(final ICollidableEntity entity);

	/**
	 * Process all collision among the stored {@link ICollidableEntity collidable entities}.
	 *
	 * @param entityManager The game global {@link IEntityManager}.
	 * @return {@code true} if successfully processed all {@link ICollidableEntity entities}.
	 * {@code false} if failed to process some or all {@link ICollidableEntity entities}.
	 */
	public boolean processCollisions(final IEntityManager entityManager);
}
